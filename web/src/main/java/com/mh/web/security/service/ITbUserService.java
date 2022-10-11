package com.mh.web.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.web.security.model.TbUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface ITbUserService extends IService<TbUser> {

    void deleteUsersWithAuths(List<TbUser> tbuser);

}
