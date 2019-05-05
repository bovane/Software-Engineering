package edu.zjgsu.ito.contractmgn.service;


import edu.zjgsu.ito.contractmgn.model.AccountReceivable;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.vo.OperationNode;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface AccountReceivableService {

    Message addOperationAccountReceivable(OperationNode operationNode) throws ParseException;//添加运维合同应收款

    Message addCommonAccountReceivable(AccountReceivable accountReceivable) throws ParseException;//添加普通合同应收款

    Message getNodeInfo(Integer nodeId);//获取该节点应收款以及剩余应收款及该节点下的收款信息

    Message getOperationReInfo(Integer contractId);//获取运维合同往期记录

    Message deleteCommonAccountRe(Integer accountReId,Integer nodeId);//根据应收款id删除普通合同记录

    Message deleteOperationAccountRe(Integer nodeId);//

    Message updateCommonAccountRe(AccountReceivable accountReceivable);//更新普通合同应收款记录

//    List<AccountActual> getAccountActualByContractIId();

}
