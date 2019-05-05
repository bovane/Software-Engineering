package edu.zjgsu.ito.contractmgn.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.zjgsu.ito.contractmgn.model.ActuralReceipt;
import edu.zjgsu.ito.contractmgn.model.ActuralTicket;
import edu.zjgsu.ito.contractmgn.model.ReceiptModel;
import edu.zjgsu.ito.contractmgn.provider.ContractProvider;
import org.springframework.stereotype.Repository;

/**
 * IActuralReceiptDAO
 */
@Repository
public interface IActuralReceiptDAO {

        /**
         *
         */

        @Results({ @Result(column = "id", property = "sellers", one = @One(select = "edu.zjgsu.ito.contractmgn.mapper.IActuralReceiptDAO.getSellers")) })
        @Select(" select  id , " + " receivable receipt ," + " receive_date receipt_date," + " remark ," + " issue_id "
                        + " from account_actual " + " where contract_id = #{value} " + " order by issue_id ")
        List<ActuralReceipt> getListByContractId(Integer id);

        @Select(" select c.saler_id seller_id ," + " u.name seller_name , " + " c.percentage percentage, "
                        + " c.due_date due_date " + " from commission c " + " join user u on u.id = c.saler_id "
                        + " where c.actual_id = #{value} ")
        List<Map<String, Object>> getSellers(Integer id);

        @Select(" select sum(money - actual_money ) from account_receivable where contract_id = #{value}")
        Double isPlan(Integer contractId);

        @Options(useGeneratedKeys = true)
        @Insert("insert into account_actual values(null,#{issueId},#{contractId},#{receipt},#{receiptDate},#{remark})")
        void insertReceipt(ReceiptModel model);

        @Select("select issue_id , remark , date , amount from actural_ticket where contract_id = #{value}")
        List<Map<String, Object>> getTicket(Integer id);

        @Options(useGeneratedKeys = true)
        @Insert("insert into actural_ticket values(null,#{amount},#{date},#{remark},#{contractId},#{issueId})")
        Integer addActralTicket(ActuralTicket model);

        @Select(" select sum(money) from ticket where contract_id = #{value}")
        Double isPlanTicket(Integer contractId);

        @SelectProvider(type = ContractProvider.class, method = "ticketList")
        List<Map<String, Object>> getTicketList(@Param("key") String key, @Param("start_date") Date startDate,
                        @Param("end_date") Date endDate, @Param("block_id") Integer blockId);

        @Select("select money, id,should_id from ticket  where contract_id = #{value} ")
        List<Map<String, Object>> getTicketMoney(Integer contractId);

        @Update("update ticket set money = #{money} where id = #{id}")
        void updateMoney(@Param("money") Double money, @Param("id") Integer id);

        @Delete("delete from ticket where id = #{id}")
        void deleteTicket(Integer id);

        @Update("update invoice_receivable set complete_tag = 1 where id = #{value}")
        void updateTicket(Integer shouldId);

        @Select("select id , money should_money,actual_money actural_money from account_receivable where contract_id = #{value} and money > actual_money order by end_date ")
        List<Map<String, Object>> getShouldReceiptMoney(Integer contractId);

        @Update("update account_receivable set actual_money = #{money} where id = #{id}")
        void updateReceiptMoney(@Param("id") Integer id, @Param("money") Double money);

        @Update("update account_receivable set actual_money = money , status = 5 where id = #{id} ")
        void updateReceipt(Integer id);

        @Insert("insert into should_actural values(null,#{param1},#{param2},#{param3}) ")
        void addShouldActral(Integer shouldId, Integer id, Double money);

        @Select("select s.issue_id issue_id , s.invoice_money should_money,s.date  should_date , s.remark should_remark , sa.money actural_money,a.date actural_date, a.remark actural_remark from invoice_receivable s left join should_actural sa on sa.should_id = s.id left join actural_ticket a on a.id = sa.actural_id where s.contract_id = #{value} order by s.issue_id,a.date  ")
        List<Map<String, Object>> getTicketDetail(Integer contractId);

        @Update("update ticket_execute set should_id = #{param2} where contract_id = #{param1}")
        void updateTicketExecute(Integer contractId, Integer issueId);

        @Select("select count(id) from actural_ticket where contract_id=#{contractId}")
        Integer getActualTicketRecordByContractId(@Param("contractId") Integer contractId);

        @Select("select amount money , date ,remark from actural_ticket where contract_id = #{id}")
        List<Map<String, Object>> getActralDetail(Integer id);

        @Update("update invoice_receivable set complete_tag = #{param2} where id =#{param1}")
        void updateShouldTicket(Integer shouldId, int i);
}