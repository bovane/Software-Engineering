package edu.zjgsu.ito.contractmgn.provider;

import java.util.Date;
import java.util.Map;

public class AccountProvider {
    public String getAccounInfo(Map<String, Object> map) {
        String key = (String) map.get("key");
        Date start = (Date) map.get("start"), end = (Date) map.get("end");
        Integer userId=(Integer) map.get("userId");
        StringBuffer sb = new StringBuffer();
        sb.append("select c.id contract_id," +
                "c.number number," +
                "c.name name," +
                "ar.money account_receivable," +
                "(c.amount-c.rest_amount) account_actual," +
                "c.operation_tag contractTag," +
                "b.name block," +
                "t.name contract_type," +
                "ct.name content_type," +
                "cn.name contract_node," +
                "ar.stage stage," +
                "ar.status status," +
                "ar.days days " +
                "from contract c " +
                " join contract_type t on t.id=c.contract_type " +
                " join content_type ct on ct.id=c.content_type" +
                " join account_receivable ar on ar.id=c.account_id" +
                " left join contract_node cn on cn.id=ar.node_id" +
                " join block b on b.id = c.block ");
        if(userId!=null){
            sb.append(" join block_user bu on bu.user_id=#{userId} where c.block=bu.block_id");
        }

        if (start != null) {
            sb.append(" and c.sign_date >= #{start} ");
        }

        if (end != null)
            sb.append(" and c.sign_date <= #{end} ");

        if (key != null) {
            sb.append(" and (c.number like concat('%',#{key},'%')" +
                    " or c.name like concat('%',#{key},'%')" +
                    " or b.name like concat('%',#{key},'%')" +
                    " or cn.name like concat('%',#{key},'%')" +
                    " or ct.name like concat('%',#{key},'%')) ");
        }
        sb.append(" order by ar.end_date asc");

        return sb.toString();
    }
}
