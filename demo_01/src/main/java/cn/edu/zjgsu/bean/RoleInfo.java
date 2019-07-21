package cn.edu.zjgsu.bean;

import utils.BaseBean;

/**
 *
 * <p>Title:RoleInfo </p>
 * <p>Description:角色管理的实体类</p>
 * <p>Company:ZJGSU</p>
 * @author VB
 * @version 1.0
 */
public class RoleInfo extends BaseBean {
    // 角色编号
    private Integer roleId;
    // 角色名称
    private String roleName;
    //角色描述
    private String roleDesc;
    //标示
    private String roleMark;
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleDesc() {
        return roleDesc;
    }
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    public String getRoleMark() {
        return roleMark;
    }
    public void setRoleMark(String roleMark) {
        this.roleMark = roleMark;
    }
}