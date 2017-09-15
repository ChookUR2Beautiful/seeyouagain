package com.xmniao.xmn.core.common.request.order;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 支付请求参数
 **/
public class PaymentOrderRequest  extends BaseRequest{
	/**
	 */
	private static final long serialVersionUID = 438465465480038747L;
	
	//商户ID
	@NotNull(message = "订单编号不能为空")
	private String orderNo;
	
	//用户UID
	private Integer uid;
	
	//是否余额
	@NotNull(message = "余额支付标示不能为空")
	private Integer isBalance = 0;
	
	//是否鸟币
//	@NotNull(message = "鸟币支付标示不能为空")
	private Integer isBirdCoin = 0;
	
	//是否储值卡支付
	@NotNull(message = "鸟粉专享卡支付不能为空")
	private Integer isSellerCard = 0;
	
	//是否第三方标示
//	@NotNull(message = "第三方支付标示不能为空")
	private String payType;
	
	private String couponId;
	
	private Integer couponType = 0;
	
	//微信公众号openid
	private String openId;
	
	private String returnUrl;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getIsBalance() {
		return isBalance;
	}

	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public Integer getIsBirdCoin() {
		return isBirdCoin;
	}

	public void setIsBirdCoin(Integer isBirdCoin) {
		this.isBirdCoin = isBirdCoin;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Integer getIsSellerCard() {
		return isSellerCard;
	}

	public void setIsSellerCard(Integer isSellerCard) {
		this.isSellerCard = isSellerCard;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "PaymentOrderRequest [orderNo=" + orderNo + ", uid=" + uid
				+ ", isBalance=" + isBalance + ", isBirdCoin=" + isBirdCoin
				+ ", isSellerCard=" + isSellerCard + ", payType=" + payType
				+ ", couponId=" + couponId + ", couponType=" + couponType
				+ ", openId=" + openId + ", returnUrl=" + returnUrl + "]";
	}
	

}
