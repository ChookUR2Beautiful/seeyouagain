package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;


/**
 * 购买粉丝抵用券请求参数
 * */
public class LiveBuyCouponRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959037552617374030L;
	
	
	private Integer uid ;
	
	@NotNull(message="直播记录ID不能为空")
	private Integer liveRecordId;
	
	@NotNull(message="购买粉丝券数量不能为空")
	private Integer quantity = 1;
	
	@NotNull(message="粉丝券ID不能为空")
	private Integer fansCouponId;
	
	//粉丝抵用券ID
	private Integer couponId = 0;
	
	//购买来源：1、通过主播购买，2、通过商户购买，3、通过预告购买',4、其他
	private Integer source = 4;
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getLiveRecordId() {
		return liveRecordId;
	}
	
	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getFansCouponId() {
		return fansCouponId;
	}

	public void setFansCouponId(Integer fansCouponId) {
		this.fansCouponId = fansCouponId;
	}

	public Integer getSource() {
		return source;
	}
	
	public void setSource(Integer source) {
		this.source = source;
	}
	
	
}
