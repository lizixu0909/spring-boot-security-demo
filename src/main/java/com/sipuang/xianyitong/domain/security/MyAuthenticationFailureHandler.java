package com.sipuang.xianyitong.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipuang.xianyitong.domain.web.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lijun
 * @date 2018-04-20.
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ErrorResult result = new ErrorResult(HttpStatus.UNAUTHORIZED, "");
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            //用户不存在或密码错误
            result.setMessage("用户不存在或密码错误");
        } else if (e instanceof DisabledException) {
            //用户已被禁用
            result.setMessage("用户被禁用");
        } else if (e instanceof LockedException) {
            //用户已被锁定
            result.setMessage("用户被锁定");
        } else if (e instanceof CredentialsExpiredException) {
            //凭证已过期
            result.setMessage("登录凭证已过期");
        } else if (e instanceof AccountExpiredException) {
            //账号已过期
            result.setMessage("账号已过期");
        } else {
            result.setMessage("发生未知异常，登录失败");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
