package com.mh.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description SpringSecurity安全框架配置
 */
@Configuration
@EnableWebSecurity//开启Spring Security的功能
////prePostEnabled属性决定Spring Security在接口前注解是否可用@PreAuthorize,@PostAuthorize等注解,设置为true,会拦截加了这些注解的接口
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    //处理登录时转表单为json的过滤器，不使用
//    @Bean
//    public LoginFilter loginFilter() throws Exception {
//        LoginFilter loginFilter = new LoginFilter();
//        loginFilter.setFilterProcessesUrl("/doLogin");
////        loginFilter.setUsernameParameter("username");
////        loginFilter.setPasswordParameter("password");
//        loginFilter.setAuthenticationManager(authenticationManager());
//        loginFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
//        loginFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
//        return loginFilter;
//    }


    @Autowired
    private UrlFilterSecurityInterceptor urlFilterSecurityInterceptor;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 基于内存的方式，创建两个用户admin/123456，user/123456
         * */
//        auth.inMemoryAuthentication()
//                .withUser("admin")//用户名
//                .password(passwordEncoder().encode("admin"))//密码
//                .roles("admin");//角色
//        auth.inMemoryAuthentication()
//                .withUser("user")//用户名
//                .password(passwordEncoder().encode("user"))//密码
//                .roles("user");//角色
        /**
         * 基于数据库自定义
         */
        auth.authenticationProvider(daoAuthenticationProvider());
       // daoAuthenticationProvider 中已指定customUserDetailsService
//        auth.userDetailsService(customUserDetailsService);
    }
    /**
     * 指定加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling().accessDeniedPage("/error.html");//配置没有权限访问跳转的自定义页面
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/login.html").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html") //跳转登录页面
//                .loginProcessingUrl("/admin/acl/index/login")//带数据访问登录接口 默认判断账户密码正确跳转successForwardUrl（post请求）
//                .usernameParameter("username") //默认就是username,可通过配置改
//                .passwordParameter("password") //默认password
//                .defaultSuccessUrl("/index") //跳转登录前想到的路径，可配置和successForwardUrl一样只跳指定路径
//                .successForwardUrl("/index") //跳转到index
//                .failureForwardUrl("/f2") //错误跳转
//                .failureUrl("/f1")
                .permitAll()
                .and()
                .logout()//默认就是/logout
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST")) //自定义注销请求
//                .logoutSuccessUrl("/index")
                .deleteCookies() //清理cookie
//                .clearAuthentication(true) //清除认证信息和使 HttpSession 失效,不配默认就是ture
//                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()//添加异常处理过滤器
                .accessDeniedHandler(new RestfulAccessDeniedHandler())//访问拒绝处理器
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //前后端分离的时候配置成STATELESS，非前后端分离的需要配置成ALWAYS
//                .maximumSessions(1)//只能登一个
//                .maxSessionsPreventsLogin(true)  //登录之后其他新的登录禁止，不配则是踢掉原来的登录
//                .sessionRegistry(sessionRegistry())
        ;
        http.addFilterBefore(urlFilterSecurityInterceptor, FilterSecurityInterceptor.class); // 自定义过滤器实现读权限表判断访问权限  (暂时先不用这个判断权限)
        http.addFilterBefore(new JwtLoginFilter("/admin/acl/index/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class);

    }
//    ExceptionTranslationFilter
//    SessionManagementFilter
//    @Autowired
//    FindByIndexNameSessionRepository sessionRepository;
//    @Bean
//    SpringSessionBackedSessionRegistry sessionRegistry() {
//        return new SpringSessionBackedSessionRegistry(sessionRepository);
//    }
@Bean
public AuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setHideUserNotFoundExceptions(false); // 配置false 补货用户不存在异常，否则抛bad credentials
    return daoAuthenticationProvider;
}
//    @Bean
//    RoleHierarchy roleHierarchy() {
//        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
//        hierarchy.setHierarchy("ROLE_admin > ROLE_user"); //角色继承
//        return hierarchy;
//    }

}