package com.xmn.saas.controller.api.v1.common.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;

/**
 * Created by yang.qiang on 2017/3/23.
 */
public class ShopListRequest extends Request {

    @NotNull(message = "账户不能为空")
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "ShopListRequest{" +
            "account='" + account + '\'' +
            '}';
    }
}
