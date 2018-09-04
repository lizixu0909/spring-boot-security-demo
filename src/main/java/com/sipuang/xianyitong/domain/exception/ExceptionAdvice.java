package com.sipuang.xianyitong.domain.exception;

import com.sipuang.xianyitong.domain.web.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author: laoli
 * @date: 2017-12-29 22:13
 * @Description: 异步全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class,
            BindException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class,
            ValidationException.class
    })
    public ErrorResult handleMethodArgumentNotValidException(Exception e) {
        log.error("参数验证失败：", e);
        String message = "";
        if (e instanceof MethodArgumentNotValidException || e instanceof BindException) {
            BindingResult result;
            if (e instanceof MethodArgumentNotValidException) {
                result = ((MethodArgumentNotValidException) e).getBindingResult();
            } else {
                result = ((BindException) e).getBindingResult();
            }
            FieldError error = result.getFieldError();
            assert error != null;
            String field = error.getField();
            String code = error.getDefaultMessage();
            message = String.format("%s:%s", field, code);
        } else {
            if (e instanceof HttpMessageNotReadableException) {
                message = "参数解析失败";
            } else if (e instanceof MissingServletRequestParameterException) {
                MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
                message = ex.getParameterName() + ":" + "缺少请求参数";
            } else if (e instanceof ConstraintViolationException) {
                ConstraintViolationException ex = (ConstraintViolationException) e;
                Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
                ConstraintViolation<?> violation = violations.iterator().next();
                PathImpl path = (PathImpl) violation.getPropertyPath();
                String p = path.getLeafNode().getName();
                message = p + ":" + violation.getMessage();
            } else if (e instanceof ValidationException) {
                message = "参数验证失败: " + e.getMessage();
            }
        }
        return new ErrorResult(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ErrorResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return new ErrorResult(HttpStatus.METHOD_NOT_ALLOWED, "当前接口不支持【" + e.getMethod() + "】方法！");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ErrorResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型：", e);
        return new ErrorResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "不支持当前媒体类型");
    }

    /**
     * 自定义的业务错误，返回200
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceErrorException.class)
    public ErrorResult handleServiceErrorException(ServiceErrorException e) {
        log.error(e.getMessage());
        return new ErrorResult(e.getMessage());
    }

    /**
     * 处理系统异常
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult handleException(Exception ex) {
        log.error("发生未知异常:  ", ex);
        return new ErrorResult(HttpStatus.INTERNAL_SERVER_ERROR, "系统发生未知异常");
    }
}
