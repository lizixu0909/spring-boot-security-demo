package com.sipuang.xianyitong.service.impl;

import com.sipuang.xianyitong.bo.RoleBO;
import com.sipuang.xianyitong.mapper.RoleMapper;
import com.sipuang.xianyitong.model.Resource;
import com.sipuang.xianyitong.model.Role;
import com.sipuang.xianyitong.service.ResourceService;
import com.sipuang.xianyitong.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public List<RoleBO> getByUserId(Integer userId) {
        //查询用户的角色
        Set<Role> roles = roleMapper.selectByUserId(userId);
        return convertToBO(roles);
    }

    @Override
    public List<RoleBO> getAll() {
        List<Role> roles = roleMapper.selectAll();
        return convertToBO(roles);
    }

    /**
     * 转换成BO对象
     *
     * @param roles
     * @return
     */
    private List<RoleBO> convertToBO(Collection<Role> roles) {
        List<RoleBO> bos = new ArrayList<>(roles.size());
        RoleBO bo;
        //根据角色查询资源
        for (Role role : roles) {
            bo = new RoleBO();
            BeanUtils.copyProperties(role, bo);
            Set<Resource> resources = resourceService.getByRoleId(role.getId());
            bo.setResources(resources);
            bos.add(bo);
        }
        return bos;
    }
}
