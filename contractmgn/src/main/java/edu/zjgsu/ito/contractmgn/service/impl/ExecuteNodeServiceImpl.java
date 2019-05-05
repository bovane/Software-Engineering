package edu.zjgsu.ito.contractmgn.service.impl;

import edu.zjgsu.ito.contractmgn.mapper.ExecuteNodeDAO;
import edu.zjgsu.ito.contractmgn.model.ExecuteNode;
import edu.zjgsu.ito.contractmgn.model.ExecuteNodeExample;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ExecuteNodeService;
import edu.zjgsu.ito.contractmgn.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecuteNodeServiceImpl implements ExecuteNodeService {
    @Autowired
    private ExecuteNodeDAO executeNodeDAO;
    @Autowired
    private ReminderService reminderService;

    @Override
    public Message addExecuteNode(ExecuteNode executeNode) {
        executeNodeDAO.insertSelective(executeNode);
        reminderService.insertReminder(executeNode.getContractId(),1,"执行节点更新");
        return Message.createSuc(null);

    }

    @Override
    public Message remindExecuteNode() {
        //执行节点添加后提醒给总经理 财务 总负责人，以上用户登陆时调用此方法，通过查询数据库实现
        return null;
    }

    @Override
    public Message showExecuteNode(Integer id) {
        ExecuteNodeExample executeNodeExample = new ExecuteNodeExample();
        ExecuteNodeExample.Criteria criteria = executeNodeExample.createCriteria();
        criteria.andContractIdEqualTo(id);
        executeNodeExample.setOrderByClause("execute_date");
        List<ExecuteNode> executeNodeList = executeNodeDAO.selectByExample(executeNodeExample);
        return Message.createSuc(executeNodeList);
    }
}
