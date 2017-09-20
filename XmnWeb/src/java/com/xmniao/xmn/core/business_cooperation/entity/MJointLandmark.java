package com.xmniao.xmn.core.business_cooperation.entity;

import java.io.Serializable;
public class MJointLandmark implements Serializable{
	
	private static final long serialVersionUID = -2180681345737968246L;
	
	private Double longitude=0.00;// 经度
	private Double latitude=0.00;// 纬度
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
	@Override
	public String toString() {
		return "MJointLandmark [longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}
}
