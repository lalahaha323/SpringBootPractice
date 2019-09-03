package com.example.mall2.config;

import com.example.mall2.mapper.LogMapper;
import com.example.mall2.pojo.Permission;
import com.example.mall2.pojo.Role_Permission;
import com.example.mall2.pojo.User;
import com.example.mall2.pojo.User_Role;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lala
 */


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String)principalCollection.getPrimaryPrincipal();
        User user = userDao.getByUserName(username);
        for(User_Role user_role : userDao.getRoles(user.getId())){
            for(Role_Permission role_permission: roleDao.getPermissions(user_role.getRoleId())){
                Permission permission = permissionDao.getById(role_permission.getPermissionId());
                authorizationInfo.addStringPermission(permission.getName());
            }
        }
        return authorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();

        User user = logMapper.getByUserName(userName);

        if(user != null){
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
            return authenticationInfo;
        }else{
            //没有此用户
            return null;
        }
    }

}

