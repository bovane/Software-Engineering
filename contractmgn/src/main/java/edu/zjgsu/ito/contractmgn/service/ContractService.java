package edu.zjgsu.ito.contractmgn.service;


import edu.zjgsu.ito.contractmgn.model.ContractArchive;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.vo.ContractList;
import edu.zjgsu.ito.contractmgn.vo.ContractVo;
import edu.zjgsu.ito.contractmgn.vo.ShowContract;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface ContractService {
    Message addContract(ContractVo contractVo);//新建一个合同

    String buildContractNumber(int blockId, int contractTypeId, Date creatTime);//生成合同编号

    Message updateContract(ContractVo contractVo);//修改合同

    Message deleteContract(Integer id);//标记删除id

    Message getContractServiceTime(Integer contractId);//获取运维合同服务开始

    ShowContract lookUpContractAll(Integer id);//查询某个id的合同所以信息

    ShowContract lookUpContractNoMoney(Integer id);//查询某个id的合同排除了钱和人的合同

    Message getContractMoney(Integer contractId);//获取合同金额以及未支付金额

    List<Integer> lookUpBlockDuty(Integer id);

    List<Integer> lookUpSaler(Integer id);

    String getArchiveNumber(Integer contractId);

    List<ContractList> getContractList(String key, Integer blockId, Integer typeId, Date start, Date end, Integer sellerId,Integer archiveTag);

    List<String> executeList(Integer id);

    Integer archiveContract(ContractArchive ca);

	Map<String, Object> showArchive(Integer id);

	Integer getBlockById(Integer attribute);

	Integer updateArchive(ContractArchive ca);

    Integer deleteArchive(Integer id);
}
