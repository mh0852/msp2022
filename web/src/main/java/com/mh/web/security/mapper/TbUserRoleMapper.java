package com.mh.web.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.mh.web.security.model.TbUserRole;
import com.mh.web.security.vo.userItem;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface TbUserRoleMapper extends MppBaseMapper<TbUserRole> {
    List<userItem> getAllUsersWithRole(String username);
}
