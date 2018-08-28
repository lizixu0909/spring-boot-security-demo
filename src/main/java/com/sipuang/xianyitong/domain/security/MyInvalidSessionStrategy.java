package com.sipuang.xianyitong.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipuang.xianyitong.domain.web.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义session过期处理
 *
 * @author lijun
 * @date 2018-04-20.
 */
@Component
public class MyInvalidSessionStrategy implements InvalidSessionStrategy {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ErrorResult result = new ErrorResult(HttpStatus.UNAUTHORIZED, "用户未登录");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
