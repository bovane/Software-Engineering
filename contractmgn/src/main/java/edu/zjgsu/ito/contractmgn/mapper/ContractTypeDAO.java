package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.ContractType;
import edu.zjgsu.ito.contractmgn.model.ContractTypeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * ContractTypeDAO继承基类
 */
@Repository
public interface ContractTypeDAO extends MyBatisBaseDao<ContractType, Integer, ContractTypeExample> {

    @Select("select * from contract_type where id=#{contractTypeId}")
    ContractType selectContractTypeById(@Param("contractTypeId") Integer contractTypeId);
}