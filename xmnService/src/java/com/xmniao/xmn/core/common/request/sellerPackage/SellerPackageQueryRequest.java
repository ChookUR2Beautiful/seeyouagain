package com.xmniao.xmn.core.common.request.sellerPackage;

import com.xmniao.xmn.core.base.BaseRequest;


/**
 *  查询订单，取消订单 ，购买成功推荐套餐
 * */
public class SellerPackageQueryRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959037552617374030L;
	
	
	private Integer uid ;
	
	//订单编号
	private String orderNo;
	
	//套餐ID
	private Integer packageId;
	
	//纬度
	private Double lat;
	
	//经度
	private Double lon;
	
	private String cityId;
	
	private Integer page;
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Override
	public String toString() {
		return "SellerPackageQueryRequest [uid=" + uid + ", orderNo=" + orderNo
				+ ", packageId=" + packageId + ", lat=" + lat + ", lon=" + lon
				+ ", cityId=" + cityId + ", page=" + page + "]";
	}
	
}
