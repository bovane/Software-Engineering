package com.zjsu.bean;

import com.utils.BaseBean;

import java.io.Serializable;

/**
 * <p>Title:RoleInfo </p>
 * <p>Description:角色管理的实体类</p>
 * <p>Company:ZJGSU</p>
 *
 * @author pf
 * @version 1.0
 */

public class RoleInfo extends BaseBean implements Serializable  {

    public RoleInfo( ) {
    }

    // 角色编号
    private Integer roleId;
    // 角色名称
    private String roleName;
    //角色描述
    private String roleDesc;
    //标示
    private String roleMark;

    public Integer getRoleId( ) {
        return roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }

    public String getRoleName( ) {
        return roleName;
    }

    public void setRoleName( String roleName ) {
        this.roleName = roleName;
    }

    public String getRoleDesc( ) {
        return roleDesc;
    }

    public void setRoleDesc( String roleDesc ) {
        this.roleDesc = roleDesc;
    }

    public String getRoleMark( ) {
        return roleMark;
    }

    public void setRoleMark( String roleMark ) {
        this.roleMark = roleMark;
    }

    @Override
    public String toString( ) {
        return "RoleInfo{" + "roleId=" + roleId + ", roleName='" + roleName + '\'' + ", roleDesc='" + roleDesc + '\'' + ", roleMark='" + roleMark + '\'' + '}';
    }
}