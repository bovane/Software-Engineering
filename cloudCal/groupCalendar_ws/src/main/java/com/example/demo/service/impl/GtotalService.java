package com.example.demo.service.impl;

import com.example.demo.dao.GtotalDao;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GtotalService implements IGtotalService {
    @Autowired
    private GtotalDao gtotalDao;
    //展示用户信息
    public List<Gtotal> findgroup(String groupname) { return gtotalDao.findgroup(groupname);}
    public List<Gtotal> listgroup() { return gtotalDao.listgroup();}
    //添加群组
    @Override
    public void addgrp(String groupname,String groupdesc,String owner) { gtotalDao.addgrp(groupname,groupdesc,owner); }
/*
    //通过用户名找用户
    @Override
    public Users finduser(String username) {
        return usersDao.finduser(username);
    }
    // 更新用户资料
    @Override
    public void updateuser(Users users) {
        usersDao.updateuser(users);
    }
    //修改密码
    @Override
    public void updatepassword(String username,String password) {

        usersDao.updatepassword(username,password);
    }
    // 删除用户
    @Override
    public void deleteuser(String username) {
        usersDao.deleteuser(username);
    }
    //发送邮件
    @Override
    public boolean forgetPWD(String email) {
        //生成激活码
        String code = CodeUtil.generateUniqueCode();
        Jedis jedis = BaseRedis.getJedis();
        jedis.set(email,code);
        jedis.expire(email,60*5); // 设置过期时间，五分钟
        BaseRedis.returnResource(jedis);
        BaseMailUtil baseMailUtil = new BaseMailUtil();
        try {
            baseMailUtil.sendEmail(email,code);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return true;
    }
    //验证验证码
    public boolean vertifyCode(String email,String code) {
        Jedis jedis = BaseRedis.getJedis();
        if(code.compareTo(jedis.get(email))==0) {
            System.out.println("匹配一致");
            return true;
        }
        System.out.println(jedis.get(email));
        return false;
    }

    //展示用户信息(个人)
    public List<Users> list(String username) { return usersDao.list(username);}*/
}
