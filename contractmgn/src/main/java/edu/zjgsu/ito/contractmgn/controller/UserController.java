package edu.zjgsu.ito.contractmgn.controller;

import edu.zjgsu.ito.contractmgn.model.User;
import edu.zjgsu.ito.contractmgn.pojo.Message;
import edu.zjgsu.ito.contractmgn.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("staff")
    @RequiresRoles(value = {"manager","accountant","overallchief"},logical = Logical.OR)
    public Message addStaff(@RequestBody User user){
    return userService.addStaff(user);
}

    @PutMapping("staff")
    @RequiresRoles(value = {"manager","accountant","overallchief"},logical = Logical.OR)
    public Message updateStaff(@RequestBody User user){
        return userService.updateStaff(user);
    }

    @GetMapping("staff")
    @RequiresRoles(value = {"manager","accountant","overallchief"},logical = Logical.OR)
    Message getStaffs(@RequestParam Integer role, @RequestParam String departMent, @RequestParam int pageNum){
        return userService.getStaffs(role, departMent, pageNum);
    }

    @GetMapping("staff/{id}")
    @RequiresRoles(value = {"manager","accountant","overallchief"},logical = Logical.OR)
    Message getStaffInfo(@PathVariable(value = "id") int id){
        return userService.getStaffInfo(id);
    }

    @GetMapping("password")
    @RequiresRoles(value = {"manager","accountant","overallchief"},logical = Logical.OR)
    Message resetPassword(@RequestParam int id){
        return userService.resetPassword(id);
    }

    @PostMapping("password")
    Message setPassWordAfterReset(@RequestParam String originPassword,@RequestParam String password){
        return userService.setPassWordAfterReset(originPassword, password);
    }

    @GetMapping("staff/key")
    @RequiresRoles(value = {"manager","accountant","overallchief"},logical = Logical.OR)
    Message searchStaffByName(@RequestParam String name,@RequestParam int pageNum){
        return userService.searchStaffByName(name,pageNum);
    }

    @GetMapping("staff/saler")
    @RequiresRoles(value = {"manager","accountant","overallchief","areachief"},logical = Logical.OR)
    Message getSaler(){
        return userService.getSaler();
    }

    @GetMapping("staff/saler/{id}")
    @RequiresRoles(value = {"manager","accountant","overallchief","areachief"},logical = Logical.OR)
    Message getSalerInContract(@PathVariable(value = "id")int id){
        return userService.getSalerInContract(id);
    }

    @GetMapping("staff/areachief")
    @RequiresRoles(value = {"manager","accountant","overallchief"},logical = Logical.OR)
    Message getAreaChief(){
        return userService.getAreaChief();
    }

}
