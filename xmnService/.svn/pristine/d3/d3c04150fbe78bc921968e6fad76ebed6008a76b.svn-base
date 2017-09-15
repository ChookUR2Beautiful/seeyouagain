package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveBuyBirdCoinRequest   
* 类描述：   获取购买鸟币套餐信息请求参数
* 创建人：yhl   
* 创建时间：2016年8月24日 上午9:35:36   
* @version    
*
 */
public class LiveBuyBirdCoinOrderIOSRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791924145986622771L;

	@NotNull(message="IOS内购product_id参数不能为空")
	private String productId;
	
	@NotNull(message="会员UID不能为空")
	private Integer uid;
	
	//商铺ID 标示充值要充值到店铺储值卡
	private Long sellerId = 0L;
	
	//是否标示充值到储值卡 0 否 1是
	private Long isCard = 0L;
	
	//充值渠道 （0.常规 1.vip 2.商家 3.直销  4.v客PC端下单 ）
	private  Integer rechargeType=0;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Integer getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}

	@Override
	public String toString() {
		return "LiveBuyBirdCoinOrderIOSRequest [productId=" + productId
				+ ", uid=" + uid + ", sellerId=" + sellerId + ", isCard="
				+ isCard + ", rechargeType=" + rechargeType + "]";
	}

}
