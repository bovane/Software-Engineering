package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.BlockUser;
import edu.zjgsu.ito.contractmgn.model.BlockUserExample;
import org.springframework.stereotype.Repository;

/**
 * BlockUserDAO继承基类
 */
@Repository
public interface BlockUserDAO extends MyBatisBaseDao<BlockUser, Integer, BlockUserExample> {
}