package com.xmniao.xmn.core.manor.entity;

/**
 * Created by yang.qiang on 2017/8/2.
 */
public class HttpResult {
    private Integer state;      // 状态码 0 成功  1 失败
    private String message;     // 消息
    private Object context;



    public HttpResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public HttpResult(Integer state, String message, Object context) {
        this.state = state;
        this.message = message;
        this.context = context;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }
}
