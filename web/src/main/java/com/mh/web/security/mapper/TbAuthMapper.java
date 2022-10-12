package com.mh.web.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.web.security.model.TbAuth;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface TbAuthMapper extends BaseMapper<TbAuth> {
    List<TbAuth> findAll();
    List<TbAuth> findAuthsByUserId(int userId);
    List<TbAuth> findAuthsByUserName(String username);
}
