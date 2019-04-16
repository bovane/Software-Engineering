package com.example.demo.controller;

import com.example.demo.entity.GUsers;
import com.example.demo.entity.Gtotal;
import com.example.demo.entity.Users;
import com.example.demo.service.IGtotalService;
import com.example.demo.service.IGusersService;
import com.example.demo.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/gusers")
public class GusersController {
    @Autowired
    private IGusersService iGusersService;
    @Autowired
    private IGtotalService iGtotalService;
    //添加用户
    @RequestMapping(value = "/addgus",method = RequestMethod.POST)
    public void addgus(@RequestParam String groupname,@RequestParam String username) {
        List<Gtotal> gtotal=iGtotalService.findgroup(groupname);
        int groupid = gtotal.get(0).getGroupid();
        iGusersService.addgus(groupid,groupname,username); }
    //展示我的群组
    @RequestMapping(value = "/mygrp",method = RequestMethod.POST)
    public List<GUsers> mygrp(@RequestParam String username){
        return iGusersService.mygrp(username);
    }
    //count
    @RequestMapping(value = "/count",method = RequestMethod.POST)
    public GUsers count(@RequestParam int groupid){
        return iGusersService.count(groupid);
    }
    //展示群组成员
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<GUsers> list(@RequestParam String groupname){
        return iGusersService.list(groupname);
    }
    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public List<GUsers> find(@RequestParam String groupname,@RequestParam String username){
        return iGusersService.find(groupname,username);
    }
    //修改权限
    @RequestMapping(value = "/upgus",method = RequestMethod.POST)
    public void upgus(@RequestParam String groupname,@RequestParam String username,@RequestParam int level) {
        iGusersService.upgus(groupname,username,level);
    }
    //删除
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public void del(@RequestParam String groupname,@RequestParam String username) {
        iGusersService.del(groupname,username); }
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



    //修改个人信息
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public void updateuser(@RequestBody Users users) {
        iUserService.updateuser(users);
    }*/
}
