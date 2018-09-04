package com.sipuang.xianyitong.system.service;

import com.sipuang.xianyitong.system.bo.UserBO;
import com.sipuang.xianyitong.system.model.User;

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
     * @param id
     * @param roleIds
     */
    void editRoles(Integer id, List<Integer> roleIds);

    void editPass(Integer id, String oldPass, String newPass);
}
