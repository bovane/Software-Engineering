package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Users;
import com.example.demo.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private IUsersService iUserService;
    //添加用户
    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public void adduser(@RequestBody Users users) { iUserService.adduser(users); }
    //登入
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Users login(@RequestBody Users users){
        return iUserService.finduser(users.getUsername());
    }
    //发送邮件
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
    public void forgetpassword(@RequestParam String email) {
        iUserService.forgetPWD(email);
    }
    // 验证验证码
    @RequestMapping(value = "/vertifyCode",method = RequestMethod.POST)
    public void vertifyCode(@RequestParam String email,@RequestParam String code) {
        iUserService.vertifyCode(email,code);
    }
    //修改密码
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public void updatepassword(@RequestParam String username,@RequestParam String password) {
        iUserService.updatepassword(username,password);
    }
    //展示用户信息
    @RequestMapping(value = "/listuser",method = RequestMethod.POST)
    public List<Users> listuser(){
        return iUserService.listuser();
    }
    //展示用户信息(个人)
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<Users> list(@RequestParam String username){
        return iUserService.list(username);
    }
    //修改个人信息
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public void updateuser(@RequestBody Users users) {
        iUserService.updateuser(users);
    }
}
