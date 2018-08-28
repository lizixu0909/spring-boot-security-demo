package com.sipuang.xianyitong.service;

import com.sipuang.xianyitong.model.Resource;

import java.util.List;
import java.util.Set;

/**
 * @author lijun
 * @date 2018-04-20.
 */
public interface ResourceService {
    List<Resource> getAll();

    Set<Resource> getByRoleId(Integer roleId);
}