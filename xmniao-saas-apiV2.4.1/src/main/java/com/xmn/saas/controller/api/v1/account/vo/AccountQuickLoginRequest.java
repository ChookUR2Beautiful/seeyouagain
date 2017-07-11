package com.xmn.saas.controller.api.v1.account.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;

/**
 * create 2016/10/14
 *
 * @author yangQiang
 */

public class AccountQuickLoginRequest extends Request {

    // 页面二维码中的token
    @NotNull(message = "二维码信息(token)不能为空")
    private String token;

    // 扫码状态(1:检测二维码有效性,2:扫码正确确认登录)
    @NotNull(message = "类型(type)不能为空")
    private Integer type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccountQuickLoginRequest{" +
                "token='" + token + '\'' +
                ", type=" + type +
                '}';
    }
}
