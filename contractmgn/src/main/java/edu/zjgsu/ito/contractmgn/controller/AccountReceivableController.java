package edu.zjgsu.ito.contractmgn.controller;


import edu.zjgsu.ito.contractmgn.model.AccountReceivable;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.AccountReceivableService;
import edu.zjgsu.ito.contractmgn.service.ContractService;
import edu.zjgsu.ito.contractmgn.vo.OperationNode;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class AccountReceivableController {
    @Autowired
    private AccountReceivableService accountReceivableService;
    @Autowired
    private ContractService contractService;


    /**
     * 运维合同应收款期数添加
     *
     * @param operationNode
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("运维合同应收款期数添加")
    @PostMapping("operationAccountRe")
    public Message addOperationAccountRe(@RequestBody OperationNode operationNode) throws ParseException {
        return accountReceivableService.addOperationAccountReceivable(operationNode);
    }

    /**
     * 普通合同应收款期数添加
     *
     * @param accountReceivable
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("普通合同应收款期数添加")
    @PostMapping("commonAccountRe")
    public Message addCommonAccountRe(@RequestBody AccountReceivable accountReceivable) throws ParseException {
        return accountReceivableService.addCommonAccountReceivable(accountReceivable);
    }

    /**
     * 普通合同应收款期数删除
     *
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("普通合同应收款期数删除")
    @DeleteMapping("commonAccountRe")
    public Message deleteCommonAccountRe(@RequestParam("accountReId") Integer accountReId, @RequestParam("nodeId") Integer nodeId) {
        return accountReceivableService.deleteCommonAccountRe(accountReId, nodeId);
    }

    /**
     * 运维合同应收款期数删除
     *
     * @param nodeId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("运维合同应收款期数删除")
    @DeleteMapping("operationAccountRe")
    public Message deleteOperationAccountRe(@RequestParam("nodeId") Integer nodeId) {
        return accountReceivableService.deleteOperationAccountRe(nodeId);
    }

    /**
     * 普通合同应收款更新
     *
     * @param accountReceivable
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("普通合同应收款更新")
    @PutMapping("commonAccountRe")
    public Message updateCommonAccountRe(@RequestBody AccountReceivable accountReceivable) {
        return accountReceivableService.updateCommonAccountRe(accountReceivable);
    }

    /**
     * 获取普通合同总金额和应收款剩余金额(
     *
     * @param contractId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief"}, logical = Logical.OR)
    @ApiOperation("获取普通合同总金额和应收款剩余金额及节点信息")
    @GetMapping("/contract/money")
    public Message getContractMoney(@RequestParam("contractId") Integer contractId) {
        return contractService.getContractMoney(contractId);
    }

    /**
     * 根据节点id获得该节点金额信息以及应收款记录
     *
     * @param nodeId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief"}, logical = Logical.OR)
    @ApiOperation("获取该合同节点应收款以及剩余应收款")
    @GetMapping("contractNode/{nodeId}")
    public Message getNodeMoney(@PathVariable Integer nodeId) {
        return accountReceivableService.getNodeInfo(nodeId);
    }

    /**
     * 获取运维合同往期及合同总金额、剩余应收款金额
     *
     * @param contractId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief"}, logical = Logical.OR)
    @ApiOperation("获取运维合同往期应收款记录")
    @GetMapping("operationAccountRe")
    public Message getOperationReInfo(@RequestParam("contractId") Integer contractId) {
        return accountReceivableService.getOperationReInfo(contractId);
    }

}
