package edu.zjgsu.ito.contractmgn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.contractmgn.mapper.AccountReceivableDAO;
import edu.zjgsu.ito.contractmgn.mapper.ContractNodeDAO;
import edu.zjgsu.ito.contractmgn.model.ContractNode;
import edu.zjgsu.ito.contractmgn.model.ContractNodeExample;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ContractNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractNodeServiceImpl implements ContractNodeService {
    @Autowired
    private ContractNodeDAO contractNodeDAO;
    @Autowired
    private AccountReceivableDAO accountReceivableDAO;

    @Override
    public Message getContractNodeList(Integer contractId) {
        ContractNodeExample contractNodeExample = new ContractNodeExample();
        contractNodeExample.createCriteria().andContractIdEqualTo(contractId);
        List<ContractNode> contractNodeList = contractNodeDAO.selectByExample(contractNodeExample);//获得合同节点
//        AccountActualExample accountActualExample=new AccountActualExample();
//        accountActualExample.createCriteria().andContractIdEqualTo(contractId);
//        List<AccountActual> accountActualList=accountActualDAO.selectByExample(accountActualExample);
//        if(accountActualList.size()>0){
//
//        }
        Map<String, Object> map = new HashMap<>();
        JSONArray array = new JSONArray();

        for (ContractNode contractNode : contractNodeList) {
            Double moneyActual = accountReceivableDAO.countMoneyByNodeId(contractNode.getId());
            if (moneyActual == null)
                moneyActual = 0.0;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nodeId", contractNode.getId());
            jsonObject.put("name", contractNode.getName());
            jsonObject.put("money", contractNode.getMoney());
            jsonObject.put("restMoney", contractNode.getMoney() - moneyActual);
            array.add(jsonObject);
        }
        map.put("nodeList", array);
        return Message.createSuc(map);
    }

    @Transactional
    @Override
    public Message updateContractNode(List<ContractNode> contractNodeList) {
        for (ContractNode contractNode : contractNodeList) {
            try {
                contractNodeDAO.updateByPrimaryKeySelective(contractNode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Message.createSuc(null);
    }
}
