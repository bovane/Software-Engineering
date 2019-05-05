package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.model.ExecuteNode;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import org.springframework.stereotype.Service;

@Service
public interface ExecuteNodeService {
     Message addExecuteNode(ExecuteNode executeNode);//添加执行节点

     Message remindExecuteNode();//执行节点提醒

     Message showExecuteNode(Integer id);//执行节点显示
}
