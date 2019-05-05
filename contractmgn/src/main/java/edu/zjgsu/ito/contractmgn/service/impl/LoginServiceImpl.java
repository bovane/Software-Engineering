package edu.zjgsu.ito.contractmgn.service.impl;

import edu.zjgsu.ito.contractmgn.mapper.ReminderDAO;
import edu.zjgsu.ito.contractmgn.mapper.UserDAO;
import edu.zjgsu.ito.contractmgn.model.ReminderExample;
import edu.zjgsu.ito.contractmgn.model.User;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.LoginService;
import edu.zjgsu.ito.contractmgn.service.UserService;
import edu.zjgsu.ito.contractmgn.util.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;
    @Autowired
    ReminderDAO reminderDAO;

    @Override
    public Message userLogin(User user,HttpServletResponse response) throws UnsupportedEncodingException {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), Md5Util.getMD5(user.getPassword()));
        try {
            subject.login(token);
        }catch (Exception e){
            if(e instanceof LockedAccountException){
                return Message.createErr(501,"user locked");
            }
            if(e instanceof IncorrectCredentialsException){
                return Message.createErr(502,"wrong pwd");
            }
            if(e instanceof UnknownAccountException){
                return Message.createErr(503,"wrong username");
            }
        }

        User user1 = userService.getUserByUserName(user.getUsername());

        subject.getSession().setAttribute("userId",user1.getId());
        subject.getSession().setAttribute("role",user1.getRole());
        HashMap<String,Object> result = new HashMap<>();
        result.put("reset",user1.getReset());
        result.put("role",user1.getRole());
        result.put("nickname",user1.getName());
        result.put("unread",getUnReadMessageAccount());
        result.put("userId",user1.getId());

        return Message.createSuc(result);
    }



    @Override
    public Message logout() {

        SecurityUtils.getSubject().logout();

        return Message.createSuc(null);
    }

    private int getUnReadMessageAccount(){

        int unread = 0;

        Subject subject = SecurityUtils.getSubject();
        ReminderExample example = new ReminderExample();
        if(subject.hasRole("manager")){
            example.or().andManagerEqualTo(false);
            unread = (int)reminderDAO.countByExample(example);
        }
        if(subject.hasRole("accountant")){
            example.or().andAccountantEqualTo(false);
            unread = (int)reminderDAO.countByExample(example);
        }
        if(subject.hasRole("overallchief")){
            example.or().andOverallchiefEqualTo(false);
            unread = (int)reminderDAO.countByExample(example);
        }

        return unread;
    }
}
