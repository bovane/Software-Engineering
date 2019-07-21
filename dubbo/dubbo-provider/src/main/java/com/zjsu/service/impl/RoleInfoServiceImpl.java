package com.zjsu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.utils.Comm;
import com.zjsu.bean.RoleInfo;
import com.zjsu.dao.IRoleInfoDAO;
import com.zjsu.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title:RoleInfoServiceImpl </p>
 * <p>Description:角色管理业务逻辑的实现类</p>
 * <p>Company:ZJGSU</p>
 *
 * @author pf
 * @version 1.0
 */
@Service( interfaceClass = IRoleInfoService.class )
@Transactional
@Component
public class RoleInfoServiceImpl implements IRoleInfoService {
    @Autowired
    private IRoleInfoDAO roledao;

/*    public void setRoledao( IRoleInfoDAO roledao ) {
        this.roledao = roledao;
    }*/


    public void add( RoleInfo info ) {
        roledao.add( info );
    }

    public List< RoleInfo > list( RoleInfo info ) {
        condition( info );

        // for test
/*        System.out.println( "before roledao.list( info )" );
        List< RoleInfo > roleInfoList = roledao.list( info );
        System.out.println( "after roledao.list( info )" );
        System.out.println( "roleInfoList: " + roleInfoList.toString( ) );*/

        return roledao.list( info );
    }

    public long getcount( RoleInfo info ) {
        condition( info );
        return roledao.getcount( info );
    }

    /**
     * 查询条件
     *
     * @param info
     */
    public void condition( RoleInfo info ) {
        if ( info != null ) {
            info.setRoleMark( Comm.MARK_YES );
            if ( info.getRoleName( ) != null && !info.getRoleName( ).equals( "" ) ) {
                info.setRoleName( "%" + info.getRoleName( ) + "%" );
            }
        }
    }

    public RoleInfo getInfo( RoleInfo info ) {
        return roledao.getInfo( info );
    }

    public void update( RoleInfo info ) {
        roledao.update( info );
    }

    public void delete( Integer[] roleId ) {
        //可以删除多个角色
        for ( Integer id : roleId ) {
            RoleInfo info = new RoleInfo( );
            info.setRoleId( id );
            //修改为无效表示
            info.setRoleMark( Comm.MARK_NO );
            //其实删除做的只是修改，没有真正删除，否则要做级联删除
            roledao.update( info );
        }
    }
}