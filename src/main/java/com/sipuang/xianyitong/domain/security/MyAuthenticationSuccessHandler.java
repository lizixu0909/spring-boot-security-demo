package com.sipuang.xianyitong.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipuang.xianyitong.system.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功使用的处理器
 *
 * @author lijun
 * @date 2018-04-20.
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserBO userBO = (UserBO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        response.setContentType("application/json;charset=UTF-8"); // 响应类型
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(userBO));
        out.flush();
        out.close();
    }
}