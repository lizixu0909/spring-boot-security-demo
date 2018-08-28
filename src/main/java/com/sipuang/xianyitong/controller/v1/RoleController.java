package com.sipuang.xianyitong.controller.v1;

import com.sipuang.xianyitong.model.Role;
import com.sipuang.xianyitong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
