package com.sipuang.xianyitong.system.controller;

import com.sipuang.xianyitong.system.model.Resource;
import com.sipuang.xianyitong.system.service.ResourceService;
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
@RequestMapping("/v1/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping
    public void add(Resource resource) {
        resourceService.add(resource);
    }

    @PutMapping
    public void edit(Resource resource) {
        resourceService.edit(resource);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Integer id) {
        resourceService.del(id);
    }

    @GetMapping("/{id}")
    public Resource get(@PathVariable Integer id) {
        return resourceService.get(id);
    }
}
