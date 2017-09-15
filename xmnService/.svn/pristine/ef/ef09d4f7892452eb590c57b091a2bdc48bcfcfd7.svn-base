package com.xmniao.xmn.core.common.request.personal;


import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 用户优惠券请求实体
 * */
public class UserCouponRequset extends BaseRequest{
	/**
	 */
	private static final long serialVersionUID = -1204001193405527018L;
	
	//用户uid
	private Integer uid;
	
	//优惠券类型 1 优惠券 2 粉丝券
	private Integer type;
	
	//优惠券类型 1,未使用，2已使用，3已过期
	private Integer status = 1;
	
	private Integer sellerId;
	
	//
	private BigDecimal orderAmount;
	
	@NotNull(message = "分页码不能为空")
	private Integer page = 1;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Override
	public String toString() {
		return "UserCouponRequset [uid=" + uid + ", type=" + type + ", status="
				+ status + ", sellerId=" + sellerId + ", orderAmount="
				+ orderAmount + ", page=" + page + "]";
	}
	
}
