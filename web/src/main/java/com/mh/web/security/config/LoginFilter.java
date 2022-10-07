package com.mh.web.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// 20221007 该过滤器可用来修改前端登录兼容传json，然后spring security(原本只能处理表单)处理json,
//            但使用JWT TOKEN 后，增加JWT 登录过滤器，直接使用即可，此过滤器不使用

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 1. 判断是否是post请求方式
        if(!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 2. 判断是否是json格式请求类型
        if(request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            // 3. 从json数据中获取用户输入用户名和密码认证：{"uname":"xxx","password":"xxx"}
            try {
                Map<String,String> userInfo = new HashMap<>();
                userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                String username = userInfo.get(getUsernameParameter());
                String password = userInfo.get(getPasswordParameter());
                System.out.println("username = " + username);
                System.out.println("password = " + password);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
                setDetails(request,authenticationToken);
                return this.getAuthenticationManager().authenticate(authenticationToken);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // 4. 如果不是json格式，那么按照父类的表单登录逻辑处理
        return super.attemptAuthentication(request,response);
    }
}
