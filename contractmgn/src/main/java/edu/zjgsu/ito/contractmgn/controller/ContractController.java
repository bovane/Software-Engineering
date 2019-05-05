package edu.zjgsu.ito.contractmgn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zjgsu.ito.contractmgn.vo.ContractList;
import edu.zjgsu.ito.contractmgn.model.ContractArchive;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ContractService;
import edu.zjgsu.ito.contractmgn.util.FileUtil;
import edu.zjgsu.ito.contractmgn.vo.ContractVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api(description = "合同相关接口")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Value("${pdf.path}")
    private String path;

    /**
     * 新建一个合同(包括合同节点)
     *
     * @param contractVo 合同对象
     * @return
     */
    @RequiresRoles(value = { "manager", "accountant", "overallchief", "areachief" }, logical = Logical.OR)
    @PostMapping("/contract")
    @ResponseBody
    public Message buildContract(@RequestBody ContractVo contractVo) {
        String number = contractService.buildContractNumber(contractVo.getBlock(), contractVo.getContractType(),
                contractVo.getCreateDate());
        contractVo.setNumber(number);
        return contractService.addContract(contractVo);
    }

    /**
     * 更新合同
     *
     * @param contractVo 合同对象
     * @return Message
     */
    @RequiresRoles(value = { "manager", "accountant", "overallchief" }, logical = Logical.OR)
    @PutMapping("/contract")
    @ResponseBody
    public Message updateContract(@RequestBody ContractVo contractVo) {
        return contractService.updateContract(contractVo);
    }

    /**
     * 删除合同
     * 
     * @param ContractId 合同id
     * @return Message
     */
    @RequiresRoles(value = { "manager", "accountant", "overallchief" }, logical = Logical.OR)
    @DeleteMapping("/contract")
    @ResponseBody
    public Message deleteContract(@RequestParam Integer ContractId) {
        return contractService.deleteContract(ContractId);
    }

    /**
     *
     * @param id 合同id
     * @return 该合同的详细信息
     */

    @GetMapping("contract")
    @ResponseBody
    @ApiOperation("查看合同")
    @RequiresAuthentication
    public Message lookUpContract(@RequestParam Integer id) {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");
        if (subject.hasRole("manager") || subject.hasRole("accountant") || subject.hasRole("overallchief")) {
            return Message.createSuc(contractService.lookUpContractAll(id));
        } else if (subject.hasRole("areachief")) {
            if (contractService.lookUpBlockDuty(id).contains(userId)) {
                return Message.createSuc(contractService.lookUpContractAll(id));
            }

            return Message.createErr(-1, "该合同不在你所负责的区块内");
        } else if (subject.hasRole("saler")) {
            if (contractService.lookUpSaler(id).contains(userId)) {
                return Message.createSuc(contractService.lookUpContractAll(id));
            }

            return Message.createErr(-2, "你不是该合同的销售员");
        } else if (subject.hasRole("buyer")) {
            return Message.createSuc(contractService.lookUpContractNoMoney(id));
        }
        return Message.createErr(-3, "你还没有属于自己的角色");
    }

    /**
     *
     * @param key       检索字段
     * @param pageNum   页号
     * @param record    每页记录数
     * @param blockId   区块号
     * @param typeId    合同内容类别
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    @GetMapping("contract_list")
    @ResponseBody
    @ApiOperation("获取列表")
    @RequiresAuthentication
    public Message getContractList(@RequestParam(value = "key", required = false) String key,
            @RequestParam("page_num") Integer pageNum, @RequestParam Integer record,
            @RequestParam(value = "block_id") Integer blockId, @RequestParam(value = "type_id") Integer typeId,
            @RequestParam(value = "start_date", required = false) String startDate,
            @RequestParam(value = "end_date", required = false) String endDate,
            @RequestParam(value = "archive_tag") Integer  archiveTag ) {
        Message message = null;
        Integer sellerId = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("saler")) {
            sellerId = (Integer) subject.getSession().getAttribute("userId");
            blockId = 0;
        } else if (subject.hasRole("areachief")) {
            blockId = contractService.getBlockById((Integer) subject.getSession().getAttribute("userId"));
            if (blockId == null) {
                message = Message.createErr(-2, "你还没有负责的区块");
                return message;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null, end = null;
        try {
            if (startDate != null)
                start = sdf.parse(startDate);
            if (endDate != null)
                end = sdf.parse(endDate);
        } catch (Exception e) {
            Message.createErr(-3, "日期格式不对");
        }

        PageHelper.startPage(pageNum, record);
        PageInfo<ContractList> pageInfo = new PageInfo<>(
                contractService.getContractList(key, blockId, typeId, start, end, sellerId,archiveTag));
        Map<String, Object> result = new HashMap<>();
        result.put("all_record", pageInfo.getTotal());
        result.put("all_page", pageInfo.getPages());
        result.put("current_page", pageNum);
        result.put("data", pageInfo.getList());
        message = Message.createSuc(result);
        return message;
    }

    @RequiresRoles(value = { "manager", "accountant", "allchief" }, logical = Logical.OR)
    @ApiOperation("合同归档")
    @ResponseBody
    @PostMapping("archive_contract")
    public Message archiveContract(
            ContractArchive ca,@RequestParam(required = false) List<MultipartFile> contractContent,@RequestParam(required =  false) List<MultipartFile> other) {
        ca.setEntryId((Integer) (SecurityUtils.getSubject().getSession().getAttribute("userId")));
        StringBuilder contractContentString ;
        StringBuilder otherString  ;
        if(ca.getContractContents() != null)
        {
            contractContentString = new StringBuilder(ca.getContractContents());
        }else
        {
            contractContentString = new StringBuilder();
        }

        if(ca.getOthers() != null)
            otherString = new StringBuilder(ca.getOthers());
        else
            otherString = new StringBuilder();
        if (ca.getId() == null)
            ca.setArchiveNumber(contractService.getArchiveNumber(ca.getContractId()));
        if (contractContent != null) {
            for (MultipartFile contractContentFile : contractContent) {
                String originalFilename = contractContentFile.getOriginalFilename();
                try {
                    String filename = System.currentTimeMillis() + "_" + originalFilename;
                    contractContentFile.transferTo(FileUtil.getFile(filename, path));
                    contractContentString.append(filename).append(",");
                } catch (IOException e) {
                    return Message.createErr(-1, "上传文件出错");
                }
            }
        }
        if (other != null) {
            for (MultipartFile contractContentFile : other) {
                String otherName = contractContentFile.getOriginalFilename();
                try {
                    String filename = System.currentTimeMillis() + "_" + otherName;
                    contractContentFile.transferTo(FileUtil.getFile(filename, path));
                    otherString.append(filename).append(",");
                } catch (IOException e) {
                    return Message.createErr(-1, "上传文件出错");
                }
            }
        }

        ca.setContractContents(contractContentString.toString());
        ca.setOthers(otherString.toString());

        Integer id = ca.getId() == null ? contractService.archiveContract(ca) : contractService.updateArchive(ca);

        if (id == null) {
            return Message.createErr(-1, "数据库存储错误");
        }
        return Message.createSuc(id);
    }

    @ApiOperation("执行节点列表")
    @ResponseBody
    @GetMapping("execute_list")
    public Message executeList(Integer id) {
        List<String> result = contractService.executeList(id);
        return Message.createSuc(result);
    }

    @RequiresRoles(value = { "manager", "accountant", "allchief", "areachief" }, logical = Logical.OR)
    @ApiOperation("查看合同归档信息")
    @ResponseBody
    @GetMapping("show_archive")
    public Message showArchive(@RequestParam("contract_id") Integer id) {
        Message message = null;
        Map<String, Object> map = contractService.showArchive(id);
        if (map == null || map.isEmpty())
            message = Message.createErr(-1, "该合同未归档");
        else
            message = Message.createSuc(map);
        return message;
    }

    @RequiresRoles(value = { "manager", "accountant", "allchief"}, logical = Logical.OR)
    @ApiOperation("刪除归档信息")
    @ResponseBody
    @DeleteMapping("delete_archive")
    public Message deleteArchive(@RequestParam("contract_id") Integer id) {
        Message message = null;
        Integer  result = contractService.deleteArchive(id);
        if (result == null)
            message = Message.createErr(-1, "刪除歸檔失敗");
        else
            message = Message.createSuc(result);
        return message;
    }



}
