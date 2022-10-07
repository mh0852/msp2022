package com.mh.web.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

// 此过滤器为登录后，后续请求携带token 验证过滤器，通过则放行

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        // 前端直接在请求头存token
        String jwtToken = req.getHeader("token");
        if(jwtToken != null){
            Jws<Claims> jws= Jwts.parser().setSigningKey("menghao")
                    .parseClaimsJws(jwtToken.replace("Bearer","")); // 此处替换Bearer 为header 为authorization 时value 携带前缀，可去除
            Claims claims = jws.getBody();
            String username = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }
}
