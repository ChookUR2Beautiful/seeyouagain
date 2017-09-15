package com.xmniao.xmn.core.common.request.order;

import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 下单请求参数
 **/
public class BillpaymentRequest  extends BaseRequest{
	/**
	 */
	private static final long serialVersionUID = 438465465480038747L;
	
	//商户ID
	@NotNull(message = "商户ID不能为空")
	private Long sellerId;
	
	//用户UID
	private Integer uid;
	
	//订单金额
	@NotNull(message = "买单金额不能为空")
	private BigDecimal amount = new BigDecimal(0);
	
	//优惠券ID
	private String couponId;
	
	//优惠券类型 -1标示默认没有选择  1:平台 2:商户 3:粉丝
	private Integer couponType = -1;
	
	//优惠券类型 0:否  1是
	@NotNull(message = "是否创建订单参数不能为空")
	private Integer isCreate = 0;
	
	//推荐人手机
	private String rPhone;
	
	//
	private Integer isBirdCoin;
	
	//服务员ID、
	private Integer aid = 0;
	
	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Integer getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
	}

	public String getrPhone() {
		return rPhone;
	}

	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getIsBirdCoin() {
		return isBirdCoin;
	}

	public void setIsBirdCoin(Integer isBirdCoin) {
		this.isBirdCoin = isBirdCoin;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "BillpaymentRequest [sellerId=" + sellerId + ", uid=" + uid
				+ ", amount=" + amount + ", couponId=" + couponId
				+ ", couponType=" + couponType + ", isCreate=" + isCreate
				+ ", rPhone=" + rPhone + ", isBirdCoin=" + isBirdCoin
				+ ", aid=" + aid + "]";
	}

	
}
