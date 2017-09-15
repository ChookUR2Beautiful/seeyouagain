package com.xmniao.xmn.core.common.request.sellerPackage;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;


/**
 * 购买套餐请求
 * */
public class SellerPackageBuyRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959037552617374030L;
	
	
	private Integer uid ;
	
	@NotNull(message="购买套餐数量不能为空")
	private Integer quantity = 1;
	
	@NotNull(message="套餐ID不能为空")
	private Integer packageId;
	
	//粉丝抵用券ID
	private String couponId ;
	
	//优惠券使用类型
	private Integer couponType = -1 ;
	
//	//根据支付方式计算下单金额 鸟币 或者  现金价  0 按照鸟币计算  1 按照现金价格计算
//	private Integer type = 0;
//	
	//根据支付方式填写   下单时必填
	private Integer payType;
	
	//鸟币支付类型不能为空 
	private Integer isFansCard = 0;
	
	//余额支付类型不能为空
	private Integer isBalance = 0;
	
	//余额支付类型不能为空
	private Integer isSellerCard = 0;
	
	//0 否 1 是
	@NotNull(message = "是否创建订单参数不能为空")
	private Integer isCreate = 0;
	
	//微信公众号 openId 必填
	private String openId;
	
	//支付宝客户端回调URL
	private String returnUrl;
	
	//购买来源：1、通过主播购买，2、通过商户购买，3、通过预告购买',4、其他
	@NotNull(message = "购买来源不能为空")
	private Integer source = 4;
	
	public Integer getUid() {
		return uid;
	}
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
	}

	public Integer getIsFansCard() {
		return isFansCard;
	}

	public void setIsFansCard(Integer isFansCard) {
		this.isFansCard = isFansCard;
	}

	public Integer getIsBalance() {
		return isBalance;
	}

	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
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

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	public Integer getIsSellerCard() {
		return isSellerCard;
	}

	public void setIsSellerCard(Integer isSellerCard) {
		this.isSellerCard = isSellerCard;
	}
	
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SellerPackageBuyRequest [uid=" + uid + ", quantity=" + quantity
				+ ", packageId=" + packageId + ", couponId=" + couponId
				+ ", couponType=" + couponType + ", payType=" + payType
				+ ", isFansCard=" + isFansCard + ", isBalance=" + isBalance
				+ ", isSellerCard=" + isSellerCard + ", isCreate=" + isCreate
				+ ", openId=" + openId + ", returnUrl=" + returnUrl
				+ ", source=" + source + "]";
	}

	
	
}
