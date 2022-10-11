package com.mh.web.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.model.TbRoleAuth;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface TbRoleAuthMapper extends MppBaseMapper<TbRoleAuth> {
    List<TbAuth> getAuthsByRoleName(String roleName);

    List<TbAuth> getAuthsByRoleId(Long roleId);

    String selectAuthsByRoleId(Integer valueOf);
}
