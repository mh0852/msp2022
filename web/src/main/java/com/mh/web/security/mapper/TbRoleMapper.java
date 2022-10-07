package com.mh.web.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.web.security.model.TbRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface TbRoleMapper extends BaseMapper<TbRole> {

    List<TbRole> findRolesByUserName(String username);

}
