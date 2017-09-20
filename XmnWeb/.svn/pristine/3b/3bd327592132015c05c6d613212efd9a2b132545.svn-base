/**   
 * 文件名：TBusiness.java   
 *    
 * 日期：2014年11月12日17时41分48秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.common.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TBusiness
 * 
 * 类描述：商圈
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日17时41分48秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TBusiness extends BaseEntity {

	private static final long serialVersionUID = 7916993452716751432L;
	private Integer bid;// 商圈ID
	private Integer areaId;// 区域ID
	private String title;// 商圈名称
	private Date sdate;// 更新时间
	private String picurl;// 商圈图片
	private String remarks;// 备注
	private Double longitude;//经度
	private Double latitude;//纬度
	private String geohash; //HASH值
	


	private String tptitle;//省名
	private Integer tpareaid;//省id
	private String tctitle;//城市名
	private Integer tcareaid;//城市id
	private String tatitle;//区域名
	private Integer taareaid;//区域id
		
	private Integer number;// 商圈数
	private Integer isSeller;//商圈下是否有商家isSeller
	
	private Integer is_open;  //是否开通  0:未开通  1：已开通 


	public Integer getIs_open() {
		return is_open;
	}

	public void setIs_open(Integer is_open) {
		this.is_open = is_open;
	}

	/**
	 * 创建一个新的实例 TBusiness.
	 * 
	 */
	public TBusiness() {
		super();
	}

	public TBusiness(Integer bid) {
		this.bid = bid;
	}

	/**
	 * bid
	 * 
	 * @return the bid
	 */
	public Integer getBid() {
		return bid;
	}

	/**
	 * @param bid
	 *            the bid to set
	 */
	public void setBid(Integer bid) {
		this.bid = bid;
	}

	/**
	 * areaId
	 * 
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId
	 *            the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	/**
	 * picurl
	 * 
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl
	 *            the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	/**
	 * remarks
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
/*	@Override
	public String toString() {
		return "TBusiness [bid=" + bid + ", areaId=" + areaId + ", title=" + title + ", sdate=" + sdate + ", picurl=" + picurl + ", remarks=" + remarks + ", ]";
	}*/

	public String getTptitle() {
		return tptitle;
	}

	public void setTptitle(String tptitle) {
		this.tptitle = tptitle;
	}

	public Integer getTpareaid() {
		return tpareaid;
	}

	public void setTpareaid(Integer tpareaid) {
		this.tpareaid = tpareaid;
	}

	public String getTctitle() {
		return tctitle;
	}

	public void setTctitle(String tctitle) {
		this.tctitle = tctitle;
	}

	public Integer getTcareaid() {
		return tcareaid;
	}

	public void setTcareaid(Integer tcareaid) {
		this.tcareaid = tcareaid;
	}

	public String getTatitle() {
		return tatitle;
	}

	public void setTatitle(String tatitle) {
		this.tatitle = tatitle;
	}

	public Integer getTaareaid() {
		return taareaid;
	}

	public void setTaareaid(Integer taareaid) {
		this.taareaid = taareaid;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public Integer getIsSeller() {
		return isSeller;
	}

	public void setIsSerller(Integer isSeller) {
		this.isSeller = isSeller;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TBusiness [bid=" + bid + ", areaId=" + areaId + ", title="
				+ title + ", sdate=" + sdate + ", picurl=" + picurl
				+ ", remarks=" + remarks + ", tptitle=" + tptitle
				+ ", tpareaid=" + tpareaid + ", tctitle=" + tctitle
				+ ", tcareaid=" + tcareaid + ", tatitle=" + tatitle
				+ ", taareaid=" + taareaid + "]";
	}
		
}
