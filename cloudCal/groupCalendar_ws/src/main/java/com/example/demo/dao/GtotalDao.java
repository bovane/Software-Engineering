package com.example.demo.dao;

import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GtotalDao {
    List<Gtotal> findgroup(@Param("groupname") String groupname);
    List<Gtotal> listgroup();
    void addgrp(@Param("groupname") String groupname,@Param("groupdesc") String groupdesc,@Param("owner") String owner);
/*
    Users finduser(@Param("username") String username);
    void updateuser(@Param("users") Users users);
    void updatepassword(@Param("username") String username, @Param("password") String password);
    void deleteuser(@Param("username") String username);
    List<Users> list(@Param("username") String username);*/
}
