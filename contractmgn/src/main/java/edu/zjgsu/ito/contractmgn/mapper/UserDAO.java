package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.User;
import edu.zjgsu.ito.contractmgn.model.UserExample;
import org.springframework.stereotype.Repository;

/**
 * UserDAO继承基类
 */
@Repository
public interface UserDAO extends MyBatisBaseDao<User, Integer, UserExample> {
}