package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.model.ContractNode;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ContractNodeService {
    Message getContractNodeList(Integer contractId);//根据合同id获取合同节点
    Message updateContractNode(List<ContractNode> contractNodeList);//合同节点更新
}
