package edu.zjgsu.ito.contractmgn.config;

import edu.zjgsu.ito.contractmgn.pojo.Message;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Message handleException(Exception e) {
        e.printStackTrace();
        return Message.createErr(500,"服务器出错");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Message handleAuthorizationException(AuthorizationException e){
        e.printStackTrace();
        return Message.createErr(403,"没有权限");
    }
}
