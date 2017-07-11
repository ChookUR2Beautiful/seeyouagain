package com.xmn.saas.entity.shop;

/**
 * 
*      
* 类名称：SellerLandMark   
* 类描述：   商家坐标信息
* 创建人：xiaoxiong   
* 创建时间：2016年9月26日 上午11:06:58   
* 修改人：xiaoxiong   
* 修改时间：2016年9月26日 上午11:06:58   
* 修改备注：   
* @version    
*
 */
public class SellerLandMark {
	
	/**
	 * 坐标id
	 */
	private Integer lid;
	/**
	 * 经度
	 */
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	/**
	 * 高德纬度
	 */
	private Double ggLat;
	/**
	 * 高德经度
	 */
	private Double ggLng;
	
	private Integer sellerid;

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
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

	public Double getGgLat() {
		return ggLat;
	}

	public void setGgLat(Double ggLat) {
		this.ggLat = ggLat;
	}

	public Double getGgLng() {
		return ggLng;
	}

	public void setGgLng(Double ggLng) {
		this.ggLng = ggLng;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	
	
	
	
	
	
	
	
}
