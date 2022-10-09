package com.mh.web.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mh.web.security.model.TbRole;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.service.ITbRoleService;
import com.mh.web.security.utils.EncPassword;
import com.mh.web.security.utils.ResponseResult;
import com.mh.web.security.vo.roleItem;
import com.mh.web.security.vo.userItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AclRoleController {

    @Autowired
    private ITbRoleService tbRoleService;

    @GetMapping("/admin/acl/role/{pageNum}/{pageSize}")
    public String getRoles(@RequestParam(value = "roleName",required = false) String roleName,
                           @PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize) throws JsonProcessingException {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<TbRole> queryWrapper = new QueryWrapper<>();
        if(roleName.trim()!="") {
            TbRole role = new TbRole();
            role.setEname(roleName);
            queryWrapper.setEntity(role);
        }
        List<TbRole> tbRoles = tbRoleService.getBaseMapper().selectList(queryWrapper);
        List<roleItem> roleItems = new ArrayList<>();

        for(int i=0;i<tbRoles.size();i++){
            roleItem rim = new roleItem();
            rim.setId(String.valueOf(tbRoles.get(i).getRoleId()));
            rim.setRoleName(tbRoles.get(i).getEname());
            rim.setGmtCreate(tbRoles.get(i).getCreatetime());
            rim.setGmtModified(tbRoles.get(i).getUpdatetime());
            roleItems.add(rim);
        }

        PageInfo<roleItem> pageInfo = new PageInfo(tbRoles);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("items",roleItems);

        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 添加角色
    @PostMapping("/admin/acl/role/save")
    public String addRole(@RequestBody Map<String,String> req) throws JsonProcessingException {
        String roleName = req.get("roleName");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String localDateTime = formatter.format(date);

        TbRole role = new TbRole();
        role.setEname(roleName);
        role.setCreatetime(localDateTime);
        role.setUpdatetime(localDateTime);

        tbRoleService.getBaseMapper().insert(role);
        FilterSecurityInterceptor f;
        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

}
