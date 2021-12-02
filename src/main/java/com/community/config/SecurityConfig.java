package com.community.config;

import com.community.service.UserService;
import com.community.service.impl.UserServiceImpl;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@EnableWebSecurity  //开启websecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    //请求授权验证
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * .denyAll();    //拒绝访问
         * .authenticated();    //需认证通过
         * .permitAll();    //无条件允许访问
         * 访问权限，添加之后的都可以访问否则会被拦截
         */
        http.authorizeRequests()
                .antMatchers("/register","/login","/toLogin","/kaptcha.jpg","/kaptcha","/havephone","/toLoginfail").permitAll()
                .antMatchers("/*").authenticated()
                .antMatchers("/js/**","/css/**","/images/*","/fonts/**","/**/*.png","/**/*.jpg").permitAll();

        // 登录配置
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/toLogin")
                .loginProcessingUrl("/login") // 登陆表单提交请求
                .defaultSuccessUrl("/index")// 设置默认登录成功后跳转的页面
                .failureUrl("/toLoginfail");

        // 注销配置
        http.headers().contentTypeOptions().disable();
        http.headers().frameOptions().disable(); // 图片跨域
        http.csrf().disable();//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
        http.logout().logoutSuccessUrl("/");

        // 记住我配置
        http.rememberMe().rememberMeParameter("remember");
    }

    // 用户授权验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    // 密码加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}