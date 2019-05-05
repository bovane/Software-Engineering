package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.pojo.Message;

public interface ContractTypeService {

    Message addContractType(String name);

    Message getContractType();

    Message renameContractType(int id,String name);
}
