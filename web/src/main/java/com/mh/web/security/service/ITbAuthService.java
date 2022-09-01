package com.mh.web.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.web.security.model.TbAuth;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
public interface ITbAuthService extends IService<TbAuth> {

    public List<TbAuth> findAll();
    public List<TbAuth> findByAdminUserId(int userId);

}
