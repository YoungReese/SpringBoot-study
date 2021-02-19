package com.ly.config;

import com.ly.pojo.User;
import com.ly.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * liyang 2021-02-18
 * 自定义的realm，继承AuthorizingRealm，重写相关方法
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权 => doGetAuthorizationInfo");
        /**
         * todo：给用户授予权限
         */
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");
//        return info;


        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证 => doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        /**
         * 用户名和密码：数据库中取，然后进行验证
         */
        User user = userService.getUserByName(userToken.getUsername());
        if (user == null) {
            return null; // 表示抛出一个 UnknownAccountException 异常
        }

        /**
         * 密码认证，shiro做，个人不用操作，防止密码泄漏
         * 可以加密：有MD5，MD5盐值加密等
         */
        return new SimpleAuthenticationInfo("", user.getPwd(), "");
    }
}
