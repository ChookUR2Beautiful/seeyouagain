package com.xmn.saas.controller.api.v1.account.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;

/**
 * create 2016/10/08
 *
 * @author yangQiang
 */

public class PasswordUpdateRequest extends Request {

    @NotNull(message = "原密码不能为空")
    private String oldPassword;
    @NotNull(message = "新密码不能为空")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }



    @Override
    public String toString() {
        return "PasswordUpdateRequest{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

}
