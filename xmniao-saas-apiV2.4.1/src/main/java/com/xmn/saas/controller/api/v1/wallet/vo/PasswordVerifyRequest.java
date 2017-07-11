package com.xmn.saas.controller.api.v1.wallet.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

/**
 * 钱包验证支付密码类
 * 
 * @author zhouxiaojian
 *
 */
public class PasswordVerifyRequest extends Request {

    /**
	 * 
	 */
    private static final long serialVersionUID = -7202857889575202545L;

    private String sellerId;

    /**
     * API版本，默认版本1 范围1-99
     */
    @Min( 1 )
    @Max( 99 )
    private int apiversion = 1;

    @NotNull( message = "密码不能为空" )
    private String password;



    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }



    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }



    public int getApiversion() {
        return apiversion;
    }

    public void setApiversion(int apiversion) {
        this.apiversion = apiversion;
    }


    @Override
    public String toString() {
        return "PasswordVerifyRequest [sellerId=" + sellerId + ", apiversion=" + apiversion
                + ", password=" + password + "]";
    }


    public SellerAccount converToBean(int sellerId) {
        SellerAccount sellerAccount = new SellerAccount();
        BeanUtils.copyProperties(this, sellerAccount);
        sellerAccount.setSellerid(sellerId);
        return sellerAccount;
    }

}
