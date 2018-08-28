package com.sipuang.xianyitong.service.impl;

import com.sipuang.xianyitong.mapper.ResourceMapper;
import com.sipuang.xianyitong.model.Resource;
import com.sipuang.xianyitong.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> getAll() {
        return resourceMapper.selectAll();
    }

    @Override
    public Set<Resource> getByRoleId(Integer roleId) {
        return resourceMapper.selectByRoleId(roleId);
    }

    @Override
    public void add(Resource resource) {
        resourceMapper.insertSelective(resource);
    }

    @Override
    public void edit(Resource resource) {
        resourceMapper.updateByPrimaryKeySelective(resource);
    }

    @Override
    public void del(Integer id) {
        resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Resource get(Integer id) {
        return resourceMapper.selectByPrimaryKey(id);
    }
}
