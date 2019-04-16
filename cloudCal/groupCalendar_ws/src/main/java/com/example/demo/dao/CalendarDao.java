package com.example.demo.dao;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Calendar;
import com.example.demo.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface CalendarDao {
    List<Calendar> list(@Param("username") String username);
    List<Calendar> find(@Param("eventid") int eventid);
    void delete(@Param("eventid") int eventid);
    void update(@Param("calendar") Calendar calendar);
    void add(@Param("username") String username,@Param("topic") String topic, @Param("begintime") Date begintime, @Param("endtime") Date endtime,
             @Param("cycmode") String cycmode, @Param("location") String location, @Param("remindtime") Date remindtime,
             @Param("remindway") String remindway, @Param("notes") String notes);
/*    void addCal(@Param("topic") String topic, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
                @Param("isCycle") boolean isCycle, @Param("cycMode") String cycMode, @Param("location") String location,
                @Param("isRemind") boolean isRemind, @Param("remindTime") Date remindTime, @Param("remindWay") String remindWay,
                @Param("notes") String notes);
    void delCal(@Param("eventId") int eventId);
    void updateCal(@Param("topic") String topic, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
                   @Param("isCycle") boolean isCycle, @Param("cycMode") String cycMode, @Param("location") String location,
                   @Param("isRemind") boolean isRemind, @Param("remindTime") Date remindTime, @Param("remindWay") String remindWay,
                   @Param("notes") String notes);
    Calendar findCal(@Param("eventId") int eventId);//没有必要
    Calendar findAll(@Param("user") String user);*/
/*@Param("eventid") int eventid,@Param("topic") String topic, @Param("begintime") Date begintime, @Param("endtime") Date endtime,
    @Param("cycmode") String cycmode, @Param("location") String location, @Param("remindtime") Date remindtime,
    @Param("remindway") String remindway, @Param("notes") String notes*/
}
