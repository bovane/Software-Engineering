package com.zjsu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String index( ) {
        return "login";
    }

    @RequestMapping(value = "/jsptest.do", method = RequestMethod.GET)
    public String jsptest() {
        return "test";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
