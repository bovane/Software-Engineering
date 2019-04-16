package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.acl.Group;
import java.util.List;

@RestController
@RequestMapping(value = "/gtotal")
public class GtotalController {
    @Autowired
    private IGtotalService iGroupService;
    //搜索群组
    @RequestMapping(value = "/findgroup",method = RequestMethod.POST)
    public List<Gtotal> findgroup(@RequestParam String groupname){
        return iGroupService.findgroup(groupname);
    }
    @RequestMapping(value = "/listgroup",method = RequestMethod.POST)
    public List<Gtotal> listgroup(){
        return iGroupService.listgroup();
    }
    //添加群组
    @RequestMapping(value = "/addgrp",method = RequestMethod.POST)
    public void addgrp(@RequestParam String groupname,@RequestParam String groupdesc,@RequestParam String owner) {
        iGroupService.addgrp(groupname,groupdesc,owner); }
/*
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
    }*/
}
