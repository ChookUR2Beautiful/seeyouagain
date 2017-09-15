/**
 * 2016年8月17日 下午4:45:30
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：UserPayBirdCoinRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月17日 下午4:45:30
 * @version
 */
public class UserPayBirdCoinRequest extends BaseRequest {
	/**
	 *long
	 */
	private static final long serialVersionUID = -3322374120280507220L;
	
	private Integer uid;
	
	@NotNull(message="充值套餐ID不能为空")
	private Long id;
	
//	@NotNull(message="支付方式不能为空")
	private Long pay_type;
	
	@NotNull(message="购买数量不能为空")
	private Long quantity = 1L;
	
	//商铺ID 标示充值要充值到店铺储值卡
	private Long sellerId = 0L;
	
	//是否标示充值到储值卡 0 否 1是
	private Long isCard = 0L;
	
	//充值卡 商家类型  是单店，连锁，区域代理
	private Long sellerType = 0L;
	
	//充值卡 商家类型  是单店，连锁，区域代理
	private String returnUrl;
	
	//是否是代充值请求
	private Integer isHelpPay;
	
	//被代充值的人的UID
	private Integer payUid;
	
//	@NotNull(message="是否使用余额标示不能为空")
	private Integer isBalance = 0;
	
	//充值渠道 （0.常规 1.vip 2.商家 3.直销  4.v客PC端下单 ）
	private  Integer rechargeType=0;
	
	//奖励模式 A  or  B
	private  String rewardType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPay_type() {
		return pay_type;
	}
	public void setPay_type(Long pay_type) {
		this.pay_type = pay_type;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Long getIsCard() {
		return isCard;
	}
	public void setIsCard(Long isCard) {
		this.isCard = isCard;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Long getSellerType() {
		return sellerType;
	}
	public void setSellerType(Long sellerType) {
		this.sellerType = sellerType;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public Integer getIsHelpPay() {
		return isHelpPay;
	}
	public void setIsHelpPay(Integer isHelpPay) {
		this.isHelpPay = isHelpPay;
	}
	public Integer getPayUid() {
		return payUid;
	}
	public void setPayUid(Integer payUid) {
		this.payUid = payUid;
	}
	public Integer getIsBalance() {
		return isBalance;
	}
	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
	}
	public Integer getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}
	public String getRewardType() {
		return rewardType;
	}
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	@Override
	public String toString() {
		return "UserPayBirdCoinRequest [uid=" + uid + ", id=" + id
				+ ", pay_type=" + pay_type + ", quantity=" + quantity
				+ ", sellerId=" + sellerId + ", isCard=" + isCard
				+ ", sellerType=" + sellerType + ", returnUrl=" + returnUrl
				+ ", isHelpPay=" + isHelpPay + ", payUid=" + payUid
				+ ", isBalance=" + isBalance + ", rechargeType=" + rechargeType
				+ ", rewardType=" + rewardType + "]";
	}
	
}
