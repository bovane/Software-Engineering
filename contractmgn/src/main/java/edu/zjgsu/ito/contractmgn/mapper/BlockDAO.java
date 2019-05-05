package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.Block;
import edu.zjgsu.ito.contractmgn.model.BlockExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * BlockDAO继承基类
 */
@Repository
public interface BlockDAO extends MyBatisBaseDao<Block, Integer, BlockExample> {


    @Select("select * from block where id=#{blockId}")
    Block selectBlockById(@Param("blockId") Integer blockId);

    @Select("select block_id from block_user where user_id = #{value} ")
	Integer getBlockById(Integer attribute);

}