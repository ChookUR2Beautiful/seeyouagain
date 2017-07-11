package com.xmn.saas.controller.api.v1.pay.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;

/**
 * create 2016/10/26
 *
 * @author yangQiang
 */

public class PayCheckPayPasswdRequest extends Request {

    @NotNull(message = "支付密码(payPasswd)不能为空")
    private String payPasswd;


    public String getPayPasswd() {
        return payPasswd;
    }

    public void setPayPasswd(String payPasswd) {
        this.payPasswd = payPasswd;
    }
}
