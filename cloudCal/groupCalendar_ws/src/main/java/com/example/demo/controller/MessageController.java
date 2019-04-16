package com.example.demo.controller;

import com.example.demo.entity.Gtotal;
import com.example.demo.entity.Message;
import com.example.demo.entity.Users;
import com.example.demo.service.IGtotalService;
import com.example.demo.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    private IMessageService iMessageService;
    //添加消息
    @RequestMapping(value = "/addmeg",method = RequestMethod.POST)
    public void addmeg(@RequestParam String sender,@RequestParam String receiver,@RequestParam String type,
                       @RequestParam int eventid,@RequestParam String groupname) {
        iMessageService.addmeg(sender,receiver,Integer.parseInt(type),eventid,groupname);
    }
    //展示消息
    @RequestMapping(value = "/findmeg",method = RequestMethod.POST)
    public List<Message> findmeg(@RequestParam String receiver){
        return iMessageService.findmeg(receiver);
    }
    //修改消息状态
    @RequestMapping(value = "/upmeg",method = RequestMethod.POST)
    public void upmeg(@RequestParam int messageid,@RequestParam int is_accept) {
        iMessageService.upmeg(messageid,is_accept);
    }

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

    //展示用户信息
    @RequestMapping(value = "/listuser",method = RequestMethod.POST)
    public List<Users> listuser(){
        return iUserService.listuser();
    }
    //修改个人信息
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public void updateuser(@RequestBody Users users) {
        iUserService.updateuser(users);
    }*/
}
