package edu.zjgsu.ito.contractmgn.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.zjgsu.ito.contractmgn.model.ActuralReceiptList;
import edu.zjgsu.ito.contractmgn.model.ActuralTicket;
import edu.zjgsu.ito.contractmgn.model.ReceiptModel;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ContractService;
import edu.zjgsu.ito.contractmgn.service.IActuralReceiptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ActuralReceiptController
 */
@RestController
@Api(description = "实收款操作")
@RequestMapping("/api/v1/actural")
public class ActuralReceiptController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private IActuralReceiptService acturalReceiptService;

    @GetMapping("get_receipt_list")
    @ApiOperation("查询实收款列表和合同总额")
    @RequiresRoles(value = {"manager","accountant","allchief","areachief"},logical = Logical.OR)
    public Message getReceiptList(@RequestParam("contract_id") Integer id) {
        ActuralReceiptList list = acturalReceiptService.getReceiptList(id);
        Message message;
        if (list.getTotalAmount() == null)
            message = Message.createErr(-1, "该项目不存在");
        else
            message = Message.createSuc(list);
        return message;
    }

    @RequiresRoles(value = { "manager", "accountant" }, logical = Logical.OR)
    @ApiOperation("添加一个实收款")
    @PostMapping("add_receipt")
    public Message addReceipt(@RequestBody ReceiptModel model) {
        Message message = null;
        Double plan = acturalReceiptService.isPlan(model.getContractId());
        if(plan == null)
        {
            message = Message.createErr(-1, "你还未添加应收款");
        }
        else if(plan < model.getReceipt())
        {
            message = Message.createErr(-1, "你还实收款大于了应收款");
        }
       else if (model.getReceipt() < 0) {
            message = Message.createErr(-1, "你的实收款小于0");
        } else {

            Integer result = acturalReceiptService.insertReceipt(model);
            message = Message.createSuc(result);

        }

        return message;

    }

    @RequiresRoles(value = { "manager", "accountant", "allchief", "areachief" }, logical = Logical.OR)
    @ApiOperation("查询实收款开票")
    @GetMapping("get_actural_ticket")
    public Message getActuralTicket(@RequestParam("contract_id") Integer id) {
        Message message = null;

        List<Map<String, Object>> list = acturalReceiptService.getAcuralTicket(id);
        message = Message.createSuc(list);
        return message;

    }

    @RequiresRoles(value = { "manager", "accountant" }, logical = Logical.OR)
    @ApiOperation("增加一个实际开票")
    @PostMapping("add_actural_ticket")
    public Message addActuralTicket(@RequestBody ActuralTicket model) {
        Message message = null;
        Double planTicket = acturalReceiptService.isPlanTicket(model.getContractId());
        if (planTicket == null || model.getAmount() < 0 || planTicket < model.getAmount())
            message = Message.createErr(-1, "你的实开票不符合实际");
        else {
            Integer result = acturalReceiptService.addActralTicket(model);
            message = Message.createSuc(result);
        }
        return message;
    }

    @ApiOperation("开票列表")
    @GetMapping("ticket_list")
    @RequiresRoles(value = { "manager", "accountant", "allchief", "areachief" }, logical = Logical.OR)
    public Message ticketList(@RequestParam(required = false) String key,
            @RequestParam(value = "start_date", required = false) String start,
            @RequestParam(value = "end_date", required = false) String end, @RequestParam("page_num") Integer page,
            @RequestParam Integer record) throws ParseException {

        Message message = null;
        Date startDate, endDate = startDate = null;
        Subject subject = SecurityUtils.getSubject();
        Integer blockId = null;
        if (subject.hasRole("areachief"))
            blockId = contractService.getBlockById((Integer) subject.getSession().getAttribute("userId"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (start != null)
            startDate = sdf.parse(start);
        if (end != null)
            endDate = sdf.parse(end);
        PageHelper.startPage(page, record);
        PageInfo<Map<String, Object>> list = new PageInfo<>(
                acturalReceiptService.getTicketList(key, startDate, endDate, blockId));
        Map<String, Object> map = new HashMap<>();
        map.put("data", list.getList());
        map.put("total_record", list.getTotal());
        message = Message.createSuc(map);
        return message;

    }

    @ApiOperation("开票详情列表")
    @GetMapping("ticket_detail")
    @RequiresRoles(value = { "manager", "accountant", "allchief", "areachief" }, logical = Logical.OR)
    public Message getTicketDetail(@RequestParam("page_num") Integer pageNum,@RequestParam("record")Integer record,@RequestParam("contract_id")Integer id)
    {
        Message message= null;
       PageHelper.startPage(pageNum, record);
       PageInfo<Map<String,Object>> list = new PageInfo<>(acturalReceiptService.getTicketDetail(id));
        Map<String, Object> map = new HashMap<>();
        map.put("data", list.getList());
        map.put("total_record", list.getTotal());
        message = Message.createSuc(map);
        return message;
    }

    @ApiOperation("實開票详情列表")
    @GetMapping("ticket_actural_detail")
    @RequiresRoles(value = { "manager", "accountant", "allchief", "areachief" }, logical = Logical.OR)
    public Message getTicketActuralDetail(@RequestParam("page_num") Integer pageNum,@RequestParam("record")Integer record,@RequestParam("contract_id")Integer id)
    {
        Message message= null;
        PageHelper.startPage(pageNum, record);
        PageInfo<Map<String,Object>> list = new PageInfo<>(acturalReceiptService.getActralDetail(id));
        Map<String, Object> map = new HashMap<>();
        map.put("data", list.getList());
        map.put("total_record", list.getTotal());
        message = Message.createSuc(map);
        return message;
    }



}