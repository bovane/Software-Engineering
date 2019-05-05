package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.pojo.Message;

public interface BlockService {

    Message addBlock(String name);

    Message getBlock();

    Message renameBlock(int id,String name);

    Message setBlockResponser(int id,int responser);
}
