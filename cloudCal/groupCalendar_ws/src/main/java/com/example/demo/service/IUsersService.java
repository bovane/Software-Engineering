package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Users;

import java.util.List;

public interface IUsersService {
    void adduser(Users users);
    Users finduser(String username);
    void updateuser(Users users);
    void updatepassword(String username, String password);
    void deleteuser(String username);
    boolean forgetPWD(String email);
    boolean vertifyCode(String email, String code);
    List<Users> listuser();
    List<Users> list(String username);
}
