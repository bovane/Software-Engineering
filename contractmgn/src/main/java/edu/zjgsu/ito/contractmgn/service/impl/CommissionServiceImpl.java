package edu.zjgsu.ito.contractmgn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zjgsu.ito.contractmgn.mapper.*;
import edu.zjgsu.ito.contractmgn.model.*;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.CommissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    CommissionDAO commissionDAO;
    @Autowired
    ContractDAO contractDAO;
    @Autowired
    BlockDAO blockDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    AccountActualDAO accountActualDAO;
    @Autowired
    BlockUserDAO blockUserDAO;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Message getCommissionList(int pageNum,String key,String startTime,String endTime) throws ParseException {

        HashMap<String,Object> result = new HashMap<>();
        PageHelper.startPage(pageNum,6);
        
        CommissionExample example = new CommissionExample();
        example.setOrderByClause("status asc,due_date asc");
        CommissionExample.Criteria criteria1 = example.createCriteria();
        CommissionExample.Criteria criteria2 = example.createCriteria();
        if(!startTime.equals("")){
            Date startDate = format.parse(startTime);
            criteria1.andSignDateGreaterThanOrEqualTo(startDate);
            criteria2.andSignDateGreaterThanOrEqualTo(startDate);
        }
        if(!endTime.equals("")){
            Date endDate = format.parse(endTime);
            criteria1.andSignDateLessThanOrEqualTo(endDate);
            criteria2.andSignDateLessThanOrEqualTo(endDate);
        }
        if(!key.equals("")){
            ContractExample contractExample = new ContractExample();
            contractExample.or().andNameLike("%"+key+"%");
            List<Contract> contracts = contractDAO.selectByExample(contractExample);
            List<Integer> contractIdList = new ArrayList<>();
            for(Contract contract : contracts){
                contractIdList.add(contract.getId());
            }
            if(contractIdList.size()!=0) {
                criteria1.andContractIdIn(contractIdList);
            }

            UserExample userExample = new UserExample();
            userExample.or().andRoleEqualTo(5).andNameLike("%"+key+"%");
            List<User> userList = userDAO.selectByExample(userExample);
            List<Integer> salerIdList = new ArrayList<>();
            for(User user : userList){
                salerIdList.add(user.getRole());
            }
            if(salerIdList.size()!=0) {
                criteria2.andSalerIdIn(salerIdList);
            }

            example.or(criteria2);
        }

        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("areachief")){
            BlockUserExample blockUserExample = new BlockUserExample();
            blockUserExample.or().andUserIdEqualTo((int)subject.getSession().getAttribute("userId"));
            List<BlockUser> blockUsers = blockUserDAO.selectByExample(blockUserExample);
            if(blockUsers.size()==1){
                int blockId = blockUsers.get(0).getBlockId();
                ContractExample contractExample = new ContractExample();
                contractExample.or().andBlockEqualTo(blockId);
                List<Contract> contracts = contractDAO.selectByExample(contractExample);
                if(contracts.size()!=0){
                    List<Integer> contractIdList = new ArrayList<>();
                    for(Contract contract : contracts){
                        contractIdList.add(contract.getId());
                    }
                    List<CommissionExample.Criteria> oredCriteria = example.getOredCriteria();
                    for(CommissionExample.Criteria criteria : oredCriteria){
                        criteria.andContractIdIn(contractIdList);
                    }
                }
            }
        }

        JSONArray array = new JSONArray();
        PageInfo<Commission> commissions = new PageInfo<>(commissionDAO.selectByExample(example));
        for(Commission commission : commissions.getList()){
            Contract contract = contractDAO.selectByPrimaryKey(commission.getContractId());
            JSONObject obj = new JSONObject();
            obj.put("id",commission.getId());
            obj.put("number",contract.getNumber());
            obj.put("signDate",format.format(commission.getSignDate()));
            obj.put("contractName",contract.getName());
            obj.put("block",blockDAO.selectByPrimaryKey(contract.getBlock()).getName());
            obj.put("money",commission.getMoney());
            obj.put("saler",userDAO.selectByPrimaryKey(commission.getSalerId()).getName());
            obj.put("status",commission.getStatus());
            obj.put("days",commission.getDays());
            array.add(obj);
        }
        result.put("arr",array);
        result.put("totalRecords",commissions.getTotal());

        return Message.createSuc(result);
    }

    @Override
    public void insertCommission(int contractId, int actualId, int percentage, Date dueDate,int salerId) {
        Commission commission = new Commission();
        commission.setActualId(actualId);
        commission.setContractId(contractId);
        commission.setSignDate(contractDAO.selectByPrimaryKey(contractId).getSignDate());
        commission.setDueDate(dueDate);
        commission.setPercentage(percentage);
        commission.setMoney(accountActualDAO.selectByPrimaryKey(actualId).getReceivable()*percentage/100);
        commission.setSalerId(salerId);
        commissionDAO.insertSelective(commission);
    }

    @Override
    public Message confirmCommission(int id,String finishDate, String remark) throws ParseException {

        Commission commission = commissionDAO.selectByPrimaryKey(id);
        commission.setFinishDate(format.parse(finishDate));
        commission.setStatus(4);
        commission.setRemark(remark);
        commission.setPayTag(true);
        commissionDAO.updateByPrimaryKey(commission);

        return Message.createSuc(null);
    }

    @Override
    public Message getCommissionInfo(int id) {

        Commission commission = commissionDAO.selectByPrimaryKey(id);
        JSONObject obj = new JSONObject();
        obj.put("actualMoney",accountActualDAO.selectByPrimaryKey(commission.getActualId()).getReceivable());
        obj.put("salerName",userDAO.selectByPrimaryKey(commission.getSalerId()).getName());
        obj.put("percentage",commission.getPercentage());
        obj.put("commission",commission.getMoney());

        return Message.createSuc(obj);
    }


}
