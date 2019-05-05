package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 收款信息
     *
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief"}, logical = Logical.OR)
    @ApiOperation("所有合同财务进行状况")
    @GetMapping("accounts")
    public Message accountInfo(@RequestParam("pageNum") Integer pageNum, @RequestParam("record") Integer record,
                               @RequestParam(value = "key",required = false)String key,
                               @RequestParam(value = "startDate" ,required = false)String startDate ,
                               @RequestParam(value = "endDate" ,required = false)String endDate ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null,end = null;
        try {
            if(!startDate.equals(""))
                start = sdf.parse(startDate);
            if(!endDate.equals(""))
                end = sdf.parse(endDate);
        }catch (Exception e)
        {
            return Message.createErr(-1,"日期格式不对");
        }
        accountService.updateContractPeriod();//查看合同收款信息前，先维护一下合同表的account_id字段（合同收钱到了哪一期）
        Subject subject = SecurityUtils.getSubject();
        Integer userId=null;
        if(subject.hasRole("areachief")){
             userId = (Integer) subject.getSession().getAttribute("userId");
        }
        return accountService.getAccountInfo(pageNum,record,key,start,end,userId);
    }

    /**
     * 根据合同id获得该合同实收款信息
     *
     * @param contractId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief"}, logical = Logical.OR)
    @ApiOperation("单个合同财务实收款情况信息")
    @GetMapping("accounts/actual/{contractId}")
    public Message getActualList(@PathVariable("contractId") Integer contractId,@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("record")Integer record) {
        return accountService.getAcList(contractId,pageNum,record);
    }

    /**
     * 根据合同id获得该合同应收款信息
     *
     * @param contractId
     * @return
     */
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief"}, logical = Logical.OR)
    @ApiOperation("单个合同财务实收款情况信息")
    @GetMapping("accounts/receivable/{contractId}")
    public Message getReceivableList(@PathVariable("contractId") Integer contractId,@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("record")Integer record) {
        return accountService.getReList(contractId,pageNum,record);
    }
}
