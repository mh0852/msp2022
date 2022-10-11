package com.mh.web.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mh.web.security.model.TbRole;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.service.ITbRoleService;
import com.mh.web.security.service.ITbUserRoleService;
import com.mh.web.security.service.ITbUserService;
import com.mh.web.security.utils.EncPassword;
import com.mh.web.security.utils.ResponseResult;
import com.mh.web.security.utils.TimeGet;
import com.mh.web.security.vo.roleItem;
import com.mh.web.security.vo.userItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AclUserController {

    @Autowired
    private ITbRoleService tbRoleService;

    @Autowired
    private ITbUserService tbUserService;

    @Autowired
    private ITbUserRoleService tbUserRoleService;

    // 获取用户信息
    @GetMapping("/admin/acl/index/info")
    public String getUserInfo() throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

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
            ros[i] = role.getRoleName();
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("routes",rts);
        dataMap.put("name",username);
        dataMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        dataMap.put("buttons",null);
        dataMap.put("roles",ros);

        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 注销
    @PostMapping("/admin/acl/index/logout")
    public String logout() throws JsonProcessingException {
        //
        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));

    }

    // 获取用户列表
    @GetMapping("/admin/acl/user/{pageNum}/{pageSize}")
    public String getUsers(
            @RequestParam(value = "username",required = false) String username,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) throws JsonProcessingException {
        PageHelper.startPage(pageNum,pageSize);
        username = "%" + username + "%";
        List<userItem> userItems = tbUserRoleService.getAllUsersWithRole(username);

        PageInfo<userItem> pageInfo = new PageInfo(userItems);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",pageInfo.getTotal());
        dataMap.put("items",userItems);
//        System.out.println("分页数据" + userItems.toString());

        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 添加用户
    @PostMapping("/admin/acl/user/save")
    public String addUser(@RequestBody Map<String,String> req) throws JsonProcessingException {
        String username = req.get("username");
        String nickName = req.get("nickName");
        String password = req.get("password");

        TbUser user = new TbUser();
        user.setUsername(username);
        user.setNickName(nickName);
        user.setPassword(EncPassword.passwordEncoder().encode(password));
        user.setGmtCreate(TimeGet.getCurrentTime());
        user.setGmtModified(TimeGet.getCurrentTime());

        tbUserService.getBaseMapper().insert(user);

        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 批量删除用户
    @DeleteMapping("/admin/acl/user/batchRemove")
    private String delBatchUser(@RequestBody List<String> body) throws JsonProcessingException {
        List<TbUser> tbUsers = new ArrayList<>();
        for(int i=0;i<body.size();i++){
            TbUser user = new TbUser();
            user.setId(Long.valueOf(body.get(i)));
            tbUsers.add(user);
        }
        tbUserService.deleteUsersWithAuths(tbUsers);

        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 删除单个用户
    @DeleteMapping("/admin/acl/user/remove/{id}")
    private String delUser(@PathVariable("id") String id) throws JsonProcessingException {
        TbUser user = new TbUser();
        user.setId(Long.valueOf(id));

        tbUserService.deleteUsersWithAuths(Collections.singletonList(user));

        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 更新用户信息
    @PutMapping("/admin/acl/user/update")
    private String updateUser(@RequestBody userItem userItem) throws JsonProcessingException {
        System.out.println(userItem.toString());
        TbUser user = new TbUser();
        user.setId(Long.valueOf(userItem.getId()));
        user.setUsername(userItem.getUsername());
        user.setPassword(userItem.getPassword());
        user.setGmtModified(TimeGet.getCurrentTime());
        user.setNickName(TimeGet.getCurrentTime());

        tbUserService.getBaseMapper().updateById(user);

        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    // 修改用户角色（获取用户角色及系统所有角色列表）
    @GetMapping("/admin/acl/user/toAssign/{id}")
    private String allRolesList(@PathVariable("id") String id) throws JsonProcessingException {
        List<TbRole> tbRoles = tbRoleService.findRolesByUserId(Integer.valueOf(id));
        List<TbRole> allTbRoles = tbRoleService.getBaseMapper().selectList(new QueryWrapper<>());

        List<roleItem> assignRoles = new ArrayList<>();
        List<roleItem> allRolesList = new ArrayList<>();
        for(int i=0;i<tbRoles.size();i++){
            roleItem ri = new roleItem();
            ri.setId(String.valueOf(tbRoles.get(i).getId()));
            ri.setRoleName(tbRoles.get(i).getRoleName());
            assignRoles.add(ri);
        }
        for(int i=0;i<allTbRoles.size();i++){
            roleItem ri = new roleItem();
            ri.setId(String.valueOf(allTbRoles.get(i).getId()));
            ri.setRoleName(allTbRoles.get(i).getRoleName());
            allRolesList.add(ri);
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("assignRoles",assignRoles);
        dataMap.put("allRolesList",allRolesList);

        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }


    // 修改用户角色（获取用户角色及系统所有角色列表）
    @PostMapping("/admin/acl/user/doAssign")
    public String updateUserRoles(@RequestParam Map<String,String> req) throws JsonProcessingException {
        tbUserRoleService.updateUserRoles(req);
        Map<String, Object> dataMap = new HashMap<>();
        return new ObjectMapper().writeValueAsString(new ResponseResult(20000,"成功",dataMap,true));
    }

    public static void main(String[] args) {
        String[] r = {"aa","bb","cc"};
        List<String> list = Arrays.asList(r);
        String[] b = {"aa","dd"};
        System.out.println((list.contains(b[0])));
    }

}
