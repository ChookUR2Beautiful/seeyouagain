package com.xmniao.xmn.core.exception;

/**
 * 异常类, 封装响应状态码
 * 用于在业务层抛出异常给控制层
 * create 2016/09/30
 * @author yangQiang
 */

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = -4987077640039023519L;
    // 响应码
    private Integer code;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
        this.code = 1;
    }

    public CustomException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getCode() {
        return code;
    }
}
