package com.sipuang.xianyitong.domain.web;

import com.sipuang.xianyitong.model.User;
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

    private static final String USER_SESSION_KEY = "user";

    public static void putUser(User user) {
        getSession().setAttribute(USER_SESSION_KEY, user);
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

    public static void removeUser() {
        getSession().removeAttribute(USER_SESSION_KEY);
    }

}
