package com.example.demo.dao;

import com.example.demo.entity.GUsers;
import com.example.demo.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GusersDao {
    void addgus(@Param("groupid") int groupid, @Param("groupname") String groupname,@Param("username") String username);
    List<GUsers> mygrp(@Param("username") String username);
    List<GUsers> find(@Param("groupname") String groupname,@Param("username") String username);
    List<GUsers> list(@Param("groupname") String groupname);
    GUsers count(@Param("groupid") int groupid);
    void upgus(@Param("groupname") String groupname,@Param("username") String username, @Param("level") int level);
    void del(@Param("groupname") String groupname,@Param("username") String username);

/*
    Users finduser(@Param("username") String username);
    void updateuser(@Param("users") Users users);
    void deleteuser(@Param("username") String username);
    */
}
