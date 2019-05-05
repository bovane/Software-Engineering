package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.AccountActual;
import edu.zjgsu.ito.contractmgn.model.AccountActualExample;
import org.springframework.stereotype.Repository;

/**
 * AccountActualDAO继承基类
 */
@Repository
public interface AccountActualDAO extends MyBatisBaseDao<AccountActual, Integer, AccountActualExample> {
}