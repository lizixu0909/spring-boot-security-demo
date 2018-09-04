package com.sipuang.xianyitong.system.controller;

import com.sipuang.xianyitong.system.model.Role;
import com.sipuang.xianyitong.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@RestController
@RequestMapping("/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public void add(Role role) {
        roleService.add(role);
    }

    @PutMapping("/role")
    public void edit(Role role) {
        roleService.edit(role);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Integer id) {
        roleService.del(id);
    }

    @GetMapping("/{id}")
    public Role get(@PathVariable Integer id) {
        return roleService.get(id);
    }

    @PostMapping("/edit-resources")
    public void editResources(Integer roleId, @RequestParam("resourceId") List<Integer> resourceIds) {
        roleService.editResources(roleId, resourceIds);
    }
}
