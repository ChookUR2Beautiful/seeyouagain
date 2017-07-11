package com.xmn.saas.controller.api.v1.common.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * create 2016/10/19
 * 忘记登录密码入参
 * @author yangQiang
 */
public class CommonForgetPasswordRequest extends Request {

    // 短信验证码
    @NotNull(message = "验证码(verifyCode)不能为空")
    private String verifyCode;

    // 账号( 手机号 )
    @NotNull(message = "账号(account)不能为空")
    private String account;

    // 操作类型 0:校验验证码 1:修改登录密码
    @NotNull(message = "操作类型(operation)不能为空")
    private Integer operation;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public SellerAccount converSellerAccount() {
        SellerAccount sellerAccount = new SellerAccount();
        BeanUtils.copyProperties(this,sellerAccount);
        return sellerAccount;
    }
}
