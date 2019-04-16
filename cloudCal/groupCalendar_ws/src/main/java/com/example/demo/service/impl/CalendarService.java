package com.example.demo.service.impl;

import com.example.demo.dao.CalendarDao;
import com.example.demo.dao.UsersDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Calendar;
import com.example.demo.entity.Users;
import com.example.demo.service.ICalendarService;
import com.example.demo.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class CalendarService implements ICalendarService {
    @Autowired
    private CalendarDao calendarDao;
    //展示信息
    @Override
    public List<Calendar> list(String username) { return calendarDao.list(username); }
    @Override
    public List<Calendar> find(int eventid) { return calendarDao.find(eventid); }
    //删除
    @Override
    public void delete(int eventid) { calendarDao.delete(eventid);}
    //修改
    /*int eventid, String topic, Date begintime, Date endtime, String cycmode, String location,
    Date remindtime, String remindway, String notes*/
    //eventid,topic,begintime,endtime,cycmode,location,remindtime,remindway,notes
    @Override
    public void update(Calendar calendar) { calendarDao.update(calendar); }
    //添加用户
    @Override
    public void add(String username, String topic, Date begintime, Date endtime, String cycmode, String location, Date remindtime, String remindway, String notes) { calendarDao.add(username,topic,begintime,endtime,cycmode,location,remindtime,remindway,notes); }
/*
    //通过用户名找用户
    @Override
    public Users finduser(String username) {
        return usersDao.finduser(username);
    }*/
    //展示用户列表

    /*
    //修改密码
    @Override
    public void updatemobile(String username,String mobile) { usersDao.updatepassword(username,mobile); }
    //禁用用户
    @Override
    public void banuser(String username,byte status) { usersDao.banuser(username,status);}
    //删除用户
    @Override
    public void deleteuser(String username) { usersDao.deleteuser(username);}*/
}
