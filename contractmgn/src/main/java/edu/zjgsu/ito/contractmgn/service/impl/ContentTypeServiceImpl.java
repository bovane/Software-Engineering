package edu.zjgsu.ito.contractmgn.service.impl;

import edu.zjgsu.ito.contractmgn.mapper.ContentTypeDAO;
import edu.zjgsu.ito.contractmgn.model.ContentType;
import edu.zjgsu.ito.contractmgn.model.ContentTypeExample;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentTypeServiceImpl implements ContentTypeService {

    @Autowired
    ContentTypeDAO contentTypeDAO;

    @Override
    public Message addContentType(int contractTypeId, String name) {

        ContentTypeExample example = new ContentTypeExample();
        example.or().andNameEqualTo(name).andContractTypeEqualTo(contractTypeId);
        List<ContentType> contentTypes = contentTypeDAO.selectByExample(example);
        if(contentTypes.size()!=0){
            return Message.createErr(501,"duplicate name");
        }

        ContentType contentType = new ContentType();
        contentType.setContractType(contractTypeId);
        contentType.setName(name);
        contentTypeDAO.insert(contentType);

        return Message.createSuc(null);
    }

    @Override
    public Message getContentType(int contractTypeId) {

        ContentTypeExample example = new ContentTypeExample();
        example.or().andContractTypeEqualTo(contractTypeId);

        return Message.createSuc(contentTypeDAO.selectByExample(example));
    }

    @Override
    public Message renameContentType(int id, String name) {

        ContentTypeExample example = new ContentTypeExample();
        example.or().andNameEqualTo(name);
        List<ContentType> contentTypes = contentTypeDAO.selectByExample(example);
        if(contentTypes.size()!=0){
            return Message.createErr(501,"duplicate name");
        }

        ContentType contentType = contentTypeDAO.selectByPrimaryKey(id);
        if(contentType!=null){
            contentType.setName(name);
            contentTypeDAO.updateByPrimaryKey(contentType);
        }
        return Message.createSuc(null);
    }
}
