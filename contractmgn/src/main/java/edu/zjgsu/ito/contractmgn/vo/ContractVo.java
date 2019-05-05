package edu.zjgsu.ito.contractmgn.vo;

import edu.zjgsu.ito.contractmgn.model.Contract;
import edu.zjgsu.ito.contractmgn.model.ContractNode;
import edu.zjgsu.ito.contractmgn.model.ContractSeller;
import java.util.List;

public class ContractVo extends Contract{


    private static final long serialVersionUID = 1L;

    private List<ContractNode> contractNodeList;

    private List<ContractSeller> contractSellerList;


    public List<ContractSeller> getContractSellerList() {
        return contractSellerList;
    }

    public void setContractSellerList(List<ContractSeller> contractSellerList) {
        this.contractSellerList = contractSellerList;
    }

    public List<ContractNode> getContractNodeList() {
        return contractNodeList;
    }

    public void setContractNodeList(List<ContractNode> contractNodeList) {
        this.contractNodeList = contractNodeList;
    }
}
