package com.zjsu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.utils.Comm;
import com.zjsu.bean.UserInfo;
import com.zjsu.dao.IUserInfoDAO;
import com.zjsu.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = IUserInfoService.class)
// @Service
@Transactional
@Component
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDAO userdao;

    public void setUserdao(IUserInfoDAO userdao) {
        this.userdao = userdao;
    }

    public void add( UserInfo user ) {
        userdao.add( user );
    }

    public UserInfo getUser( UserInfo user ) {
        return userdao.getUser( user );
    }

    public List< UserInfo > list( UserInfo info ) {
        // System.out.println( "UserInfoServiceImpl.list" );
        // System.out.println( "UserInfoServiceImpl.list.info" + info.toString() );
        condition( info );
        // System.out.println( "after UserInfoServiceImpl.list.condition" );
        // System.out.println( "UserInfoServiceImpl.list.userdao.list( info ): " + userdao.list( info ) );

        return userdao.list( info );
    }

    public long getcount( UserInfo info ) {
        condition( info );
        return userdao.getcount( info );
    }

    public void condition( UserInfo user ) {
        if ( user != null ) {
            if ( user.getUserName( ) != null && !user.getUserName( ).equals( "" ) ) {
                user.setUserName( "%" + user.getUserName( ) + "%" );
            }
        }
    }

    public void update( UserInfo user ) {
        userdao.update( user );
    }

    public void delete( Integer[] userId ) {
        for ( Integer id : userId ) {
            UserInfo info = new UserInfo( );
            info.setUserId( id );
            info.setUserMark( Comm.MARK_NO );
            userdao.update( info );
        }
    }
}
