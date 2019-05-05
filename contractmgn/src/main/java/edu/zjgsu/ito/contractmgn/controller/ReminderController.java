package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.ReminderService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReminderController {

    @Autowired
    ReminderService reminderService;

    @GetMapping("reminder")
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief","buyer"}, logical = Logical.OR)
    public Message getReminder(@RequestParam int type,@RequestParam int pageNum){
        return reminderService.getReminder(type,pageNum);
    }

    @GetMapping("reminder/{id}")
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief","buyer"}, logical = Logical.OR)
    public Message getReminder(@PathVariable(value = "id") int id){
        return reminderService.readReminder(id);
    }

    @GetMapping("reminder/number")
    @RequiresRoles(value = {"manager", "accountant", "overallchief","areachief","buyer"}, logical = Logical.OR)
    public Message countUnreadReminder(){
        return reminderService.countUnreadReminder();
    }
}
