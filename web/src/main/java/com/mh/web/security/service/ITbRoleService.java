package com.mh.web.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.model.TbRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface ITbRoleService extends IService<TbRole> {
    List<TbRole> findRolesByUserName(String username);
    List<TbRole> findRolesByUserId(Integer id);
    void delBatchRoles(List<String> ids);
}
