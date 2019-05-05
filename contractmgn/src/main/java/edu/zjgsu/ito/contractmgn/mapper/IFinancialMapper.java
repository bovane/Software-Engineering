package edu.zjgsu.ito.contractmgn.mapper;

import org.apache.ibatis.annotations.Select;

import edu.zjgsu.ito.contractmgn.model.Financial;

/**
 * IFinancialMapper
 */
public interface IFinancialMapper {

    /**
     *
     */

    /**
     * 查询钱财
     */
    public static final String SHOWMONEY = "select contract_id , total_amount, (total_amount - payment) as un_payment from financial where contract_id = #{value} ";

    @Select(SHOWMONEY)
    Financial showMoney(Integer id);

}