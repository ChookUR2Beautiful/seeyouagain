package com.xmn.saas.controller.api.v1.sms.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;

/**
 * create 2016/09/27
 *
 * @author yangQiang
 */

public class SendCodeRequest extends Request {
    // 发送手机号
    @NotNull(message = "phone不能为空")
    private String phone;

    // 验证码类型
    @NotNull(message = "codeType不能为空")
    private String codeType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    @Override
    public String toString() {
        return "SendCodeRequest{" +
                "phone='" + phone + '\'' +
                ", codeType='" + codeType + '\'' +
                '}';
    }
}
