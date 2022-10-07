package com.mh.web.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper() ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","登录成功");
        map.put("code",200);
        map.put("authentication",authentication);
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(map);
        response.getWriter().println(s);
    }
}
