package edu.zjgsu.ito.contractmgn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.contractmgn.mapper.ContractDAO;
import edu.zjgsu.ito.contractmgn.mapper.InvoiceReceivableDAO;
import edu.zjgsu.ito.contractmgn.model.InvoiceReceivable;
import edu.zjgsu.ito.contractmgn.model.InvoiceReceivableExample;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.pojo.StatueCode;
import edu.zjgsu.ito.contractmgn.service.InvoiceReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceReceivableServiceImpl implements InvoiceReceivableService {
    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private InvoiceReceivableDAO invoiceReceivableDAO;

    @Override
    public Message getInvoiceReInfo(Integer contractId) {
        Double amount = contractDAO.getMoney(contractId);
        Double invoiceMoney = invoiceReceivableDAO.countMoneyByContractId(contractId);//合同剩余应开票金额
        if (invoiceMoney == null)
            invoiceMoney = 0.0;
        Map<String, Object> map = new HashMap<>();
        InvoiceReceivableExample invoiceReceivableExample=new InvoiceReceivableExample();
        invoiceReceivableExample.createCriteria().andContractIdEqualTo(contractId);
        invoiceReceivableExample.setOrderByClause("date");
        List<InvoiceReceivable>invoiceReceivableList=invoiceReceivableDAO.selectByExample(invoiceReceivableExample);
        JSONArray jsonArray=new JSONArray();
        for(InvoiceReceivable invoiceReceivable:invoiceReceivableList){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("invoiceReId",invoiceReceivable.getId());
            jsonObject.put("invoice_money",invoiceReceivable.getInvoiceMoney());
            jsonObject.put("date",invoiceReceivable.getDate());
            jsonObject.put("remark",invoiceReceivable.getRemark());
            jsonArray.add(jsonObject);
        }
        map.put("contractAmount", amount);
        map.put("resetInvoiceMoney", amount - invoiceMoney);
        map.put("invoiceList",jsonArray);
        return Message.createSuc(map);
    }

    @Override
    public Message addInvoiceReceivable(InvoiceReceivable invoiceReceivable) {
//        if(invoiceReceivable.getDate().before(new Date())){
//            return Message.createErr(StatueCode.DATE_MISTAKE,"所选日期不能早于今天");
//        }
        Byte unstart=0;
        InvoiceReceivableExample invoiceReceivableExample = new InvoiceReceivableExample();
        invoiceReceivableExample.createCriteria().andContractIdEqualTo(invoiceReceivable.getContractId()).andDateGreaterThan(invoiceReceivable.getDate()).andCompleteTagNotEqualTo(unstart);
        List<InvoiceReceivable> invoiceReceivableList = invoiceReceivableDAO.selectByExample(invoiceReceivableExample);
        if (invoiceReceivableList.size() > 0) {//控制开票日期不能早于已开始或完成的期数时间
            return Message.createErr(StatueCode.DATE_MISTAKE, "所选日期早于应开票进行中日期");
        }
        Integer issue=invoiceReceivableDAO.getInvoiceReNum(invoiceReceivable.getContractId());//获取已有期数
        invoiceReceivable.setIssueId(issue+1);//期数加一
        invoiceReceivableDAO.insertSelective(invoiceReceivable);
        return Message.createSuc(null);
    }

    @Override
    public Message deleteInvoiceRe(Integer invoiceReId) {
        Byte completeTag=invoiceReceivableDAO.selectByPrimaryKey(invoiceReId).getCompleteTag();
        if(completeTag!=0){
            return Message.createErr(StatueCode.DELETE_FAILURE,"本期开票已开始，无法删除");
        }
        invoiceReceivableDAO.deleteByPrimaryKey(invoiceReId);
        return Message.createSuc(null);
    }

    @Override
    public Message updateInvoiceRe(InvoiceReceivable invoiceReceivable) {
        Byte completeTag=invoiceReceivableDAO.selectByPrimaryKey(invoiceReceivable.getId()).getCompleteTag();
        if(completeTag!=0){
            return Message.createErr(511,"本期开票已开始，无法修改");
        }
        Byte unstart=0;
        InvoiceReceivableExample invoiceReceivableExample = new InvoiceReceivableExample();
        invoiceReceivableExample.createCriteria().andContractIdEqualTo(invoiceReceivable.getContractId()).andDateGreaterThan(invoiceReceivable.getDate()).andCompleteTagNotEqualTo(unstart);
        List<InvoiceReceivable> invoiceReceivableList = invoiceReceivableDAO.selectByExample(invoiceReceivableExample);
        if (invoiceReceivableList.size() > 0) {//控制开票日期不能早于已开始或完成的期数时间
            return Message.createErr(StatueCode.DATE_MISTAKE, "修改的日期早于应开票进行中日期");
        }
        invoiceReceivableDAO.updateByPrimaryKeySelective(invoiceReceivable);
        return Message.createSuc(null);
    }
}
