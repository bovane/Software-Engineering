package edu.zjgsu.ito.contractmgn.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.zjgsu.ito.contractmgn.model.ActuralReceiptList;
import edu.zjgsu.ito.contractmgn.model.ActuralTicket;
import edu.zjgsu.ito.contractmgn.model.ReceiptModel;

/**
 * IActuralReceiptService
 */
public interface IActuralReceiptService {

	ActuralReceiptList getReceiptList(Integer id);

	Double isPlan(Integer contractId);

	Integer insertReceipt(ReceiptModel model);

	List<Map<String, Object>> getAcuralTicket(Integer id);

	Integer addActralTicket(ActuralTicket model);

	Double isPlanTicket(Integer contractId);

	List<Map<String, Object>> getTicketList(String key, Date startDate, Date endDate,Integer blockId);

	List<Map<String, Object>> getTicketDetail(Integer contractId);


	List<Map<String,Object>> getActralDetail(Integer id);
}