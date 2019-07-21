package com.zjsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 报销管理的控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping( "/expense/" )
public class ExpenseController {
    /**
     * 加载报销单
     *
     * @return
     */
    @RequestMapping( "add.do" )
    public String add( ) {
        return "expense/expense_add";
    }

    /**
     * 加载部门负责人审核页面
     *
     * @return
     */
    @RequestMapping( "loadAuditManager.do" )
    public String loadAudiManager( ) {
        return "expense/expense_audit_manager";
    }

    /**
     * 加载具体审核页面
     *
     * @return
     */
    @RequestMapping( "loadManager.do" )
    public String loadManager( ) {
        return "expense/expense_manager";
    }

    /**
     * 加载部门财务审核页面
     *
     * @return
     */
    @RequestMapping( "loadAuditFinance.do" )
    public String loadAuditFinance( ) {
        return "expense/expense_audit_finance";
    }

    /**
     * 加载具体财务审核页面
     *
     * @return
     */
    @RequestMapping( "loadFinance.do" )
    public String loadFinance( ) {
        return "expense/expense_finance";
    }

    /**
     * 查看我的报销单
     *
     * @return
     */
    @RequestMapping( "mylist.do" )
    public String mylist( ) {
        return "expense/expense_mylist";
    }

    /**
     * 查看报销单明细
     *
     * @return
     */
    @RequestMapping( "myshow.do" )
    public String myshow( ) {
        return "expense/expense_myshow";
    }

    /**
     * 报销查询
     *
     * @return
     */
    @RequestMapping( "list.do" )
    public String list( ) {
        return "expense/expense_list";
    }

    /**
     * 查看报销单明细
     *
     * @return
     */
    @RequestMapping( "show.do" )
    public String show( ) {
        return "expense/expense_show";
    }

    /**
     * 我的审核
     *
     * @return
     */
    @RequestMapping( "myauditlist.do" )
    public String myauditlist( ) {
        return "expense/expense_myauditlist";
    }

}