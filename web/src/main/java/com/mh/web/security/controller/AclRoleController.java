package com.mh.web.security.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.model.TbRole;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.service.ITbRoleAuthService;
import com.mh.web.security.service.ITbRoleService;
import com.mh.web.security.utils.EncPassword;
import com.mh.web.security.utils.MenuTree;
import com.mh.web.security.utils.ResponseResult;
import com.mh.web.security.utils.TimeGet;
import com.mh.web.security.vo.authTreeItem;
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

    @Autowired
    private ITbRoleAuthService tbRoleAuthService;

    @GetMapping("/admin/acl/role/{pageNum}/{pageSize}")
    public String getRoles(@RequestParam(value = "roleName",required = false) String roleName,
                           @PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<TbRole> queryWrapper = new QueryWrapper<>();
        if(roleName.trim()!="") {
            TbRole role = new TbRole();
            role.setRoleName(roleName);
            queryWrapper.setEntity(role);
        }
        List<TbRole> tbRoles = tbRoleService.getBaseMapper().selectList(queryWrapper);
        List<roleItem> roleItems = new ArrayList<>();

        for(int i=0;i<tbRoles.size();i++){
            roleItem rim = new roleItem();
            rim.setId(String.valueOf(tbRoles.get(i).getId()));
            rim.setRoleName(tbRoles.get(i).getRoleName());
            rim.setGmtCreate(tbRoles.get(i).getGmtCreate());
            rim.setGmtModified(tbRoles.get(i).getGmtModified());
            roleItems.add(rim);
        }

        PageInfo<roleItem> pageInfo = new PageInfo(tbRoles);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("items",roleItems);

        return JSONObject.toJSONString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 添加角色
    @PostMapping("/admin/acl/role/save")
    public String addRole(@RequestBody Map<String,String> req){
        String roleName = req.get("roleName");

        TbRole role = new TbRole();
        role.setRoleName(roleName);
        role.setGmtCreate(TimeGet.getCurrentTime());
        role.setGmtModified(TimeGet.getCurrentTime());

        tbRoleService.getBaseMapper().insert(role);
        FilterSecurityInterceptor f;
        Map<String, Object> dataMap = new HashMap<>();
        return JSONObject.toJSONString(new ResponseResult(20000,"成功",dataMap,true));
    }


    // 修改角色信息
    @PutMapping("/admin/acl/role/update")
    private String updateRole(@RequestBody Map<String,String> req){

        TbRole role = new TbRole();
        role.setId(Long.valueOf(req.get("id")));
        role.setRoleName(req.get("roleName"));
        role.setGmtModified(TimeGet.getCurrentTime());
        tbRoleService.getBaseMapper().updateById(role);

        Map<String, Object> dataMap = new HashMap<>();
        return JSONObject.toJSONString(new ResponseResult(20000,"成功",dataMap,true));
    }


    // 批量删除角色
    @DeleteMapping("/admin/acl/role/batchRemove")
    private String delBatchRoles(@RequestBody List<String> body){
        tbRoleService.delBatchRoles(body);
        Map<String, Object> dataMap = new HashMap<>();
        return JSONObject.toJSONString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 删除单个角色
    @DeleteMapping("/admin/acl/role/remove/{id}")
    private String delUser(@PathVariable("id") String id){
        tbRoleService.delBatchRoles(Collections.singletonList(id));
        Map<String, Object> dataMap = new HashMap<>();
        return JSONObject.toJSONString(new ResponseResult(20000,"成功",dataMap,true));
    }


}
