package edu.zjgsu.ito.contractmgn.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import edu.zjgsu.ito.contractmgn.mapper.ContractDAO;
import edu.zjgsu.ito.contractmgn.mapper.IActuralReceiptDAO;
import edu.zjgsu.ito.contractmgn.model.ActuralReceipt;
import edu.zjgsu.ito.contractmgn.model.ActuralReceiptList;
import edu.zjgsu.ito.contractmgn.model.ActuralTicket;
import edu.zjgsu.ito.contractmgn.model.ReceiptModel;
import edu.zjgsu.ito.contractmgn.model.Seller;
import edu.zjgsu.ito.contractmgn.schedule.ReceivableScheduleTask;
import edu.zjgsu.ito.contractmgn.service.CommissionService;
import edu.zjgsu.ito.contractmgn.service.IActuralReceiptService;
import edu.zjgsu.ito.contractmgn.service.ReminderService;

/**
 * ActuralReceiptService
 */
@Service
public class ActuralReceiptService implements IActuralReceiptService {

    @Autowired
    private IActuralReceiptDAO acturalReceiptDAO;
    @Autowired
    ReminderService reminderService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private ContractDAO contractDao;
    @Autowired
    ReceivableScheduleTask task;

    @Override
    public ActuralReceiptList getReceiptList(Integer id) {
        ActuralReceiptList list = new ActuralReceiptList();
        list.setTotalAmount(contractDao.getMoney(id));

        List<ActuralReceipt> alist = acturalReceiptDAO.getListByContractId(id);
        list.setActureReceipts(alist);
        return list;
    }

    @Override
    public Double isPlan(Integer contractId) {
        return acturalReceiptDAO.isPlan(contractId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Integer insertReceipt(ReceiptModel model) {

        acturalReceiptDAO.insertReceipt(model);
        reminderService.insertReminder(model.getContractId(),2, "实收款更新");
        Double amount = model.getReceipt();
        List<Map<String,Object>> list = acturalReceiptDAO.getShouldReceiptMoney(model.getContractId());
        for (Map<String,Object> item : list) {
            Integer id = (Integer)item.get("id");
            Double shouldMoney = (Double)item.get("should_money");
            Double acturalMoney = (Double)item.get("actural_money");
            if(amount + acturalMoney < shouldMoney )
            {
                acturalReceiptDAO.updateReceiptMoney(id,amount + acturalMoney);
                break;
            }
            else
            {
                amount -= (shouldMoney - acturalMoney);
                acturalReceiptDAO.updateReceipt(id);
            }

        }

        contractDao.updateRestAmount(amount,model.getContractId());
        for (Seller item : model.getSellers()) {
            commissionService.insertCommission(model.getContractId(), model.getId(), item.getPercentage(),
                    model.getDueDate(), item.getSellerId());
        }
        task.checkCommissionStatus();
        task.checkoutReceivableStatus();

        return model.getId();
    }

    @Override
    public List<Map<String, Object>> getAcuralTicket(Integer id) {
        return acturalReceiptDAO.getTicket(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Integer addActralTicket(ActuralTicket model) {
        acturalReceiptDAO.addActralTicket(model);
        reminderService.insertReminder(model.getContractId(), 2, "实开票更新");
        List<Map<String, Object>> map = acturalReceiptDAO.getTicketMoney(model.getContractId());
        Double amount = model.getAmount();
        if (map != null)
            for (Map<String, Object> item : map) {
                Double money = (Double) item.get("money");
                Integer id = (Integer) item.get("id");
                Integer shouldId =(Integer) item.get("should_id");
                if (money > amount)
                    {

                        acturalReceiptDAO.updateMoney(money - amount, id);
                        acturalReceiptDAO.addShouldActral(shouldId, model.getId(),amount);
                        acturalReceiptDAO.updateTicketExecute(model.getContractId(), shouldId);
                        acturalReceiptDAO.updateShouldTicket(shouldId,1);
                        break;
                    }
                    else{
                        amount -= money;
                        acturalReceiptDAO.updateMoney(0d, id);
                        acturalReceiptDAO.updateTicketExecute(model.getContractId(),shouldId);
                        acturalReceiptDAO.updateTicket(shouldId);
                        acturalReceiptDAO.addShouldActral(shouldId, model.getId(),money);
                        acturalReceiptDAO.updateShouldTicket(shouldId,2);
                    }

                

            }
            return model.getId();
    }

    @Override
    public Double isPlanTicket(Integer contractId) {
        return acturalReceiptDAO.isPlanTicket(contractId);
    }

    @Override
    public List<Map<String, Object>> getTicketList(String key, Date startDate, Date endDate,Integer blockId) {
        return acturalReceiptDAO.getTicketList(key, startDate, endDate,blockId);
    }

    @Override
    public List<Map<String, Object>> getTicketDetail(Integer contractId) {
        return acturalReceiptDAO.getTicketDetail(contractId);
    }

    @Override
    public List<Map<String, Object>> getActralDetail(Integer id) {
        return acturalReceiptDAO.getActralDetail(id);
    }

}