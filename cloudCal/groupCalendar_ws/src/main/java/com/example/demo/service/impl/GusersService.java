package com.example.demo.service.impl;

import com.example.demo.dao.GusersDao;
import com.example.demo.dao.MessageDao;
import com.example.demo.entity.GUsers;
import com.example.demo.entity.Users;
import com.example.demo.service.IGusersService;
import com.example.demo.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GusersService implements IGusersService {
    @Autowired
    private GusersDao gusersDao;
    //添加用户
    @Override
    public void addgus(int groupid,String groupname,String username) { gusersDao.addgus(groupid,groupname,username); }
    //展示我的群组
    public List<GUsers> mygrp(String username) { return gusersDao.mygrp(username);}
    public List<GUsers> list(String groupname) { return gusersDao.list(groupname);}
    public List<GUsers> find(String groupname,String username) { return gusersDao.find(groupname,username);}
    //count
    public GUsers count(int groupid) { return gusersDao.count(groupid);}
    //修改权限
    @Override
    public void upgus(String groupname,String username,int level) {
        gusersDao.upgus(groupname,username,level);
    }
    //删除
    @Override
    public void del(String groupname,String username) { gusersDao.del(groupname,username);}
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

    */
}
