package edu.zjgsu.ito.contractmgn.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.zjgsu.ito.contractmgn.model.Financial;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.IFinancialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * FinancialController
 */
@RestController
@RequestMapping("/finance")
@Api(description = "财务相关操作")
public class FinancialController {
    @Autowired
    private IFinancialService financialService;

    @ApiOperation("显示合同总额和剩余的钱")
    @GetMapping("money")
    public Message showMoney(@RequestParam("contract_id") Integer id) {
        Financial fc = financialService.showMoney(id);
        return fc == null ? Message.createErr(-1, "改合同没有财务信息") : Message.createSuc(fc);
    }

    @ApiOperation("测试response")
    @PostMapping(value = "get_response")
    public String postMethodName(String entity, HttpServletResponse response) {
        System.out.println(response.getHeaderNames());
        return entity;
    }

}