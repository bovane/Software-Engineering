package edu.zjgsu.ito.contractmgn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zjgsu.ito.contractmgn.mapper.*;
import edu.zjgsu.ito.contractmgn.model.*;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ReminderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    ReminderDAO reminderDAO;
    @Autowired
    ContractDAO contractDAO;
    @Autowired
    BlockDAO blockDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    BlockUserDAO blockUserDAO;

    @Override
    public Message getReminder(int type, int pageNum) {

        Subject subject = SecurityUtils.getSubject();
        int userId = (int)subject.getSession().getAttribute("userId");
        JSONArray arr = new JSONArray();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        PageHelper.startPage(pageNum,5);
        ReminderExample example = new ReminderExample();
        if(subject.hasRole("areachief")) {
            BlockUserExample blockUserExample = new BlockUserExample();
            blockUserExample.or().andUserIdEqualTo((int) subject.getSession().getAttribute("userId"));
            List<BlockUser> blockUsers = blockUserDAO.selectByExample(blockUserExample);
            if (blockUsers.size() == 1) {
                int blockId = blockUsers.get(0).getBlockId();
                ContractExample contractExample = new ContractExample();
                contractExample.or().andBlockEqualTo(blockId);
                List<Contract> contracts = contractDAO.selectByExample(contractExample);
                if (contracts.size() != 0) {
                    List<Integer> contractIdList = new ArrayList<>();
                    for (Contract contract : contracts) {
                        contractIdList.add(contract.getId());
                    }
                    example.or().andContractIdIn(contractIdList).andTypeEqualTo(type);
                }
            }
        }else {
            example.or().andTypeEqualTo(type);
        }
        if (subject.hasRole("manager")) {
            example.setOrderByClause("manager asc,operate_time desc");
        }
        if (subject.hasRole("accountant")) {
            example.setOrderByClause("accountant asc,operate_time desc");
        }
        if (subject.hasRole("overallchief")) {
            example.setOrderByClause("overallchief asc,operate_time desc");
        }
        if (subject.hasRole("areachief")) {
            example.setOrderByClause("areachief asc,operate_time desc");
        }
        if (subject.hasRole("buyer")) {
            example.setOrderByClause("buyer asc,operate_time desc");
        }
        List<Reminder> messages = reminderDAO.selectByExample(example);
        PageInfo<Reminder> messageList = new PageInfo<>(messages);
        List<Reminder> list = messageList.getList();
        for(Reminder reminder : list){
            JSONObject obj = new JSONObject();
            Contract contract = contractDAO.selectByPrimaryKey(reminder.getContractId());
            if(contract!=null) {
                obj.put("id", reminder.getId());
                obj.put("number", contract.getNumber());
                obj.put("name", contract.getName());
                obj.put("contract_id", contract.getId());
                obj.put("tag", contract.getOperationTag());
                obj.put("block", blockDAO.selectByPrimaryKey(contract.getBlock()).getName());
                obj.put("updateType", reminder.getOperateType());
                obj.put("operater", userDAO.selectByPrimaryKey(reminder.getOperator()).getName());
                obj.put("operaterName", userDAO.selectByPrimaryKey(userId).getName());
                obj.put("operaterTime", format.format(reminder.getOperateTime()));
                if (subject.hasRole("manager")) {
                    obj.put("read", reminder.getManager());
                }
                if (subject.hasRole("accountant")) {
                    obj.put("read", reminder.getAccountant());
                }
                if (subject.hasRole("overallchief")) {
                    obj.put("read", reminder.getOverallchief());
                }
                if (subject.hasRole("areachief")) {
                    obj.put("read", reminder.getAreachief());
                }
                if (subject.hasRole("buyer")) {
                    obj.put("read", reminder.getBuyer());
                }
                arr.add(obj);
            }
        }

        HashMap<String,Object> result = new HashMap<>();
        result.put("arr",arr);
        result.put("totalRecord",messageList.getTotal());

        return Message.createSuc(result);
    }

    @Override
    public Message readReminder(int id) {

        Subject subject = SecurityUtils.getSubject();

        Reminder reminder = reminderDAO.selectByPrimaryKey(id);
        if (subject.hasRole("manager")) {
            reminder.setManager(true);
        }
        if (subject.hasRole("accountant")) {
            reminder.setAccountant(true);
        }
        if (subject.hasRole("overallchief")) {
            reminder.setOverallchief(true);
        }
        if(subject.hasRole("areachief")){
            reminder.setAreachief(true);
        }
        if(subject.hasRole("buyer")){
            reminder.setBuyer(true);
        }
        reminderDAO.updateByPrimaryKey(reminder);

        return Message.createSuc(null);
    }

    @Override
    public void insertReminder(int contractId, int type, String operateType) {

        Reminder reminder = new Reminder();

        reminder.setContractId(contractId);
        reminder.setOperateTime(new Date());
        reminder.setOperateType(operateType);
        reminder.setOperator((int)SecurityUtils.getSubject().getSession().getAttribute("userId"));
        reminder.setType(type);

        reminderDAO.insertSelective(reminder);

    }

    @Override
    public Message countUnreadReminder() {

        int unread = 0;

        Subject subject = SecurityUtils.getSubject();
        ReminderExample example = new ReminderExample();
        if(subject.hasRole("manager")){
            example.or().andManagerEqualTo(false);
            unread = (int)reminderDAO.countByExample(example);
        }
        if(subject.hasRole("accountant")){
            example.or().andAccountantEqualTo(false);
            unread = (int)reminderDAO.countByExample(example);
        }
        if(subject.hasRole("overallchief")){
            example.or().andOverallchiefEqualTo(false);
            unread = (int)reminderDAO.countByExample(example);
        }

        JSONObject object = new JSONObject();
        object.put("count",unread);

        return Message.createSuc(object);
    }


}
