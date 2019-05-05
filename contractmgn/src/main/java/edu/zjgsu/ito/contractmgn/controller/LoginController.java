package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.model.User;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("login")
    public Message userLogin(User user,HttpServletResponse response)throws UnsupportedEncodingException {
        return loginService.userLogin(user,response);
    }

    @GetMapping("logout")
    public Message logout(){
        return loginService.logout();
    }

}
