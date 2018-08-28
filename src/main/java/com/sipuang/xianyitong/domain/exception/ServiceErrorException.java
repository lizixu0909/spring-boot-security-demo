package com.sipuang.xianyitong.domain.exception;

/**
 * 业务异常
 *
 * @author lijun
 * @date 2018-04-20.
 */
public class ServiceErrorException extends RuntimeException {
    public ServiceErrorException(String msg) {
        super(msg);
    }
}
