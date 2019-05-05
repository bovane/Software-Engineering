package edu.zjgsu.ito.contractmgn.provider;

import java.util.Date;
import java.util.Map;

public class ContractProvider {

    public String selectList(Map<String, Object> map) {
        Integer blockId = (Integer) map.get("block_id");
        Integer typeId = (Integer) map.get("type_id");
        String key = (String) map.get("key");
        Date start = (Date) map.get("start"), end = (Date) map.get("end");
        Integer sellerId = (Integer) map.get("seller_id");
        Integer archiveTag = (Integer) map.get("archive_tag");
        StringBuffer sb = new StringBuffer();
        sb.append("select distinct  c.id id, " + " c.number number," + " c.name name, " + " b.name block,"
                + " c.sign_date sign_date," + " ct.name contract_type , " + " c.operation_tag contract_tag, c.archive_tag archive_tag "
                + " from contract c " + " left join block b on b.id = c.block "
                + " left join contract_type ct on ct.id = c.contract_type "
                + " left join contract_seller cs on cs.contract_id = c.id " + " where true ");
        if (blockId != 0){
            sb.append(" and c.block = #{block_id} ");
        }

        if (sellerId != null) {
            sb.append(" and cs.seller_id = #{seller_id} ");
        }

        if (typeId != 0) {
            sb.append(" and c.content_type = #{type_id} ");
        }

        if (start != null) {
            sb.append(" and c.sign_date >= #{start} ");
        }

        if (end != null)
            sb.append(" and c.sign_date <= #{end} ");
        if(archiveTag != null && archiveTag != 0 )
        {
            sb.append("and archive_tag = (#{archive_tag} - 1) ");
        }

        if (key != null) {
            sb.append(" and (c.number like concat('%',#{key},'%')" + " or c.name like concat('%',#{key},'%')"
                    + " or b.name like concat('%',#{key},'%')" + " or ct.name like concat('%',#{key},'%')) ");
        }

        sb.append(" order by c.id ");
        return sb.toString();
    }

    public String ticketList(Map<String,Object> map)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("select c.operation_tag contract_tag ,c.id contract_id , c.number contract_number , c.name contract_name ,b.name block_name,  should.invoice_money should_money ,(should.invoice_money - rest.money) actural_money,should.issue_id ,(to_days(now()) - to_days(should.date)) day  from contract c left join block b on c.block = b.id  left join ticket_execute te on te.contract_id = c.id left join invoice_receivable should on should.id = te.should_id left join ticket rest on rest.should_id = should.id   where true ");
        Date starDate = (Date)map.get("start_date");
        Date endDate = (Date)map.get("end_date");
        String key = (String)map.get("key");
        Integer blockId = (Integer)map.get("block_id");
        if(key != null)
        {
            sb.append("and (c.number like concat('%',#{key},'%') or c.name like concat('%',#{key},'%') or b.name like concat('%',#{key},'%') ) ");
        }

        if(starDate != null)
        sb.append("and c.sign_date >= #{start_date} ");
        if(endDate != null)
        sb.append("and c.sign_date <= #{end_date} ");
        if(blockId != null && blockId != 0)
        sb.append("and c.block = #{block_id} ");
        sb.append(" order by  c.id ");

        return sb.toString();
    }
}
