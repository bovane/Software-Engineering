package edu.zjgsu.ito.contractmgn.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.zjgsu.ito.contractmgn.mapper.BlockDAO;
import edu.zjgsu.ito.contractmgn.mapper.BlockUserDAO;
import edu.zjgsu.ito.contractmgn.mapper.UserDAO;
import edu.zjgsu.ito.contractmgn.model.Block;
import edu.zjgsu.ito.contractmgn.model.BlockExample;
import edu.zjgsu.ito.contractmgn.model.BlockUser;
import edu.zjgsu.ito.contractmgn.model.BlockUserExample;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    BlockDAO blockDAO;
    @Autowired
    BlockUserDAO blockUserDAO;
    @Autowired
    UserDAO userDAO;

    @Override
    public Message addBlock(String name) {

        BlockExample example = new BlockExample();
        example.or().andNameEqualTo(name);
        if(blockDAO.selectByExample(example).size()>0){
            return Message.createErr(501,"repeat name");
        }
        Block block = new Block();
        block.setName(name);
        blockDAO.insert(block);
        String tag = "0"+String.valueOf(block.getId());
        block.setTag(tag);
        blockDAO.updateByPrimaryKey(block);

        return Message.createSuc(null);

    }

    @Override
    public Message getBlock() {

        List<Block> blocks = blockDAO.selectByExample(new BlockExample());
        JSONArray array = new JSONArray();
        for(Block block : blocks){
            JSONObject obj = new JSONObject();
            obj.put("id",block.getId());
            obj.put("name",block.getName());
            obj.put("tag",block.getTag());
            BlockUserExample example = new BlockUserExample();
            example.or().andBlockIdEqualTo(block.getId());
            List<BlockUser> blockUsers = blockUserDAO.selectByExample(example);
            if(blockUsers.size()==1){
                obj.put("responser",userDAO.selectByPrimaryKey(blockUsers.get(0).getUserId()).getName());
            }else{
                obj.put("responser","");
            }
            array.add(obj);
        }

        return Message.createSuc(array);
    }

    @Override
    public Message renameBlock(int id, String name) {

        Block block = blockDAO.selectByPrimaryKey(id);
        if(block!=null){
            block.setName(name);
            blockDAO.updateByPrimaryKey(block);
        }

        return Message.createSuc(null);
    }

    @Override
    public Message setBlockResponser(int id, int responser) {

        BlockUserExample example = new BlockUserExample();
        example.or().andBlockIdEqualTo(id);
        List<BlockUser> blockUsers = blockUserDAO.selectByExample(example);
        if(blockUsers.size()==1){
            BlockUser blockUser = blockUsers.get(0);
            blockUser.setUserId(responser);
            blockUserDAO.updateByPrimaryKey(blockUser);
        }else{
            BlockUser blockUser = new BlockUser();
            blockUser.setUserId(responser);
            blockUser.setBlockId(id);
            blockUserDAO.insertSelective(blockUser);
        }

        return Message.createSuc(null);
    }
}
