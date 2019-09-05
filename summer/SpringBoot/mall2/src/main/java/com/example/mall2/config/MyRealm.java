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
import org.apache.shiro.crypto.hash.Md5Hash;
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

    /**
     * 用户授权
     * 用户请求有权限要求的接口时要经过此认证
     * 例如我在某个Controller的方法上加了注解@RequiresPermissions(value = "permis[get]"),
     * 那么该用户的角色拥有的资源必须要包含“permis[get]”权限才能访问此接口,
     * "permis[get]"对应文章开头说的表结构那里的系统资源表中的permission_code
     *
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //添加角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取用户名，此用户名实在登录接口里new UsernamePasswordToken()时设置的
        String username = (String)principalCollection.getPrimaryPrincipal();
        User user = logMapper.getByUserName(username);
//        for(User_Role user_role : logMapper.getRoles(user.getId())){
//            for(Role_Permission role_permission: logMapper.getPermissions(user_role.getRoleId())){
//                Permission permission = logMapper.getById(role_permission.getPermissionId());
//                authorizationInfo.addStringPermission(permission.getName());
//            }
//        }
        return authorizationInfo;
    }

    /**
     * 用户认证
     * 调用了subject.login()方法就会进入到这个方法进行具体登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //用户输入的用户名和密码
        String user_name = token.getPrincipal().toString();
        String user_password = new String((char[]) token.getCredentials());
        String salt =user_name;
        Md5Hash md5Hash = new Md5Hash(user_password, salt, 2);
        //数据库找
        User user = logMapper.getByUserName(user_name);
        if(user != null){
            if(md5Hash.equals(user_password)) {
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user_name, user_password, getName());
                authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user_name));
                return authenticationInfo;
            } else {
                System.out.println("密码错误");
                throw new AuthenticationException("密码错误");
            }

        }else{
            System.out.println("用户名错误");
            throw new AuthenticationException("用户名错误");
        }
    }

}

