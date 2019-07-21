package com.zjsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 权限管理的控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping( "/authority/" )
public class AuthorityController {
    /**
     * 查询角色
     *
     * @return
     */
    @RequestMapping( "list.do" )
    public String list( ) {
        return "authority/authority_list";
    }

    /**
     * 权限变更
     *
     * @return
     */
    @RequestMapping( "change.do" )
    public String change( ) {
        return "authority/authority_change";
    }
}