package com.mh.web.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mh.web.security.model.AuthInfo;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.service.ITbAuthService;
import com.mh.web.security.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ITbUserService userService;
    @Autowired
    private ITbAuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        TbUser user = userService.getBaseMapper().selectOne(queryWrapper);
        if (user != null) {
            List<TbAuth> auths = authService.findAuthsByUserId(user.getId().intValue());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (TbAuth auth : auths) {
                if (auth != null && auth.getCode()!=null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(auth.getCode());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            System.out.println(user.getUsername() + " " + user.getPassword()+ " " + grantedAuthorities);
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("用户: " + username + " do not exist!");
        }
    }
}
