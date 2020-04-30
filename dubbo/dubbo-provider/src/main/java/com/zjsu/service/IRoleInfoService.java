package com.zjsu.service;

import com.zjsu.bean.RoleInfo;

import java.util.List;

/**
 * <p>Title:IRoleInfoService </p>
 * <p>Description:角色管理业务逻辑处理接口</p>
 * <p>Company:ZJGSU</p>
 *
 * @author pf
 * @version 1.0
 */
public interface IRoleInfoService {
    /**
     * 添加角色信息
     *
     * @param info
     */
    void add( RoleInfo info );

    /**
     * 修改角色信息
     *
     * @param info
     */
    public void update( RoleInfo info );

    /**
     * 根据条件查询角色信息
     *
     * @param info
     * @return
     */
    public List< RoleInfo > list( RoleInfo info );

    /**
     * 获取总记录数
     *
     * @param info
     * @return
     */
    public long getcount( RoleInfo info );

    /**
     * 根据条件查询角色信息
     *
     * @param info
     * @return
     */
    public RoleInfo getInfo( RoleInfo info );

    /**
     * 根据编号删除
     *
     * @param roleId
     */
    public void delete( Integer[] roleId );
}