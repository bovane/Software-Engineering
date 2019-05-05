package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.ExecuteNode;
import edu.zjgsu.ito.contractmgn.model.ExecuteNodeExample;
import org.springframework.stereotype.Repository;

/**
 * ExecuteNodeDAO继承基类
 */
@Repository
public interface ExecuteNodeDAO extends MyBatisBaseDao<ExecuteNode, Integer, ExecuteNodeExample> {
}