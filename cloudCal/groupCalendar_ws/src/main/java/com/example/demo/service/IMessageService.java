package com.example.demo.service;

import com.example.demo.entity.Gtotal;
import com.example.demo.entity.Message;
import com.example.demo.entity.Users;

import java.lang.reflect.Member;
import java.util.List;

public interface IMessageService {
    void addmeg(String sender,String receiver,int type,int eventid,String groupname);
    List<Message> findmeg(String receiver);
    void upmeg(int messageid,int is_accept);

/*
    Users finduser(String username);
    void updateuser(Users users);
    void deleteuser(String username);
    boolean forgetPWD(String email);
    boolean vertifyCode(String email, String code);
   */
}
