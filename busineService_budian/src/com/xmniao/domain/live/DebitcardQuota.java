package com.xmniao.domain.live;

import java.math.BigDecimal;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：DebitcardQuota
 * 
 * 类描述： 用户的商家储值卡额度
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月1日 下午3:46:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DebitcardQuota {
    private Integer uid;

    private String phone;

    private Integer sellerid;

    private String sellername;

    private Integer sellertype;

    private BigDecimal quota;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getSellertype() {
        return sellertype;
    }

    public void setSellertype(Integer sellertype) {
        this.sellertype = sellertype;
    }


    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    
	public DebitcardQuota() {
		super();
	}

	public DebitcardQuota(Integer uid, String phone, Integer sellerid,
			String sellername, Integer sellertype, BigDecimal quota) {
		super();
		this.uid = uid;
		this.phone = phone;
		this.sellerid = sellerid;
		this.sellername = sellername;
		this.sellertype = sellertype;
		this.quota = quota;
	}

	@Override
	public String toString() {
		return "DebitcardQuota [uid=" + uid + ", phone=" + phone
				+ ", sellerid=" + sellerid + ", sellername=" + sellername
				+ ", sellertype=" + sellertype + ", quota=" + quota + "]";
	}


}