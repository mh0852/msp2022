package com.mh.web.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.web.security.mapper.TbUserMapper;
import com.mh.web.security.model.TbUser;
import com.mh.web.security.service.ITbUserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Override
    public boolean save(TbUser entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<TbUser> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<TbUser> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    public boolean remove(Wrapper<TbUser> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(TbUser entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean update(Wrapper<TbUser> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    public boolean update(TbUser entity, Wrapper<TbUser> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<TbUser> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    public TbUser getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public List<TbUser> listByIds(Collection<? extends Serializable> idList) {
        return super.listByIds(idList);
    }

    @Override
    public List<TbUser> listByMap(Map<String, Object> columnMap) {
        return super.listByMap(columnMap);
    }

    @Override
    public TbUser getOne(Wrapper<TbUser> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public int count(Wrapper<TbUser> queryWrapper) {
        return super.count(queryWrapper);
    }

    @Override
    public List<TbUser> list(Wrapper<TbUser> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public List<TbUser> list() {
        return super.list();
    }

    @Override
    public <E extends IPage<TbUser>> E page(E page, Wrapper<TbUser> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    public <E extends IPage<TbUser>> E page(E page) {
        return super.page(page);
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<TbUser> queryWrapper) {
        return super.listMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return super.listMaps();
    }

    @Override
    public List<Object> listObjs() {
        return super.listObjs();
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return super.listObjs(mapper);
    }

    @Override
    public List<Object> listObjs(Wrapper<TbUser> queryWrapper) {
        return super.listObjs(queryWrapper);
    }

    @Override
    public <V> List<V> listObjs(Wrapper<TbUser> queryWrapper, Function<? super Object, V> mapper) {
        return super.listObjs(queryWrapper, mapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<TbUser> queryWrapper) {
        return super.pageMaps(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return super.pageMaps(page);
    }

    @Override
    public QueryChainWrapper<TbUser> query() {
        return super.query();
    }

    @Override
    public LambdaQueryChainWrapper<TbUser> lambdaQuery() {
        return super.lambdaQuery();
    }

    @Override
    public KtQueryChainWrapper<TbUser> ktQuery() {
        return super.ktQuery();
    }

    @Override
    public KtUpdateChainWrapper<TbUser> ktUpdate() {
        return super.ktUpdate();
    }

    @Override
    public UpdateChainWrapper<TbUser> update() {
        return super.update();
    }

    @Override
    public LambdaUpdateChainWrapper<TbUser> lambdaUpdate() {
        return super.lambdaUpdate();
    }

    @Override
    public boolean saveOrUpdate(TbUser entity, Wrapper<TbUser> updateWrapper) {
        return super.saveOrUpdate(entity, updateWrapper);
    }
}
