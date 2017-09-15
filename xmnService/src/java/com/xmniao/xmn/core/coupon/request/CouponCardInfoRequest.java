package com.xmniao.xmn.core.coupon.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;


@SuppressWarnings("serial")
public class CouponCardInfoRequest extends BaseRequest{

	@NotNull(message="我的卡卷是否过期")
	private Integer isOverdue;//是否过期 0 过期 1 未过期
	
	@NotNull(message="店铺ID不能为空")
	private String sellerid;//店铺ID
	
	@NotNull(message="店铺ID不能为空")
	private Integer sellerType;//店铺类型 1.普通商家 2.连锁总店 3 区域代理
	
	private Integer isRemark;//是否显示充值说明 默认为不显示

	public Integer getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(Integer isOverdue) {
		this.isOverdue = isOverdue;
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public Integer getIsRemark() {
		return isRemark;
	}

	public void setIsRemark(Integer isRemark) {
		this.isRemark = isRemark;
	}

	@Override
	public String toString() {
		return "CouponCardInfoRequest [isOverdue=" + isOverdue + ", sellerid=" + sellerid + ", sellerType=" + sellerType
			+ ", isRemark=" + isRemark + "]";
	}
	
}
