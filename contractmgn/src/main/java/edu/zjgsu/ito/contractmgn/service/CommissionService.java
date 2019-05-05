package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.pojo.Message;

import java.text.ParseException;
import java.util.Date;

public interface CommissionService {

    Message getCommissionList(int pageNum,String key,String startTime,String endTime) throws ParseException;

    void insertCommission(int contractId, int actualId, int percentage, Date dueDate,int salerId);

    Message confirmCommission(int id,String finishDate,String remark) throws ParseException;

    Message getCommissionInfo(int id);
}
