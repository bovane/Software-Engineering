package cn.edu.zjgsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 薪资管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {
    /**
     * 加载薪资发放页面
     * @return
     */
    @RequestMapping("loadadd.do")
    public String loadadd(){
        return "salary/salary_add";
    }
    /**
     * 查询薪资发放信息
     * @return
     */
    @RequestMapping("list.do")
    public String list(){
        return "salary/salary_list";
    }
}