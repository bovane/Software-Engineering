package com.example.demo.service;

import com.example.demo.entity.GUsers;
import com.example.demo.entity.Users;
import java.util.List;

public interface IGusersService {
    void addgus(int groupid,String groupname,String username);
    List<GUsers> mygrp(String username);
    List<GUsers> list(String groupname);
    List<GUsers> find(String groupname,String username);
    GUsers count(int groupid);
    void upgus(String groupname,String username,int level);
    void del(String groupname,String username);
/*
    Users finduser(String username);
    void updateuser(Users users);
    void deleteuser(String username);
    boolean forgetPWD(String email);
    boolean vertifyCode(String email, String code);
    */
}
