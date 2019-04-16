package com.example.demo.dao;

import com.example.demo.entity.Gtotal;
import com.example.demo.entity.Message;
import com.example.demo.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    void addmeg(@Param("sender") String sender,@Param("receiver") String receiver,@Param("type") int type,
                @Param("eventid") int eventid,@Param("groupname") String groupname);
    List<Message> findmeg(@Param("receiver") String receiver);
    void upmeg(@Param("messageid") int messageid, @Param("is_accept") int is_accept);

/*
    Users finduser(@Param("username") String username);
    void updateuser(@Param("users") Users users);
    void deleteuser(@Param("username") String username);
    */
}
