package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.pojo.Message;

public interface ReminderService {

    Message getReminder(int type,int pageNum);

    Message readReminder(int id);

    void insertReminder(int contractId,int type,String operateType);

    Message countUnreadReminder();

}
