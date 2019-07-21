package com.zjsu.bean;

import java.io.Serializable;

/**
 * 菜单管理的实体类
 *
 * @author Administrator
 */
public class MenuInfo implements Serializable  {

    public MenuInfo( ) {
    }

    /**
     * 菜单编号
     */
    private Integer menuId;
    /***
     * 菜单的父级id
     */
    private Integer parentMenuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单URL地址
     */
    private String menuUrl;
    /**
     * 父级菜单名称
     */
    private String parentName;

    public String getParentName( ) {
        return parentName;
    }

    public void setParentName( String parentName ) {
        this.parentName = parentName;
    }


    public Integer getMenuId( ) {
        return menuId;
    }

    public void setMenuId( Integer menuId ) {
        this.menuId = menuId;
    }

    public Integer getParentMenuId( ) {
        return parentMenuId;
    }

    public void setParentMenuId( Integer parentMenuId ) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuName( ) {
        return menuName;
    }

    public void setMenuName( String menuName ) {
        this.menuName = menuName;
    }

    public String getMenuUrl( ) {
        return menuUrl;
    }

    public void setMenuUrl( String menuUrl ) {
        this.menuUrl = menuUrl;
    }
}