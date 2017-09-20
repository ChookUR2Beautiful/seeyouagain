/**   
 * 文件名：TSellerLandmark.java   
 *    
 * 日期：2014年11月11日15时30分16秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.business_cooperation.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称： XmnWeb
 * 类名称： TJointLandmark.java
 * 类描述：合作商经纬度信息
 * 创建人： lifeng
 * 创建时间： 2016年6月27日下午3:19:02
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public class TJointLandmark extends BaseEntity {

	private static final long serialVersionUID = -5140885084194261262L;
	private Integer lid;// 坐标ID
	private Integer jointid;// 合作商ID
	private Double longitude;// 经度
	private Double latitude;// 纬度
	private String geohash;// HASH值
	private Date sdate;// 更新时间
	private Integer sellerType;//0 商家，1经销商

	/**
	 * 创建一个新的实例 TJointLandmark.
	 * 
	 */
	public TJointLandmark() {
		super();
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public Integer getJointid() {
		return jointid;
	}

	public void setJointid(Integer jointid) {
		this.jointid = jointid;
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

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	@Override
	public String toString() {
		return "TJointLandmark [lid=" + lid + ", jointid=" + jointid
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", geohash=" + geohash + ", sdate=" + sdate + ", sellerType="
				+ sellerType + "]";
	}
	
}
