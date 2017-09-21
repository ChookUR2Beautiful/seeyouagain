package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：DebitcardSeller
 * 
 * 类描述： 商家储值卡配置实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月2日 下午2:27:38 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DebitcardSeller {
    private Integer id;

    private Integer sellerid;

    private Integer sellertype;

    private String sellername;

    private Integer status;

    private String subSellerid;

    private BigDecimal totalLimitRecharge;

    private String rechargeItem;

    private Integer referrerRatio;

    private Integer parentReferrerRatio;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getSellertype() {
        return sellertype;
    }

    public void setSellertype(Integer sellertype) {
        this.sellertype = sellertype;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubSellerid() {
        return subSellerid;
    }

    public void setSubSellerid(String subSellerid) {
        this.subSellerid = subSellerid == null ? null : subSellerid.trim();
    }

    public BigDecimal getTotalLimitRecharge() {
        return totalLimitRecharge;
    }

    public void setTotalLimitRecharge(BigDecimal totalLimitRecharge) {
        this.totalLimitRecharge = totalLimitRecharge;
    }

    public String getRechargeItem() {
        return rechargeItem;
    }

    public void setRechargeItem(String rechargeItem) {
        this.rechargeItem = rechargeItem == null ? null : rechargeItem.trim();
    }

    public Integer getReferrerRatio() {
        return referrerRatio;
    }

    public void setReferrerRatio(Integer referrerRatio) {
        this.referrerRatio = referrerRatio;
    }

    public Integer getParentReferrerRatio() {
        return parentReferrerRatio;
    }

    public void setParentReferrerRatio(Integer parentReferrerRatio) {
        this.parentReferrerRatio = parentReferrerRatio;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "DebitcardSeller [id=" + id + ", sellerid=" + sellerid
				+ ", sellertype=" + sellertype + ", sellername=" + sellername
				+ ", status=" + status + ", subSellerid=" + subSellerid
				+ ", totalLimitRecharge=" + totalLimitRecharge
				+ ", rechargeItem=" + rechargeItem + ", referrerRatio="
				+ referrerRatio + ", parentReferrerRatio="
				+ parentReferrerRatio + ", updateTime=" + updateTime + "]";
	}

	public DebitcardSeller(Integer id) {
		super();
		this.id = id;
	}

	public DebitcardSeller() {
		super();
	}
    
    
}