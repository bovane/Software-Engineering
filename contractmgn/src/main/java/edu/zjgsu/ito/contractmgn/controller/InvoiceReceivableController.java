package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.model.InvoiceReceivable;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.InvoiceReceivableService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceReceivableController {
    @Autowired
    private InvoiceReceivableService invoiceReceivableService;

    /**
     * 获取合同总金额和应开票剩余金额
     *
     * @param contractId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("获取合同总金额和应开票剩余金额")
    @GetMapping("invoiceReceivable")
    public Message getInvoiceMoney(@RequestParam("contractId") Integer contractId) {
        return invoiceReceivableService.getInvoiceReInfo(contractId);

    }

    /**
     * 添加应开票
     *
     * @param invoiceReceivable
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("添加应开票")
    @PostMapping("invoiceReceivable")
    public Message addInvoiceRe(@RequestBody InvoiceReceivable invoiceReceivable) {
        return invoiceReceivableService.addInvoiceReceivable(invoiceReceivable);
    }

    /**
     * 删除应开票
     *
     * @param invoiceId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("删除应开票")
    @DeleteMapping("invoiceReceivable")
    public Message deleteInvoiceRe(@RequestParam("invoiceReId") Integer invoiceId) {
        return invoiceReceivableService.deleteInvoiceRe(invoiceId);
    }

    /**
     * 更新应开票
     * @param invoiceReceivable
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    @ApiOperation("更新应开票")
    @PutMapping("invoiceReceivable")
    public Message updateInvoiceRe(@RequestBody InvoiceReceivable invoiceReceivable) {
        return invoiceReceivableService.updateInvoiceRe(invoiceReceivable);
    }


}
