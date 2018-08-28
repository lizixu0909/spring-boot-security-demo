package com.sipuang.xianyitong.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipuang.xianyitong.domain.web.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录后无权限处理器
 *
 * @author lijun
 * @date 2018-04-20.
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //判断是否是异步请求
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ErrorResult result = new ErrorResult(HttpStatus.FORBIDDEN, "无权限访问");
        out.write(objectMapper.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
