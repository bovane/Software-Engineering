package com.zjsu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.utils.Comm;
import com.utils.Page;
import com.zjsu.bean.RoleInfo;
import com.zjsu.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色管理控制器
 */

@Controller
@RequestMapping( "/role/" )
public class RoleController extends Page {
    @Reference
    private IRoleInfoService roleservice;

    /*
     * 查询角色信息
     */
    @RequestMapping( "list.do" )
    public String list( RoleInfo info, Model model, HttpServletRequest request ) {
        if ( info == null ) {
            info = new RoleInfo( );
        }
        //分页信息
        this.initPage( request );
        //起始记录
        info.setStart( this.getPageNo( ) );
        //每页显示记录数
        info.setLength( PAGE_NUM_BIG );
        model.addAttribute( "list", roleservice.list( info ) );
        //总记录数
        model.addAttribute( "total", roleservice.getcount( info ) );
        return "role/role_list";
    }

    /**
     * 加载添加页面
     *
     * @return
     */
    @RequestMapping( "loadadd.do" )
    public String loadAdd( ) {
        return "role/role_add";
    }

    /**
     * 添加角色
     *
     * @param info
     * @param model
     * @return
     */
    @RequestMapping( "add.do" )
    public String add( RoleInfo info, Model model, HttpServletRequest request ) {
        try {
            if ( info == null ) {
                info = new RoleInfo( );
            }
            info.setRoleMark( Comm.MARK_YES );
            roleservice.add( info );
        }
        catch ( Exception e ) {
            model.addAttribute( "errorinfo", "角色添加失败" );
            e.printStackTrace( );
        }
        info = null;
        //return "role/role_list";
        return list( info, model, request );
    }

    /**
     * 加载修改页面
     *
     * @return
     */
    @RequestMapping( "loadupdate.do" )
    public String loadUpdate( RoleInfo info, Model model ) {
        //查询角色信息
        model.addAttribute( "role", roleservice.getInfo( info ) );
        return "role/role_update";
    }

    /**
     * 修改角色信息
     *
     * @param info
     * @param model
     * @param request
     * @return
     */
    @RequestMapping( "update.do" )
    public String update( RoleInfo info, Model model, HttpServletRequest request ) {
        try {
            roleservice.update( info );
        }
        catch ( Exception e ) {
            e.printStackTrace( );
            model.addAttribute( "errorinfo", "角色修改失败" );
        }
        return list( null, model, request );
    }

    @RequestMapping( "delete.do" )
    public String delete( Integer[] roleId, Model model, HttpServletRequest request ) {
        //Integer[]表示可以删除多个角色
        try {
            roleservice.delete( roleId );
            ;
        }
        catch ( Exception e ) {
            e.printStackTrace( );
            model.addAttribute( "errorinfo", "角色修改失败" );
        }
        return list( null, model, request );
    }
}