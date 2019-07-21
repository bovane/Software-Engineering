package com.zjsu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjsu.bean.MenuInfo;
import com.zjsu.service.IMenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 菜单管理的控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping( "/menu/" )
public class MenuController {

    // @Autowired
    @Reference
    private IMenuInfoService menuservice;

    /**
     * 菜单dTreeJS
     *
     * @return
     */
    @RequestMapping( "menuleft.do" )
    public String menuleft( Model model, MenuInfo info ) {

        List< MenuInfo > list = menuservice.list( info );
        model.addAttribute( "list", list );

        return "menu/menu_left";
    }


    /**
     * 菜单主要内容
     *
     * @return
     */
    @RequestMapping( "menumain.do" )
    public String menumain( MenuInfo menu, Model model ) {
        //model类是用来给前端JSP传递数据来生成html页面数据用的
        MenuInfo info = null;
        if ( menu != null && menu.getMenuId( ) != null ) {
            if ( menu.getMenuId( ) == 0 ) {
                info = new MenuInfo( );
                info.setMenuId( 0 );
                info.setMenuName( "根节点" );
                info.setParentMenuId( -1 );
            } else {
                info = menuservice.getInfo( menu );
            }
        }
        model.addAttribute( "menu", info );
        return "menu/menu_main";
    }

    /**
     * 菜单首页信息
     *
     * @param
     * @return
     */
    @RequestMapping( "list.do" )
    public String list( ) {
        return "menu/menu_index";
    }

    /**
     * 菜单顶部
     *
     * @return
     */
    @RequestMapping( "menutop.do" )
    public String menutop( ) {
        return "menu/menu_top";
    }

    /**
     * 加载修改添加页面
     *
     * @return
     */
    @RequestMapping( "loadadd.do" )
    public String laodadd( MenuInfo info, Model model ) {
        List< MenuInfo > list = menuservice.list( info );
        model.addAttribute( "list", list );
        model.addAttribute( "menu", info );

        return "menu/menu_add";
    }

    @RequestMapping( "add.do" )
    public String add( MenuInfo info, Model model ) {
        try {
            menuservice.add( info );
        }
        catch ( Exception e ) {
            e.printStackTrace( );
        }

        return "menu/menu_info";

    }

    /**
     * 加载修改修改页面
     *
     * @return
     */
    @RequestMapping( "loadupdate.do" )
    public String loadupdate( ) {
        return "menu/menu_update";
    }
}
