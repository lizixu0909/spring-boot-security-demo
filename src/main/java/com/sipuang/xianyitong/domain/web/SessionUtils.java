package com.sipuang.xianyitong.domain.web;

import com.sipuang.xianyitong.system.bo.UserBO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 用户工具类
 *
 * @author lijun
 * @date 2018-04-20.
 */
public class SessionUtils {


    public static UserBO getUser() {
        return (UserBO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Integer getUserId() {
        return getUser().getId();
    }

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getSession();
    }

}
