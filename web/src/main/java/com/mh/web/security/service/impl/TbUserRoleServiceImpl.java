package com.mh.web.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
import com.mh.web.security.model.TbUserRole;
import com.mh.web.security.service.ITbUserRoleService;
import com.mh.web.security.vo.userItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TbUserRoleServiceImpl extends MppServiceImpl<TbUserRoleMapper, TbUserRole> implements ITbUserRoleService {

    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;

    @Override
    public List<userItem> getAllUsersWithRole(String username) {
        return tbUserRoleMapper.getAllUsersWithRole(username);
    }
}
