package com.sipuang.xianyitong.service;

import com.sipuang.xianyitong.bo.RoleBO;

import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface RoleService {
    /**
     * 根据用户ID查询角色
     *
     * @param userId
     */
    List<RoleBO> getByUserId(Integer userId);

    List<RoleBO> getAll();
}
