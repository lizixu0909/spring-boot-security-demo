package com.sipuang.xianyitong.system.service.impl;

import com.sipuang.xianyitong.system.bo.RoleBO;
import com.sipuang.xianyitong.system.bo.UserBO;
import com.sipuang.xianyitong.domain.exception.ServiceErrorException;
import com.sipuang.xianyitong.system.mapper.UserMapper;
import com.sipuang.xianyitong.system.model.User;
import com.sipuang.xianyitong.system.service.RoleService;
import com.sipuang.xianyitong.system.service.UserService;
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

    @Override
    public void editRoles(Integer id, List<Integer> roleIds) {
        //判断用户是否存在
        final boolean exists = userMapper.existsWithPrimaryKey(id);
        if (!exists) {
            throw new ServiceErrorException("用户不存在");
        }
        userMapper.deleteRolesByUserId(id);
        userMapper.insertRoles(id, roleIds);
    }

    @Override
    public void editPass(Integer id, String oldPass, String newPass) {
        User user = userMapper.selectByPrimaryKey(id);
        //匹配密码
        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new ServiceErrorException("原密码错误");
        }
        user.setPassword(passwordEncoder.encode(newPass));
        userMapper.updateByPrimaryKeySelective(user);
    }
}
