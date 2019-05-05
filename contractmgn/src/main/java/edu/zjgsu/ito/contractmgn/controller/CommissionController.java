package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.CommissionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class CommissionController {

    @Autowired
    CommissionService commissionService;

    @GetMapping("commission")
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief"}, logical = Logical.OR)
    public Message getCommissionList(@RequestParam int pageNum, @RequestParam String key,@RequestParam String startTime,@RequestParam String endTime) throws ParseException {
        return commissionService.getCommissionList(pageNum,key,startTime,endTime);
    }

    @PutMapping("commission")
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    public Message confirmCommission(@RequestParam int id,@RequestParam String finishDate,@RequestParam String remark) throws ParseException{
        return commissionService.confirmCommission(id, finishDate, remark);
    }

    @GetMapping("commission/{id}")
    @RequiresRoles(value = {"manager", "accountant", "overallchief"}, logical = Logical.OR)
    public Message getCommissionInfo(@PathVariable(value = "id") int id){
        return commissionService.getCommissionInfo(id);
    }

}
