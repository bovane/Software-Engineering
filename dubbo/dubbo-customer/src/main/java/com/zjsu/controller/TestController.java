package com.zjsu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjsu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    // @Autowired
    @Reference
    private TestService testService;

    @RequestMapping("/test.do")
    public void sayDubbo(){
        testService.test();
    }

    @RequestMapping("/tests.do")
    @ResponseBody
    public String testString(String str){
        String s = testService.testString(str);
        return s;
    }

}