package com.zjsu.dao;

import com.zjsu.bean.RoleInfo;

import java.util.List;

/**
 * *
 * <p>Title:IRoleInfoDAO </p>
 * <p>Description:角色管理的数据访问接口</p>
 * <p>Company:ZJGSU</p>
 *
 * @author pf
 * @version 1.0
 */
public interface IRoleInfoDAO {
    /**
     * 添加角色
     */
    public int add( RoleInfo info );

    /**
     * 修改角色
     *
     * @param info
     * @return
     */
    public int update( RoleInfo info );

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
}
