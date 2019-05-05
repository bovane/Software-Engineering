package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.pojo.Message;

public interface ContentTypeService {

    Message addContentType(int contractTypeId,String name);

    Message getContentType(int contractTypeId);

    Message renameContentType(int id,String name);
}
