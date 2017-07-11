package com.xmn.saas.controller.api.v1.wallet.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 钱包设置支付密码类
 * 
 * @author zhouxiaojian
 *
 */
public class PasswordSetRequest extends Request {

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

    private String oldPassword;

    // 是否第一次修改支付密码
    @NotNull(message = "是否首次修改支付密码(type),不能为空")
    private Integer type;

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
        return "PasswordSetRequest [sellerId=" + sellerId + ", apiversion=" + apiversion
                + ", password=" + password + ", rePassword=" +"]";
    }

    public SellerAccount converToBean(int sellerId) {
        SellerAccount sellerAccount = new SellerAccount();
        BeanUtils.copyProperties(this, sellerAccount);
        sellerAccount.setSellerid(sellerId);
        return sellerAccount;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
