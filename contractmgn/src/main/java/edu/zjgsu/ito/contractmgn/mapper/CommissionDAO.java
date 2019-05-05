package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.Commission;
import edu.zjgsu.ito.contractmgn.model.CommissionExample;
import org.springframework.stereotype.Repository;

/**
 * CommissionDAO继承基类
 */
@Repository
public interface CommissionDAO extends MyBatisBaseDao<Commission, Integer, CommissionExample> {
    
}