package com.example.demo.entity;

import java.sql.Date;

public class Gtotal {
    private int groupid;
    private String groupname;
    private String owner;
    private String groupdesc;
    private Date createtime;

    public void setGroupdesc(String groupdesc) {
        this.groupdesc = groupdesc;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOwner() {

        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Gtotal{" +
                "groupid=" + groupid +
                ", groupname='" + groupname + '\'' +
                ", groupowner='" + owner + '\'' +
                ", groupdesc='" + groupdesc + '\'' +
                '}';
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupdesc() {
        return groupdesc;
    }

    public void setGroupusers(String groupusers) {
        this.groupdesc = groupusers;
    }
}
