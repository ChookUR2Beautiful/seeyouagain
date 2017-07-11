package com.xmn.saas.exception;

import com.xmn.saas.base.ResponseCode;

/**
 * 异常类, 封装响应状态码
 * 用于在业务层抛出异常给控制层
 * create 2016/09/30
 * @author yangQiang
 */

public class SaasException extends RuntimeException{// 集成与RuntimeException 抛出异常会回滚事务
    /**
     * 
     */
    private static final long serialVersionUID = 2383394436468704576L;
    // 响应码
    private Integer code;

    public SaasException() {
        super();
    }

    public SaasException(String message) {
        super(message);
        this.code = ResponseCode.FAILURE;
    }

    public SaasException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public SaasException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaasException(Throwable cause) {
        super(cause);
    }

    protected SaasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getCode() {
        return code;
    }
}
