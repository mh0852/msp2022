package com.mh.web.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.mh.web.security.mapper.TbUserRoleMapper;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.model.TbUserRole;
import com.mh.web.security.service.ITbUserRoleService;
import com.mh.web.security.vo.userItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
@Service
public class TbUserRoleServiceImpl extends MppServiceImpl<TbUserRoleMapper, TbUserRole> implements ITbUserRoleService {

    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;

    @Override
    public List<userItem> getAllUsersWithRole(String username) {
        return tbUserRoleMapper.getAllUsersWithRole(username);
    }


    @Transactional  //当前方法开启事务管理
    @Override
    public void updateUserRoles(Map<String,String> req) {
        String userId = req.get("userId");
        String roleIds = req.get("roleId");
        String[] s = roleIds.split(",");
        if(roleIds.trim()!="") {
            List<TbUserRole> tbUserRoles = new ArrayList<>();
            for (int i = 0; i < s.length; i++) {
                TbUserRole tbUserRole = new TbUserRole();
                tbUserRole.setUserId(Long.valueOf(userId));
                tbUserRole.setRoleId(Long.valueOf(s[i]));
                tbUserRoles.add(tbUserRole);
            }
            this.saveOrUpdateBatchByMultiId(tbUserRoles);
            String ur = this.selectByUserId(Integer.valueOf(userId));
            String[] r = ur.split(",");
            List<String> list = Arrays.asList(s);
            for(int i=0;i<r.length;i++){
                System.out.println(r[i] + "ssss0000");
                if(!list.contains(r[i])){
                    TbUserRole userRole = new TbUserRole();
                    userRole.setRoleId(Long.valueOf(r[i]));
                    userRole.setUserId(Long.valueOf(userId));
                    this.deleteByMultiId(userRole);
                    System.out.println(r[i] + "ssss0000");
                }
            }
        }
        else{
            QueryWrapper<TbUserRole> queryWrapper = new QueryWrapper<>();
            TbUserRole tbUserRole = new TbUserRole();
            tbUserRole.setUserId(Long.valueOf(userId));
            queryWrapper.setEntity(tbUserRole);
            this.tbUserRoleMapper.delete(queryWrapper);
        }
    }

    @Override
    public String selectByUserId(Integer userId) {
        return tbUserRoleMapper.selectByUserId(userId);
    }


}
