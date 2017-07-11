package com.xmn.saas.entity.shop;

/**
 * 
*      
* 类名称：Business   
* 类描述：   商圈实体类
* 创建人：xiaoxiong   
* 创建时间：2016年9月26日 下午4:53:41   
* 修改人：xiaoxiong   
* 修改时间：2016年9月26日 下午4:53:41   
* 修改备注：   
* @version    
*
 */
public class Business {

	/**
	 * 商圈ID
	 */
	private int bid;
	
	/**
	 * 区域ID
	 */
	private Integer areaId;
	/**
	 * 商圈名称
	 */
	private String title;
	/**
	 * 商圈图片
	 */
	private String picurl;
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
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
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
	
	
	
	
}
