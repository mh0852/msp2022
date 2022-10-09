package com.mh.web.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.mh.web.security.model.TbUserRole;
import com.mh.web.security.vo.userItem;

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
public interface ITbUserRoleService extends IMppService<TbUserRole> {

    List<userItem> getAllUsersWithRole(String username);

    void updateUserRoles(Map<String,String> tbUserRoles);

    String selectByUserId(Integer userId);

}
