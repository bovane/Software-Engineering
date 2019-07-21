package cn.edu.zjgsu.controller;

import java.util.List;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zjgsu.bean.RoleInfo;
import cn.edu.zjgsu.bean.UserInfo;
import cn.edu.zjgsu.service.IRoleInfoService;
import cn.edu.zjgsu.service.IUserInfoService;
import utils.Comm;
import utils.Page;

@Controller
@RequestMapping("/user/")
public class UserController extends Page {

    // 用户业务逻辑
    @Autowired
    private IUserInfoService userservice;

    // 角色管理的业务逻辑
    @Autowired
    private IRoleInfoService roleservice;

    /**
     * 查询用户信息
     *
     * @return
     */
    @RequestMapping("list.do")
    public String list(UserInfo info, Model model, HttpServletRequest request) {
        if (info == null) {
            info = new UserInfo();
        }
        info.setUserMark(Comm.MARK_YES);
        // 分頁信息
        this.initPage(request);
        //起始记录
        info.setStart(this.getPageNo());
        //每页显示记录数
        info.setLength(PAGE_NUM_BIG);
        model.addAttribute("list",userservice.list(info));
        //总记录数
        model.addAttribute("total",userservice.getcount(info));

        return "user/user_list";
    }

    /**
     * 加载添加页面
     *
     * @return
     */
    @RequestMapping("loadadd.do")
    public String loadadd(Model model) {
        RoleInfo info = new RoleInfo();
        info.setStart(-1);
        List<Role> list = roleservice.list(info);
        model.addAttribute("list", list); // 添加所有角色信息
        return "user/user_add";
    }

    @RequestMapping("add.do")
    public String add(UserInfo user, Model model,HttpServletRequest request) {
        try {
            if (user == null) {
                user = new UserInfo();
            }

            user.setUserMark(Comm.MARK_YES);
            userservice.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorinfo", "用户添加失败");
        }
        return list(null,model,request);
    }

    /**
     * 验证账号是否已存在
     *
     * @param user
     * @return
     */
    @RequestMapping("check.do")
    public @ResponseBody String check(UserInfo user) {
        UserInfo userinfo = userservice.getUser(user);
        if (userinfo != null) {
            return "账号已存在，请重新输入！";
        } else {
            return "该账号可以使用";
        }

    }

    /**
     * 加载修改页面
     *
     * @return
     */
    @RequestMapping("loadupdate.do")
    public String loadupdate(UserInfo user, Model model) {
        //用户信息
        UserInfo userinfo = userservice.getUser(user);
        model.addAttribute("userinfo", userinfo);

        //角色信息
        RoleInfo info = new RoleInfo();
        info.setStart(-1);
       // List<RoleInfo> list = roleservice.list(info);
        List<Role> list = roleservice.list(info);
        model.addAttribute("list", list);
        return "user/user_update";
    }

    /**
     * 查看用户信息
     * @return
     */
    @RequestMapping("listuser.do")
    public String listuser(UserInfo user,Model model) {
        UserInfo userinfo=userservice.getUser(user);
        model.addAttribute("userinfo",userinfo);
        return "user/user_info";
    }

    /**
     * 修改用户信息
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("update.do")
    public String update(UserInfo user,Model model,HttpServletRequest request) {
        try {
            System.out.println(user);
            userservice.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorinfo", "用户修改失败");
        }
        return list(null,model,request);
    }

    /**
     * 删除用户信息
     * @param userId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("delete.do")
    public String delete(Integer[] userId, Model model,HttpServletRequest request) {
        try {
            userservice.delete(userId);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorinfo", "用户删除失败");
        }

        return list(null,model,request);
    }

}
