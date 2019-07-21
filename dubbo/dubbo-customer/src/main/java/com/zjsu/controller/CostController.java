package com.zjsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 费用管理的控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping( "/cost/" )
public class CostController {
    /**
     * 查询费用信息
     *
     * @author Administrator
     */
    @RequestMapping( "list.do" )
    public String list( ) {
        return "cost/cost_list";
    }

    /**
     * 加载费用添加页面
     *
     * @return
     */
    @RequestMapping( "loadadd.do" )
    public String loadadd( ) {
        return "cost/cost_add";
    }

    /**
     * 加载费用修改页面
     *
     * @return
     */
    @RequestMapping( "loadupdate.do" )
    public String loadupdate( ) {
        return "cost/cost_update";
    }
}