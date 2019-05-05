package edu.zjgsu.ito.contractmgn.service.impl;

import edu.zjgsu.ito.contractmgn.mapper.ContractTypeDAO;
import edu.zjgsu.ito.contractmgn.model.ContractType;
import edu.zjgsu.ito.contractmgn.model.ContractTypeExample;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ContractTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeServiceImpl implements ContractTypeService {

    @Autowired
    ContractTypeDAO contractTypeDAO;

    @Override
    public Message addContractType(String typeName) {

        ContractTypeExample example = new ContractTypeExample();
        example.or().andNameEqualTo(typeName);
        List<ContractType> contractTypes = contractTypeDAO.selectByExample(example);
        if(contractTypes.size()!=0){
            return Message.createErr(501,"duplicate name");
        }

        ContractType contractType = new ContractType();
        contractType.setName(typeName);
        contractTypeDAO.insert(contractType);
        int id = contractType.getId();

        if(id>25){
            return Message.createErr(502,"too much type");
        }
        char tag = (char)(id+64);
        contractType.setId(id);
        contractType.setTag(String.valueOf(tag));
        contractTypeDAO.updateByPrimaryKey(contractType);

        return Message.createSuc(null);
    }

    @Override
    public Message getContractType() {
        return Message.createSuc(contractTypeDAO.selectByExample(new ContractTypeExample()));
    }

    @Override
    public Message renameContractType(int id, String typeName) {

        ContractTypeExample example = new ContractTypeExample();
        example.or().andNameEqualTo(typeName);
        List<ContractType> contractTypes = contractTypeDAO.selectByExample(example);
        if(contractTypes.size()!=0){
            return Message.createErr(501,"duplicate name");
        }

        ContractType contractType = contractTypeDAO.selectByPrimaryKey(id);
        if(contractType!=null){
            if(contractType.getName().equals("运维技术服务")||contractType.getName().equals("环保工程")){
                return Message.createErr(501,"cant change special type name");
            }
            contractType.setName(typeName);
            contractTypeDAO.updateByPrimaryKey(contractType);
        }

        return Message.createSuc(null);
    }
}
