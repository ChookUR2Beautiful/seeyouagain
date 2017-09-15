package com.xmniao.xmn.core.seller.entity;

public class SellerDetailed {
	
	/**
	 * 商家ID
	 */
	private Integer sellerid;
	/**
	 * 是否有wifi
	 */
	private Integer isWifi;
	/**
	 * 是否可以停车
	 */
	private Integer isParking;
	/**
	 * 地标
	 */
	private String landMark;
	/**
	 * 平均消费
	 */
	private Double consume;
	
	private int isRoom;
	
	public int getIsRoom() {
		return isRoom;
	}
	public void setIsRoom(int isRoom) {
		this.isRoom = isRoom;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getIsWifi() {
		return isWifi;
	}
	public void setIsWifi(Integer isWifi) {
		this.isWifi = isWifi;
	}
	public Integer getIsParking() {
		return isParking;
	}
	public void setIsParking(Integer isParking) {
		this.isParking = isParking;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}


	
	

}
