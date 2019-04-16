package com.example.demo.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class BaseMailUtil {
    /*发送验证重置密码邮件的工具类，输入为：email和code*/
    public void sendEmail(String email,String code) throws EmailException {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName("smtp.163.com");
        htmlEmail.setCharset("UTF-8");
        htmlEmail.addTo(email);
        htmlEmail.setFrom("xiuvaneliu@163.com","验证码邮件");
        htmlEmail.setAuthentication("xiuvaneliu@163.com","123aaa");
        htmlEmail.setSubject("这是一封验证码邮件");
        htmlEmail.setMsg("你正在重置密码，你的验证码是："+code+"。若非本人操作，请忽略本邮件。");
        htmlEmail.send();

    }

}
