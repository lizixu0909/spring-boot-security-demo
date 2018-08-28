package com.sipuang.xianyitong.controller;

import com.sipuang.xianyitong.model.User;
import com.sipuang.xianyitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDetails getMe() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public void add(User user) {
        userService.add(user);
    }

    @PutMapping
    public void edit(User user) {
        userService.edit(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Integer id) {
        userService.del(id);
    }
}
