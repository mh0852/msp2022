package com.mh.web.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.service.ITbAuthService;
import com.mh.web.security.service.ITbRoleAuthService;
import com.mh.web.security.service.ITbRoleService;
import com.mh.web.security.utils.MenuTree;
import com.mh.web.security.utils.ResponseResult;
import com.mh.web.security.vo.authTreeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AclPermissionController {

    @Autowired
    private ITbRoleAuthService tbRoleAuthService;

    @Autowired
    private ITbAuthService tbAuthService;

    @GetMapping("/admin/acl/permission/toAssign/{roleId}")
    public String getAuthTree(@PathVariable("roleId") String roleId) throws JsonProcessingException {

        List<TbAuth> allAuths = tbAuthService.findAll();

        List<TbAuth> auths = tbRoleAuthService.getAuthsByRoleId(Long.valueOf(roleId));
        System.out.println(auths.toString());
        List<authTreeItem> authTreeItems = new ArrayList<>();

        for(int i = 0;i<allAuths.size();i++){
            authTreeItem authTreeItem = new authTreeItem();
            authTreeItem.setId(String.valueOf(allAuths.get(i).getId()));
            authTreeItem.setGmtCreate(allAuths.get(i).getGmtCreate());
            authTreeItem.setGmtModified(allAuths.get(i).getGmtModified());
            authTreeItem.setCode(allAuths.get(i).getCode());
            authTreeItem.setDeleted(allAuths.get(i).getDeleted().equals("1"));
            authTreeItem.setName(allAuths.get(i).getName());
            authTreeItem.setLevel(Integer.parseInt(allAuths.get(i).getLevel()));
            authTreeItem.setPid(String.valueOf(allAuths.get(i).getPid()));
            authTreeItem.setStatus(allAuths.get(i).getStatus());
            authTreeItem.setToCode(allAuths.get(i).getToCode());
            authTreeItem.setType(Integer.parseInt(allAuths.get(i).getType()));

            if(auths.contains(allAuths.get(i))){
                authTreeItem.setSelect(true);
            }else {
                authTreeItem.setSelect(false);
            }


            authTreeItems.add(authTreeItem);
        }
        //创建权限树
        MenuTree menuTree =new MenuTree(authTreeItems);
        authTreeItems=menuTree.builTree();
//        //转为json看看效果
//        String jsonOutput= JSON.toJSONString(authTreeItems);
//        System.out.println(jsonOutput);


        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("children",authTreeItems);
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));

    }

    @PostMapping("/admin/acl/permission/doAssign")
    private String savePermission(@RequestParam Map<String,String> req) throws JsonProcessingException {
        tbRoleAuthService.updateRoleAuths(req);
        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

}



