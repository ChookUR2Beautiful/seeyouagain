package com.xmn.saas.controller.api.v1.common.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * create 2016/09/23
 *
 * @author yangQiang
 */

public class LoginRequest extends Request{

    @NotNull(message = "登录账号名不能为空!")
    private String account;

    @NotNull(message = "登录密码不能为空!")
    private String password;

    private Integer sellerid;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public SellerAccount converToBean(){
        SellerAccount sellerAccount = new SellerAccount();
        BeanUtils.copyProperties(this,sellerAccount);
        return sellerAccount;
    }

}
