package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.model.User;
import edu.zjgsu.ito.contractmgn.pojo.Message;

public interface UserService {

    Message addStaff(User user);

    Message updateStaff(User user);

    Message getStaffs(Integer role,String departMent,int pageNum);

    Message resetPassword(int userId);

    Message getStaffInfo(int userId);

    Message setPassWordAfterReset(String originPassword,String password);

    Message searchStaffByName(String name,int pageNum);

    Message getSaler();

    Message getAreaChief();

    Message getSalerInContract(int id);

    User getUserByUserName(String username);

    User getUserByPhone(String number);

    User getUserByName(String name);
}