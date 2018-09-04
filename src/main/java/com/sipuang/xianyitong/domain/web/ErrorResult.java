package com.sipuang.xianyitong.domain.web;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 错误时返回的对象
 *
 * @author lijun
 * @date 2018-04-20.
 */
@Data
public class ErrorResult {
    /**
     * 错误状态码
     */
    private Integer status;
    /**
     * 错误
     */
    private String error;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 当前请求的地址
     */
    private String path;

    public ErrorResult(String message) {
        this.status = 200;
        this.error = "Custom Service Error";
        this.message = message;
        this.path = getCurrentPath();
    }

    public ErrorResult(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = getCurrentPath();
    }

    private String getCurrentPath() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest().getServletPath();
        }
        return "";
    }
}
