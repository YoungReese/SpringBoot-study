package com.ly.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * liyang 2021-02-18
 * shiro相关配置
 */
@Configuration
public class ShiroConfig {

    /**
     * step3：
     *  生成ShiroFilterFactoryBean并关联DefaultWebSecurityManager的对象
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();

        /**
         * 设置安全管理器
         */
        filterBean.setSecurityManager(securityManager);

        /**
         * 登陆拦截
         * 添加shiro的内置过滤器
         *  anon：无需认证即可访问
         *  authc：必须认证了才能访问
         *  user：必须拥有记住我功能才能用
         *  perms：拥有对某个资源的权限才能访问
         *  role：拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/*", "authc"); // 支持通配符

        /**
         * 授权，默认情况下未授权跳转到401（type=Unauthorized, status=401）
         */
//        filterMap.put("/user/add", "perms[user:add]"); // 这个用户需要有add权限才能访问/user/add页面
        filterBean.setFilterChainDefinitionMap(filterMap);

        /**
         * 设置未授权跳转的页面
         */
        filterBean.setUnauthorizedUrl("/noauth");

        /**
         * 如果没有认证权限，默认跳转到404页面，这里改成跳转到登录页面
         */
        filterBean.setLoginUrl("/toLogin");

        return filterBean;
    }

    /**
     * step2：
     *  生成DefaultWebSecurityManager并关联UserRealm的对象
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm); // DefaultWebSecurityManager的对象关联Realm对象
        return securityManager;
    }

    /**
     * step1：
     *  创建Realm对象，需要自定义类
     *  使用@Bean让spring进行托管
     */
    @Bean(name="userRealm") //bean的名字默认就是方法名字，可以不加
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
