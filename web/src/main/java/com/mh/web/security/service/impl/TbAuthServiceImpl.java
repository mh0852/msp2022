package com.mh.web.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.web.security.mapper.TbAuthMapper;
import com.mh.web.security.model.TbAuth;
import com.mh.web.security.service.ITbAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
@Service
public class TbAuthServiceImpl extends ServiceImpl<TbAuthMapper, TbAuth> implements ITbAuthService {

    @Autowired
    private TbAuthMapper authMapper;

    @Override
    public List<TbAuth> findAll() {
        return authMapper.findAll();
    }

    @Override
    public List<TbAuth> findAuthsByUserId(int userId) {
        return authMapper.findAuthsByUserId(userId);
    }
}
