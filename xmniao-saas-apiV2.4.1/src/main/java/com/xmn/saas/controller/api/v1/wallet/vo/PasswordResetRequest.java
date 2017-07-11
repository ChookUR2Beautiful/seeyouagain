package com.xmn.saas.controller.api.v1.wallet.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 钱包重置支付密码类
 * 
 * @author zhouxiaojian
 *
 */
public class PasswordResetRequest extends Request {

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


    @NotNull( message = "验证码不能为空" )
    private String verifyCode;

    @NotNull( message = "姓名不能为空" )
    private String name;

    @NotNull( message = "身份证号码不能为空" )
    private String idCard;

    @NotNull( message = "银行卡号不能为空" )
    private String bank;

    public String getVerifyCode() {
        return verifyCode;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getIdCard() {
        return idCard;
    }


    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }


    public String getBank() {
        return bank;
    }


    public void setBank(String bank) {
        this.bank = bank;
    }


    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }



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
        return "PasswordResetRequest [sellerId=" + sellerId + ", apiversion=" + apiversion
                + ", password=" + password + ", verifyCode=" + verifyCode + ", name=" + name
                + ", idCard=" + idCard + ", bank=" + bank + "]";
    }


    public SellerAccount converToBean(int sellerId) {
        SellerAccount sellerAccount = new SellerAccount();
        BeanUtils.copyProperties(this, sellerAccount);
        sellerAccount.setSellerid(sellerId);
        return sellerAccount;
    }

}
