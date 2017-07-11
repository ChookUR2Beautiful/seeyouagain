package com.xmn.saas.controller.api.v1.account.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;

/**
 * create 2016/10/22
 *
 * @author yangQiang
 */

public class AccountForgetGestureRequest extends Request {

    @NotNull(message = "短信验证码(verifyCode)不能为空")
    private String verifyCode;


    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "AccountForgetGestureRequest{" +
                "verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
