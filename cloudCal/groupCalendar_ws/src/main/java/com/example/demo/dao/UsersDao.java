package com.example.demo.dao;
/*新增 updateuser方法，传入user对象

* */

import com.example.demo.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao {
    void adduser(@Param("users") Users users);
    Users finduser(@Param("username") String username);
    void updateuser(@Param("users") Users users);
    void updatepassword(@Param("username") String username, @Param("password") String password);
    void deleteuser(@Param("username") String username);
    List<Users> listuser();
    List<Users> list(@Param("username") String username);
}
