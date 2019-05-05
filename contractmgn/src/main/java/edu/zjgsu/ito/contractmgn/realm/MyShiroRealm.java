package edu.zjgsu.ito.contractmgn.realm;

import edu.zjgsu.ito.contractmgn.model.User;
import edu.zjgsu.ito.contractmgn.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();
        User user = userService.getUserByUserName(username);
        if(user!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> list = new HashSet<>();
            if(user.getRole()==1){
                list.add("manager");
            }
            if(user.getRole()==2){
                list.add("accountant");
            }
            if(user.getRole()==3){
                list.add("overallchief");
            }
            if(user.getRole()==4){
                list.add("areachief");
            }
            if(user.getRole()==5){
                list.add("saler");
            }
            if(user.getRole()==6){
                list.add("buyer");
            }
            info.addRoles(list);
            info.addStringPermissions(list);
            return info;
        }else{
            throw new AuthorizationException("user does not exist or deleted");
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)throws UnknownAccountException,LockedAccountException{

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        User user =userService.getUserByUserName(username);

        if(user==null){
            throw new UnknownAccountException("用户名或密码错误");
        }
        if(user.getState()){
            throw new LockedAccountException("用户被锁定");
        }
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),this.getName());

    }


}
