package com.sipuang.xianyitong.service.impl;

import com.sipuang.xianyitong.bo.RoleBO;
import com.sipuang.xianyitong.bo.UserBO;
import com.sipuang.xianyitong.mapper.UserMapper;
import com.sipuang.xianyitong.model.User;
import com.sipuang.xianyitong.service.RoleService;
import com.sipuang.xianyitong.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserBO getByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        UserBO bo = new UserBO();
        BeanUtils.copyProperties(user, bo);
        List<RoleBO> roleBOS = roleService.getByUserId(user.getId());
        bo.setRoles(roleBOS);
        return bo;
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public void edit(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void del(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
