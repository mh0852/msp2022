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
import com.mh.web.security.mapper.TbRoleAuthMapper;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.model.TbRoleAuth;
import com.mh.web.security.model.TbUserRole;
import com.mh.web.security.service.ITbRoleAuthService;
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
public class TbRoleAuthServiceImpl extends MppServiceImpl<TbRoleAuthMapper, TbRoleAuth> implements ITbRoleAuthService {

    @Autowired
    private TbRoleAuthMapper tbRoleAuthMapper;

    @Override
    public List<TbAuth> getAuthsByRoleName(String roleName) {
        return tbRoleAuthMapper.getAuthsByRoleName(roleName);
    }

    @Override
    public List<TbAuth> getAuthsByRoleId(Long roleId) {
        return tbRoleAuthMapper.getAuthsByRoleId(roleId);
    }

    @Transactional
    @Override
    public void updateRoleAuths(Map<String, String> req) {
        String roleId = req.get("roleId");
        String permissionIds = req.get("permissionId");

        String[] s = permissionIds.split(",");
        if(permissionIds.trim()!="") {
            List<TbRoleAuth> tbRoleAuths = new ArrayList<>();
            for (int i = 0; i < s.length; i++) {
                TbRoleAuth tbRoleAuth = new TbRoleAuth();
                tbRoleAuth.setRoleId(Long.valueOf(roleId));
                tbRoleAuth.setAuthId(Long.valueOf(s[i]));
                tbRoleAuths.add(tbRoleAuth);
            }
            this.saveOrUpdateBatchByMultiId(tbRoleAuths);
            String ur = this.selectAuthsByRoleId(Integer.valueOf(roleId));
            String[] r = ur.split(",");
            List<String> list = Arrays.asList(s);
            for(int i=0;i<r.length;i++){
                System.out.println(r[i] + "ssss0000");
                if(!list.contains(r[i])){
                    TbRoleAuth roleAuth = new TbRoleAuth();
                    roleAuth.setAuthId(Long.valueOf(r[i]));
                    roleAuth.setRoleId(Long.valueOf(roleId));
                    this.deleteByMultiId(roleAuth);
                    System.out.println(r[i] + "ssss0000");
                }
            }
        }
        else{
            QueryWrapper<TbRoleAuth> queryWrapper = new QueryWrapper<>();
            TbRoleAuth roleAuth = new TbRoleAuth();
            roleAuth.setRoleId(Long.valueOf(roleId));
            queryWrapper.setEntity(roleAuth);
            this.tbRoleAuthMapper.delete(queryWrapper);
        }

    }

    private String selectAuthsByRoleId(Integer valueOf) {
        return tbRoleAuthMapper.selectAuthsByRoleId(valueOf);
    }
}
