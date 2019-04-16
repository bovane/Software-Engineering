package com.example.demo.entity;

import java.util.Date;

public class Calendar {
    private int eventid;
    private String username;
    private String topic;
    private Date begintime;
    private Date endtime;
    private boolean iscycle;
    private String cycmode;
    private String location;
    private boolean isremind;
    private Date remindtime;
    private String remindway;
    private String notes;

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public boolean isIscycle() {
        return iscycle;
    }

    public void setIscycle(boolean iscycle) {
        this.iscycle = iscycle;
    }

    public String getCycmode() {
        return cycmode;
    }

    public void setCycmode(String cycmode) {
        this.cycmode = cycmode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isIsremind() {
        return isremind;
    }

    public void setIsremind(boolean isremind) {
        this.isremind = isremind;
    }

    public Date getRemindtime() {
        return remindtime;
    }

    public void setRemindtime(Date remindtime) {
        this.remindtime = remindtime;
    }

    public String getRemindway() {
        return remindway;
    }

    public void setRemindway(String remindway) {
        this.remindway = remindway;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
/*    @Override
    public String toString() {
        return "Calendar{" +
                "user='" + user + '\'' +
                ", topic='" + topic + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", isCycle=" + isCycle +
                ", cycMode='" + cycMode + '\'' +
                ", location='" + location + '\'' +
                ", isRemind=" + isRemind +
                ", remindTime=" + remindTime +
                ", remindWay='" + remindWay + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }*/

}
