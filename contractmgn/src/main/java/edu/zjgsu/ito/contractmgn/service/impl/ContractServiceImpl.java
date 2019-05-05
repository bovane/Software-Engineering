package edu.zjgsu.ito.contractmgn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.contractmgn.mapper.*;
import edu.zjgsu.ito.contractmgn.model.*;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.pojo.StatueCode;
import edu.zjgsu.ito.contractmgn.service.ContractService;
import edu.zjgsu.ito.contractmgn.service.ReminderService;
import edu.zjgsu.ito.contractmgn.vo.ContractList;
import edu.zjgsu.ito.contractmgn.vo.ContractVo;
import edu.zjgsu.ito.contractmgn.vo.ShowContract;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.abs;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private BlockDAO blockDAO;
    @Autowired
    private ContractTypeDAO contractTypeDAO;
    @Autowired
    private ContractNodeDAO contractNodeDAO;
    @Autowired
    private ContractSellerDAO contractSellerDAO;
    @Autowired
    private AccountReceivableDAO accountReceivableDAO;
    @Autowired
    private ReminderService reminderService;
    @Autowired
    private InvoiceReceivableDAO invoiceReceivableDAO;
    @Autowired
    private IActuralReceiptDAO iActuralReceiptDAO;
    @Autowired
    private ExecuteNodeDAO executeNodeDAO;

    @Transactional
    @Override
    public Message addContract(ContractVo contractVo) {
        Contract contract = new Contract();
        ContractNode contractNodes = new ContractNode();
        ContractSeller contractSeller = new ContractSeller();
        BeanUtils.copyProperties(contractVo, contract);
        contract.setRestAmount(contractVo.getAmount());//设置剩余应收款的初始值为合同金额
        Subject subject = SecurityUtils.getSubject();
        int userId = (int) subject.getSession().getAttribute("userId");//获取登陆者userid，作为创建者id
        contract.setCreateUser(userId);
        contractDAO.insertSelective(contract);
        int contractId = contractDAO.lastRecordId();//得到刚插入合同记录的id
        for (int j = 0; j < contractVo.getContractSellerList().size(); j++) {//遍历插入销售员
            contractSeller.setContractId(contractId);
            contractSeller.setSellerId(contractVo.getContractSellerList().get(j).getSellerId());
            contractSellerDAO.insertSelective(contractSeller);
        }

        if (contractVo.getContractNodeList() != null) {//遍历插入合同节点
            //List<ContractNode> contractNodeList = new ArrayList<>();
            for (int i = 0; i < contractVo.getContractNodeList().size(); i++) {
                BeanUtils.copyProperties(contractVo.getContractNodeList().get(i), contractNodes);
                contractNodes.setContractId(contractId);
                contractNodeDAO.insertSelective(contractNodes);
            }
        }
        if (accountReceivableDAO.checkRecord() == null) {//如果应收表中缺少用于连表的记录，则插入一条
            accountReceivableDAO.insertRecord();
        }
        return Message.createSuc(null);
    }

    @Override
    public String buildContractNumber(int blockId, int contractTypeId, Date creatTime) {
        String rankString = "001";//合同编号后三位编号
        String monthYearRecord = "-1";
        Block block = blockDAO.selectBlockById(blockId);
        String blockTag = block.getTag();//获取所属区块编号
        ContractType contractType = contractTypeDAO.selectContractTypeById(contractTypeId);
        String contractTag = contractType.getTag();//获取地区编号
        if (contractDAO.recordNumber() != 0) {//判断数据库合同表是否已经有记录
            String lastContractNumber = contractDAO.lastRecord().getNumber();//获取上一条合同记录编号
            monthYearRecord = lastContractNumber.substring(lastContractNumber.length() - 9, lastContractNumber.length() - 3);//获取最后一条记录的月份
            int rank = Integer.parseInt(lastContractNumber.substring(lastContractNumber.length() - 3));
            rank++;
            rankString = String.valueOf(rank);
        }
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(creatTime);
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        if (month.length() < 2)
            month = "0" + month;
        if (!(year + month).equals(monthYearRecord)) {
            rankString = "1";
        }
        while (rankString.length() < 3) {
            rankString = "0" + rankString;
        }
        return blockTag + contractTag + year + month + rankString;
    }

    @Override
    public Message updateContract(ContractVo contractVo) {
        Contract contract = new Contract();
        ContractSeller contractSeller = new ContractSeller();
        BeanUtils.copyProperties(contractVo, contract);
        //Subject subject = SecurityUtils.getSubject();
        //int userId = (int) subject.getSession().getAttribute("userId");//获取登陆者userid，作为创建者id
        //contract.setCreateUser(userId);
        contractDAO.updateByPrimaryKeySelective(contract);
        contractSellerDAO.deleteSellerByContractId(contractVo.getId());
        for (int j = 0; j < contractVo.getContractSellerList().size(); j++) {//遍历插入销售员
            BeanUtils.copyProperties(contractVo.getContractSellerList().get(j), contractSeller);
            contractSeller.setContractId(contractVo.getId());
            contractSellerDAO.insertSelective(contractSeller);
        }

        if (contractVo.getContractNodeList() != null) {
            for (int i = 0; i < contractVo.getContractNodeList().size(); i++) {
                ContractNodeExample contractNodeExample = new ContractNodeExample();
                contractNodeExample.createCriteria().andContractIdEqualTo(contractVo.getId()).andNameEqualTo(contractVo.getContractNodeList().get(i).getName());
                List<ContractNode> contractNodeList = contractNodeDAO.selectByExample(contractNodeExample);//根据合同id以及节点名称在contract_node表找到节点记录
                AccountReceivableExample accountReceivableExample = new AccountReceivableExample();
                accountReceivableExample.createCriteria().andContractIdEqualTo(contractVo.getId());
                List<AccountReceivable> accountReceivableList = accountReceivableDAO.selectByExample(accountReceivableExample);
                if (abs(contractNodeList.get(0).getMoney() - contractVo.getContractNodeList().get(0).getMoney()) > 0 && accountReceivableList.size() > 0) {
                    return Message.createErr(511, "已添加应收款，无法修改合同节点金额，如要修改，请先删除该合同应收款计划");
                }
                ContractNode contractNodes = new ContractNode();
                BeanUtils.copyProperties(contractVo.getContractNodeList().get(i), contractNodes);
                contractNodeDAO.updateByExampleSelective(contractNodes, contractNodeExample);
            }

        }
        reminderService.insertReminder(contractVo.getId(), 1, "合同基本内容更新");
        return Message.createSuc(null);
    }

    @Transactional
    @Override
    public Message deleteContract(Integer id) {
        //合同有应收款，则无法删除
        Contract contract = contractDAO.selectByPrimaryKey(id);
        if (contract.getRestAmount() < contract.getAmount() ) {
            return Message.createErr(StatueCode.DELETE_FAILURE, "合同已有收款记录，无法删除");//
        }
        Integer records = iActuralReceiptDAO.getActualTicketRecordByContractId(id);
        if(records>0){
            return Message.createErr(StatueCode.DELETE_FAILURE, "合同已有实开票记录，无法删除");//
        }
        //删除该合同节点表中记录
        ContractNodeExample contractNodeExample = new ContractNodeExample();
        contractNodeExample.createCriteria().andContractIdEqualTo(id);
        contractNodeDAO.deleteByExample(contractNodeExample);
        //删除该合同应收款表中记录
        AccountReceivableExample accountReceivableExample = new AccountReceivableExample();
        accountReceivableExample.createCriteria().andContractIdEqualTo(id);
        accountReceivableDAO.deleteByExample(accountReceivableExample);
        //删除该合同应开票表中记录
        InvoiceReceivableExample invoiceReceivableExample = new InvoiceReceivableExample();
        invoiceReceivableExample.createCriteria().andContractIdEqualTo(id);
        invoiceReceivableDAO.deleteByExample(invoiceReceivableExample);
        //删除该合同销售员记录
        contractSellerDAO.deleteSellerByContractId(id);
        //删除该合同执行节点表中记录
        ExecuteNodeExample executeNodeExample = new ExecuteNodeExample();
        executeNodeExample.createCriteria().andContractIdEqualTo(id);
        executeNodeDAO.deleteByExample(executeNodeExample);
        //删除合同记录
        contractDAO.deleteByPrimaryKey(id);
        return Message.createSuc(null);
    }

    @Override
    public Message getContractServiceTime(Integer contractId) {
        return Message.createSuc(contractDAO.getContractServiceStartDate(contractId));
    }

    @Override
    public ShowContract lookUpContractAll(Integer id) {
        ShowContract sc = contractDAO.lookUPContractALL(id);
        List<SimpleSeller> sellers = contractDAO.getSellers(id);
        if (sellers != null && sellers.size() != 0)
            sc.setSellers(sellers);
        List<Double> nodeMoneys = contractDAO.getNodeMoneys(id);
        if (nodeMoneys != null && nodeMoneys.size() != 0)
            sc.setNodeMoneys(nodeMoneys);

        return sc;
    }

    @Override
    public ShowContract lookUpContractNoMoney(Integer id) {
        return contractDAO.lookUpContractNoMoney(id);
    }

    @Override
    public Message getContractMoney(Integer contractId) {
        Double amount = contractDAO.getMoney(contractId);
        Double amountActual = accountReceivableDAO.countMoneyByNodeId(contractId);//应收款金额
        ContractNodeExample contractNodeExample = new ContractNodeExample();
        contractNodeExample.createCriteria().andContractIdEqualTo(contractId);
        List<ContractNode> contractNodeList = contractNodeDAO.selectByExample(contractNodeExample);//查询该合同下的节点记录
        JSONArray contractNodes = new JSONArray();
        for (ContractNode contractNodeString : contractNodeList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("contractNodeId", contractNodeString.getId());
            jsonObject.put("contractNodeName", contractNodeString.getName());
            contractNodes.add(jsonObject);
        }
        if (amountActual == null) {
            amountActual = 0.0;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("amount", amount);
        map.put("restMoneyActual", amount - amountActual);
        map.put("contractNode", contractNodes);
        return Message.createSuc(map);
    }

    @Override
    public List<Integer> lookUpBlockDuty(Integer id) {
        return contractDAO.lookUpBlockDuty(id);
    }

    @Override
    public List<Integer> lookUpSaler(Integer id) {
        return contractDAO.lookUpSaler(id);
    }


    @Override
    public String getArchiveNumber(Integer contractId) {
        String first = contractDAO.getContractTypeTag(contractId);
        String second = new SimpleDateFormat("yyyyMM").format(new Date());
        String last = contractDAO.lastArchive();
        String third = null;
        if (last == null || !(last.substring(last.length() - 9, last.length() - 3).equals(second))) {
            third = "001";
        } else {
            third = String.format("%03d", Integer.parseInt(last.substring(last.length() - 3)) + 1);
        }

        return first + second + third;

    }

    @Override
    public List<ContractList> getContractList(String key, Integer blockId, Integer typeId, Date start, Date end, Integer sellerId,Integer archiveTag) {
        return contractDAO.getContractList(key, blockId, typeId, start, end, sellerId,archiveTag);

    }

    @Override
    public List<String> executeList(Integer id) {
        return contractDAO.executeList(id);
    }

    @Override
    public Integer archiveContract(ContractArchive ca) {

        contractDAO.archiveContract(ca);
        return ca.getId();
    }

    @Override
    public Map<String, Object> showArchive(Integer id) {
        return contractDAO.showArchive(id);
    }

    @Override
    public Integer getBlockById(Integer attribute) {
        return blockDAO.getBlockById(attribute);
    }

    @Override
    public Integer updateArchive(ContractArchive ca) {
        return contractDAO.updateArchive(ca);
    }

    @Override
    public Integer deleteArchive(Integer id) {
        return contractDAO.deleteArchive(id);
    }

}
