package com.example.demo.service.impl;

import com.example.demo.dao.GtotalDao;
import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Gtotal;
import com.example.demo.entity.Message;
import com.example.demo.entity.Users;
import com.example.demo.service.IGtotalService;
import com.example.demo.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private MessageDao messageDao;
    //添加消息
    @Override
    public void addmeg(String sender,String receiver,int type,int eventid,String groupname) {
        messageDao.addmeg(sender,receiver,type,eventid,groupname); }
    //展示消息
    public List<Message> findmeg(String receiver) { return messageDao.findmeg(receiver);}
    //修改密码
    @Override
    public void upmeg(int messageid,int is_accept) {
        messageDao.upmeg(messageid,is_accept);
    }

/*
    //通过用户名找用户
    @Override
    public Users finduser(String username) {
        return usersDao.finduser(username);
    }
    // 更新用户资料
    @Override
    public void updateuser(Users users) {
        usersDao.updateuser(users);
    }

    // 删除用户
    @Override
    public void deleteuser(String username) {
        usersDao.deleteuser(username);
    }
    //发送邮件
    @Override
    public boolean forgetPWD(String email) {
        //生成激活码
        String code = CodeUtil.generateUniqueCode();
        Jedis jedis = BaseRedis.getJedis();
        jedis.set(email,code);
        jedis.expire(email,60*5); // 设置过期时间，五分钟
        BaseRedis.returnResource(jedis);
        BaseMailUtil baseMailUtil = new BaseMailUtil();
        try {
            baseMailUtil.sendEmail(email,code);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return true;
    }
    //验证验证码
    public boolean vertifyCode(String email,String code) {
        Jedis jedis = BaseRedis.getJedis();
        if(code.compareTo(jedis.get(email))==0) {
            System.out.println("匹配一致");
            return true;
        }
        System.out.println(jedis.get(email));
        return false;
    }

    */
}
