package com.sipuang.xianyitong.system.controller;

import com.sipuang.xianyitong.system.bo.UserBO;
import com.sipuang.xianyitong.domain.web.SessionUtils;
import com.sipuang.xianyitong.system.model.User;
import com.sipuang.xianyitong.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Validated
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserBO getMe() {
        return SessionUtils.getUser();
    }

    /**
     * 编辑用户角色
     *
     * @param roleIds 角色数组
     * @param userId  用户ID
     */
    @PostMapping("/editRoles")
    public void editRoles(Integer userId, @RequestParam("roleId") List<Integer> roleIds) {
        userService.editRoles(userId, roleIds);
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

    /**
     * 更新密码
     *
     * @param oldPass 原密码
     * @param newPass 新密码
     */
    @PostMapping("/editPass")
    public void editPass(@NotEmpty String oldPass, @NotEmpty String newPass) {
        Integer currentUserId = SessionUtils.getUserId();
        userService.editPass(currentUserId, oldPass, newPass);
    }
}
