package com.mh.web.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","登录失败:"+exception.getMessage());
        map.put("code",-1);
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(map);
        response.getWriter().println(s);
    }
}
