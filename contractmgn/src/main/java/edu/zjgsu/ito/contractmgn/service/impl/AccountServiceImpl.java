package edu.zjgsu.ito.contractmgn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zjgsu.ito.contractmgn.mapper.AccountActualDAO;
import edu.zjgsu.ito.contractmgn.mapper.AccountReceivableDAO;
import edu.zjgsu.ito.contractmgn.mapper.ContractDAO;
import edu.zjgsu.ito.contractmgn.model.*;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.AccountService;
import edu.zjgsu.ito.contractmgn.vo.AccountInfo;
import edu.zjgsu.ito.contractmgn.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountReceivableDAO accountReceivableDAO;
    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private AccountActualDAO accountActualDAO;

    @Override
    public Message getAcList(Integer contractId,Integer pageNum,Integer record) {
        AccountActualExample accountActualExample = new AccountActualExample();
        accountActualExample.createCriteria().andContractIdEqualTo(contractId);
        PageHelper.startPage(pageNum,record);
        PageInfo<AccountActual> pageInfo=new PageInfo<>(accountActualDAO.selectByExample(accountActualExample));
//        List<AccountActual> accountActualList = accountActualDAO.selectByExample(accountActualExample);
        JSONArray actualList = new JSONArray();
        if (pageInfo.getList().size() > 0) {
            for (AccountActual accountActual : pageInfo.getList()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("actualDate", simpleDateFormat.format(accountActual.getReceiveDate()));
                jsonObject.put("actualAmount", accountActual.getReceivable());
                jsonObject.put("remark", accountActual.getRemark());
                actualList.add(jsonObject);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("all_record", pageInfo.getTotal());
        result.put("all_page", pageInfo.getPages());
        result.put("current_page", pageNum);
        result.put("data",actualList);
        return Message.createSuc(result);
    }

    @Override
    public Message getReList(Integer contractId, Integer pageNum, Integer record) {
        PageHelper.startPage(pageNum,record);
        PageInfo<AccountVo> pageInfo=new PageInfo<>(accountReceivableDAO.getAccountList(contractId));
        //Map<String, List<AccountVo>> map = new HashMap<>();
//        if (pageInfo.getList().size() > 0) {
//            for (AccountVo accountVo : pageInfo.getList()) {
//                if (map.containsKey(accountVo.getNodeName())) {
//                    map.get(accountVo.getNodeName()).add(accountVo);
//                } else {
//                    List<AccountVo>accountVoLists=new ArrayList<>();
//                    accountVoLists.add(accountVo);
//                    map.put(accountVo.getNodeName(),accountVoLists);
//                }
//            }
//        }
        Map<String, Object> result = new HashMap<>();
        result.put("all_record", pageInfo.getTotal());
        result.put("all_page", pageInfo.getPages());
        result.put("current_page", pageNum);
        result.put("data",pageInfo.getList());
        return Message.createSuc(result);
    }

    @Override
    public Message getAccountInfo(Integer pageNum, Integer record, String key, Date start, Date end,Integer userId) {
        PageHelper.startPage(pageNum, record);
        PageInfo<AccountInfo> pageInfo = new PageInfo<>(accountReceivableDAO.getAccountInfo(key, start, end,userId));
        Map<String, Object> result = new HashMap<>();
        result.put("all_record", pageInfo.getTotal());
        result.put("all_page", pageInfo.getPages());
        result.put("current_page", pageNum);
        result.put("data", pageInfo.getList());
        return Message.createSuc(result);

    }

    @Override
    public Message updateContractPeriod() {
        ContractExample contractExample = new ContractExample();
        contractExample.createCriteria().andAccountIdNotEqualTo(0);
        List<Contract> contractList = contractDAO.selectByExample(contractExample);
        for (Contract contract : contractList) {
            AccountReceivableExample accountReceivableExample = new AccountReceivableExample();
            accountReceivableExample.createCriteria().andContractIdEqualTo(contract.getId()).andStatusNotEqualTo(5);
            accountReceivableExample.setOrderByClause("end_date");
            List<AccountReceivable> accountReceivableList = accountReceivableDAO.selectByExample(accountReceivableExample);
            if (accountReceivableList.size() > 0) {
                contract.setAccountId(accountReceivableList.get(0).getId());
            }
            contractDAO.updateByPrimaryKeySelective(contract);
        }
        return Message.createSuc(null);


    }
}
