package com.sipuang.xianyitong.service;

import com.sipuang.xianyitong.bo.RoleBO;
import com.sipuang.xianyitong.model.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@CacheConfig(cacheNames = "role")
public interface RoleService {
    /**
     * 根据用户ID查询角色
     *
     * @param userId
     */
    List<RoleBO> getByUserId(Integer userId);

    /**
     * 为防止每次访问url都去访问数据库，将该接口加上缓存
     *
     * @return
     */
    @Cacheable
    List<RoleBO> getAll();

    @CachePut
    void add(Role role);

    void edit(Role role);

    @CacheEvict
    void del(Integer id);

    Role get(Integer id);
}
