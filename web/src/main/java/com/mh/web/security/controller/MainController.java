package com.mh.web.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mh.web.security.model.TbRole;
import com.mh.web.security.service.ITbRoleService;
import com.mh.web.security.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MainController {

    @Autowired
    private ITbRoleService tbRoleService;

    @GetMapping("/admin/acl/index/info")
    public String getUserInfo(String token) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("&&&&&" + username );
        Collection<? extends GrantedAuthority> routes = authentication.getAuthorities();

        List<String> s = new ArrayList<>();
        for(Iterator<? extends GrantedAuthority> iter = routes.iterator(); iter.hasNext();){
            s.add(iter.next().toString());
        }
        String[] rts = new String[s.size()];
        for(int i = 0;i< s.size();i++){
            rts[i] = s.get(i);
        }


        List<TbRole> roles = tbRoleService.findRolesByUserName(username);
        String[] ros = new String[roles.size()];
        for(int i = 0;i< roles.size();i++){
            TbRole role = roles.get(i);
            ros[i] = role.getEname();
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("routes",rts);
        dataMap.put("name",username);
        dataMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        dataMap.put("buttons",null);
        dataMap.put("roles",ros);




        System.out.println(new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true)));


        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }
}
