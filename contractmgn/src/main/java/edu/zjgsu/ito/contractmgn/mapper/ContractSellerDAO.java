package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.ContractSeller;
import edu.zjgsu.ito.contractmgn.model.ContractSellerExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ContractSellerDAO继承基类
 */
@Repository
public interface ContractSellerDAO extends MyBatisBaseDao<ContractSeller, Integer, ContractSellerExample> {
    @Delete("delete from contract_seller where contract_id=#{contractId}")
    void deleteSellerByContractId(@Param("contractId")Integer contractId);
}