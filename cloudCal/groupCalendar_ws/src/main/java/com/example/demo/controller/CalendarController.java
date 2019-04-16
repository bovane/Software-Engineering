package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Calendar;
import com.example.demo.entity.Users;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping(value = "/calendar")
public class CalendarController {
    @Autowired
    private ICalendarService iCalendarService;
    //展示用户列表
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<Calendar> list(@RequestParam String username) { return iCalendarService.list(username); }
    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public List<Calendar> find(@RequestParam int eventid) { return iCalendarService.find(eventid); }
    //删除用户
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(@RequestParam int eventid) { iCalendarService.delete(eventid); }
    //修改
    /*@RequestParam int eventid,@RequestParam String topic,@RequestParam String begintime,
        @RequestParam String endtime,@RequestParam String cycmode,@RequestParam String location,
        @RequestParam String remindtime,@RequestParam String remindway,@RequestParam String notes*/
    @RequestMapping(value = "/update",method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void update(@RequestBody Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //eventid,topic,sdf.parse(begintime),sdf.parse(endtime),cycmode,location,sdf.parse(remindtime),remindway,notes
        //calendar.setBegintime(sdf.parse(calendar.getBegintime()));
        iCalendarService.update(calendar); }
    //添加
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void add(@RequestParam String username,@RequestParam String topic,@RequestParam String begintime,
                    @RequestParam String endtime,@RequestParam String cycmode,@RequestParam String location,
                    @RequestParam String remindtime,@RequestParam String remindway,@RequestParam String notes) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(begintime);
        iCalendarService.add(username,topic,sdf.parse(begintime),sdf.parse(endtime),cycmode,location,sdf.parse(remindtime),remindway,notes); }
   /* public void addOne(String user,String  topic,Date beginTime,Date endTime,boolean isCycle,String cycMode,
                           String location,boolean isRemind,Date remindTime,String remindWay,String notes){
    iAdminService.addCal(user,topic,beginTime,endTime,isCycle,cycMode, location,isRemind,remindTime,remindWay,notes);
    }//为用户添加一个日历计划

    public Calendar findAll(String user){ return iAdminService.findAll(user); }//查找单个用户的所有日历

    public void delCal(int eventId){ iAdminService.delCal(eventId); }//删除一个日历计划

    public void updateCal(String user,String  topic,Date beginTime,Date endTime,boolean isCycle,String cycMode,
                          String location,boolean isRemind,Date remindTime,String remindWay,String notes){
        iAdminService.updateCal(user,topic,beginTime,endTime,isCycle,cycMode, location,isRemind,remindTime,remindWay,notes);
    }*/
}
