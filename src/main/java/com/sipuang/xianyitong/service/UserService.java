package com.sipuang.xianyitong.service;

import com.sipuang.xianyitong.bo.UserBO;
import com.sipuang.xianyitong.model.User;

import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface UserService {

    UserBO getByUsername(String username);

    void add(User user);

    void edit(User user);

    User get(Integer id);

    void del(Integer id);

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    void editRoles(Integer userId, List<Integer> roleIds);
}
