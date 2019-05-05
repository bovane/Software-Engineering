package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.pojo.Message;

import java.util.Date;

public interface AccountService {
    Message getAcList(Integer contractId,Integer pageNum,Integer record);//根据合同id获得此合同实收款情况列表

    Message getReList(Integer contractId,Integer pageNum,Integer record);//根据合同id获得此合同应收款情况列表

    Message getAccountInfo(Integer pageNum, Integer record, String key, Date start, Date end,Integer userId);//获得收款信息列表

    Message updateContractPeriod();//更新合同所执行的阶段（通过accountId体现）
}
