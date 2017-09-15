package com.xmniao.xmn.core.common.request.seller;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class SelleridRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="商户ID不能为空")
	private Integer sellerid;
	/**
	 * 经度
	 */
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	
	/**
	 * 0店铺  1积分商品
	 */
	private int type = 0;

	private Integer sellerType=1;	//商家标识 1 签约商家, 2 非签约商家

	
	public Integer getSellerType() {
		return sellerType;
	}
	
	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	@Override
	public String toString() {
		return "SelleridRequest [sellerid=" + sellerid + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", type=" + type + ", sellerType=" + sellerType + "]";
	}
	
	

}
