package com.ly.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 利用拦截器原理
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 请求授权规则
        // 首页所有人可以访问，功能页只有对应权限的人可以访问（链式编程）
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 没有权限跳转到我们自己写的登录页面上
        http.formLogin().loginPage("/toLogin");

        /**
         * 注销 -> 跳到首页 http.logout().logoutSuccessUrl("/toLogin");
         *
         * 关闭跨站请求伪造功能，防止get请求攻击
         * 这里如果不关闭，那么上面logout()会出现 404错误！
         */
        http.logout().logoutSuccessUrl("/");
        http.csrf().disable();

        // 开启记住我功能，自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember");
    }

    /**
     * 认证 springboot 2.1.x 可以直接使用
     * <p>
     * 密码不加密，报错：There was an unexpected error (type=Internal Server Error, status=500).
     * 解决方法：使用加密方法passwordEncoder(new xxx)加密
     * <p>
     * 注：spring security 5.0+ 新增了很多的加密方法
     * <p>
     * 在spring security 5.4中书写格式不再这样了，当然也支持这种写法
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("12345678")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("12345678")).roles("vip2", "vip3")
                .and()
                .withUser("sy").password(new BCryptPasswordEncoder().encode("12345678")).roles("vip1")
                .and()
                .withUser("py").password(new BCryptPasswordEncoder().encode("12345678")).roles("vip2")
                .and()
                .withUser("mm").password(new BCryptPasswordEncoder().encode("12345678")).roles("vip3");
    }
}
