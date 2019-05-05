package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.Reminder;
import edu.zjgsu.ito.contractmgn.model.ReminderExample;
import org.springframework.stereotype.Repository;

/**
 * ReminderDAO继承基类
 */
@Repository
public interface ReminderDAO extends MyBatisBaseDao<Reminder, Integer, ReminderExample> {
}