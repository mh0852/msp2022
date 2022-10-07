package com.mh.web.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.utils.ResponseResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 此为JWT登录过滤器，用于登录生成token 及处理跳转

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {


    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        TbUser user = new ObjectMapper().readValue(req.getInputStream(),TbUser.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities(); //获取登录用户角色
        StringBuffer sb = new StringBuffer();
        for (GrantedAuthority authority : authorities){
            sb.append(authority.getAuthority()).append(",");
        }
        String jwt = Jwts.builder()
                .claim("authorities",sb)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis()+60*60*100))
                .signWith(SignatureAlgorithm.HS512,"menghao")
                .compact();

        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        out.write(new ObjectMapper().writeValueAsString(new ResponseResult(20000,"登录成功",map,true)));
        out.flush();
        out.close();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        Map<String,String> map = new HashMap<>();
        map.put("msg","登录失败");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
}
