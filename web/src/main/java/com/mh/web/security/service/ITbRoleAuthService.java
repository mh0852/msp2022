package com.mh.web.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.model.TbRoleAuth;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface ITbRoleAuthService extends IService<TbRoleAuth> {

    List<TbAuth> getAuthsByRoleName(String username);

    List<TbAuth> getAuthsByRoleId(Long roleId);

    void updateRoleAuths(Map<String,String> req);

}
