package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.model.ExecuteNode;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ExecuteNodeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExecuteNodeController {
    @Autowired
    private ExecuteNodeService executeNodeService;

    /**
     * 执行节点的添加
     *
     * @param executeNode
     * @return Message
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief", "areachief"}, logical = Logical.OR)
    @PostMapping("/executeNode")
    public Message addExecuteNode(@RequestBody ExecuteNode executeNode) {
        return executeNodeService.addExecuteNode(executeNode);
    }

    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @GetMapping("/executeNode")
    public Message remindExecuteNode() {
        return executeNodeService.remindExecuteNode();
    }

    /**
     * 合同执行节点显示
     *
     * @param id 合同id
     * @return Message
     */
    @GetMapping("/executeNode/{id}")
    public Message showExecuteNode(@PathVariable("id") Integer id) {
        return executeNodeService.showExecuteNode(id);
    }
}
