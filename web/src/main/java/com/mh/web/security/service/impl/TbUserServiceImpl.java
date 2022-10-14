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
import com.mh.web.security.mapper.TbUserMapper;
import com.mh.web.security.mapper.TbUserRoleMapper;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.model.TbUserRole;
import com.mh.web.security.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;

    @Transactional
    @Override
    public void deleteUsersWithAuths(List<TbUser> tbuser) {
        for(int i=0;i<tbuser.size();i++){
            tbUserMapper.delete(new QueryWrapper<TbUser>().setEntity(tbuser.get(i)));
            TbUserRole tbUserRole = new TbUserRole();
            tbUserRole.setUserId(tbuser.get(i).getId());
            tbUserRoleMapper.delete(new QueryWrapper<TbUserRole>().setEntity(tbUserRole));
        }
    }

    @Override
    public TbUser findUserByUserName(String username) {
        return tbUserMapper.findUserByUserName(username);
    }
}
