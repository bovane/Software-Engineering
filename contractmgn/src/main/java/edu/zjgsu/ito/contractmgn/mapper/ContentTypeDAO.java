package edu.zjgsu.ito.contractmgn.mapper;

import edu.zjgsu.ito.contractmgn.model.ContentType;
import edu.zjgsu.ito.contractmgn.model.ContentTypeExample;
import org.springframework.stereotype.Repository;

/**
 * ContentTypeDAO继承基类
 */
@Repository
public interface ContentTypeDAO extends MyBatisBaseDao<ContentType, Integer, ContentTypeExample> {
}