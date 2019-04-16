package com.example.demo.service;

import com.example.demo.entity.Gtotal;
import com.example.demo.entity.Users;

import java.util.List;

public interface IGtotalService {
    List<Gtotal> findgroup(String groupname);
    List<Gtotal> listgroup();
    void addgrp(String groupname,String groupdesc,String owner);
/*
    Users finduser(String username);
    void updateuser(Users users);
    void updatepassword(String username, String password);
    void deleteuser(String username);
    boolean forgetPWD(String email);
    boolean vertifyCode(String email, String code);
    List<Users> list(String username);*/
}
