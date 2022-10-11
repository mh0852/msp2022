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
import com.mh.web.security.mapper.TbAuthMapper;
import com.mh.web.security.mapper.TbRoleAuthMapper;
import com.mh.web.security.mapper.TbRoleMapper;
import com.mh.web.security.model.TbRole;
import com.mh.web.security.model.TbRoleAuth;
import com.mh.web.security.model.TbUserRole;
import com.mh.web.security.service.ITbRoleAuthService;
import com.mh.web.security.service.ITbRoleService;
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
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

    @Autowired
    private TbRoleMapper roleMapper;

    @Autowired
    private TbRoleAuthMapper roleAuthMapper;

    @Override
    public List<TbRole> findRolesByUserName(String username) {
        return roleMapper.findRolesByUserName(username);
    }

    @Override
    public List<TbRole> findRolesByUserId(Integer id) {
        return roleMapper.findRolesByUserId(id);
    }

    @Transactional
    @Override
    public void delBatchRoles(List<String> ids) {
        roleMapper.deleteBatchIds(ids);

        for(int i=0;i<ids.size();i++){
            QueryWrapper<TbRoleAuth> queryWrapper = new QueryWrapper<>();
            TbRoleAuth tbRoleAuth = new TbRoleAuth();
            tbRoleAuth.setRoleId(Long.valueOf(ids.get(i)));
            queryWrapper.setEntity(tbRoleAuth);
            roleAuthMapper.delete(queryWrapper);
        }

    }
}
