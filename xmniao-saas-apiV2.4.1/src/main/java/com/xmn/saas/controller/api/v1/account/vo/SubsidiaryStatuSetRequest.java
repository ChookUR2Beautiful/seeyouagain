package com.xmn.saas.controller.api.v1.account.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * create 2016/10/10
 *
 * @author yangQiang
 */
public class SubsidiaryStatuSetRequest extends Request {
    // 子账号主键id
    @NotNull(message = "子账号(aid)不能为空")
    private Integer aid;

    // 子账号是否冻结
    @NotNull(message = "子账号状态不能为空")
    @Max(value = 1,message = "子账号状态只能为0或1")
    @Min(value = 0,message = "子账号状态只能为0或1")
    private Integer userstatus;

    public SellerAccount converBean(){
        SellerAccount sellerAccount = new SellerAccount();
        BeanUtils.copyProperties(this,sellerAccount);
        return sellerAccount;
    }

    @Override
    public String toString() {
        return "SubsidiaryStatuSetRequest{" +
                "aid=" + aid +
                ", userstatus=" + userstatus +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }
}
