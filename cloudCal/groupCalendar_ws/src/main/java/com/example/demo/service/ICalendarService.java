package com.example.demo.service;

import com.example.demo.entity.Calendar;
import com.example.demo.entity.Users;

import java.util.Date;
import java.util.List;

public interface ICalendarService {
    List<Calendar> list(String username);
    List<Calendar> find(int eventid);
    void delete(int eventid);
    void update(Calendar calendar);
    /*int eventid, String topic, Date begintime, Date endtime, String cycmode, String location,
    Date remindtime, String remindway, String notes*/
    void add(String username, String topic, Date begintime, Date endtime, String cycmode, String location, Date remindtime, String remindway, String notes);
    /*void updatepassword(String username,String password);
    Users finduser(String username);
    void banuser(String username,byte status);
    void deleteuser(String username);*/
}
