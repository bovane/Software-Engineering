package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlockController {

    @Autowired
    BlockService blockService;

    @PostMapping("block")
    public Message addBlock(@RequestParam String name){
        return blockService.addBlock(name);
    }

    @GetMapping("block")
    public Message getBlock(){
        return blockService.getBlock();
    }

    @PutMapping("block")
    public Message renameBlock(@RequestParam int id,@RequestParam String name){
        return blockService.renameBlock(id, name);
    }

    @GetMapping("blockUser")
    public Message setBlockResponser(@RequestParam int id,@RequestParam int responser){
        return blockService.setBlockResponser(id, responser);
    }


}
