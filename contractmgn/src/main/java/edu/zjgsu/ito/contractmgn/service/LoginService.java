package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.model.User;
import edu.zjgsu.ito.contractmgn.pojo.Message;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface LoginService {

    Message userLogin(User user,HttpServletResponse response) throws UnsupportedEncodingException;

    Message logout();
}
