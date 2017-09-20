package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;

/**
 *@ClassName:TPostageRule
 *@Description:指定地区城市运费实体
 *@author hls
 *@date:2016年6月20日下午3:39:03
 */
public class TPostageRule implements Comparable<Object>{
	private Integer id = 0;
	
	private Integer tid = 0;//模板id
	private Integer tid_r = 0;//模板id(用于接收sql返回结果)
	
	private Integer baseWeight =0;//首重限重
	
	private String area;//包邮区域
	private String area_r;//包邮区域(用于接收sql返回结果)
	
	private BigDecimal baseFee;//首重运费
	
	private Integer extraWeight =0;//续重间隔
	
	private BigDecimal extraFee;//续重运费
	
	private Integer isdefault =0;//0指定运费;1默认运费
	
	private Integer status =0;//0 正常 1删除
	
	
	public Integer getTid_r() {
		return tid_r;
	}
	public void setTid_r(Integer tid_r) {
		this.tid_r = tid_r;
	}
	public String getArea_r() {
		return area_r;
	}
	public void setArea_r(String area_r) {
		this.area_r = area_r;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getBaseWeight() {
		return baseWeight;
	}
	public void setBaseWeight(Integer baseWeight) {
		this.baseWeight = baseWeight;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public BigDecimal getBaseFee() {
		return baseFee;
	}
	public void setBaseFee(BigDecimal baseFee) {
		this.baseFee = baseFee;
	}
	public Integer getExtraWeight() {
		return extraWeight;
	}
	public void setExtraWeight(Integer extraWeight) {
		this.extraWeight = extraWeight;
	}
	public BigDecimal getExtraFee() {
		return extraFee;
	}
	public void setExtraFee(BigDecimal extraFee) {
		this.extraFee = extraFee;
	}
	public Integer getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TPostageRule [id=" + id + ", tid=" + tid + ", baseWeight="
				+ baseWeight + ", area=" + area + ", area_r=" + area_r
				+ ", baseFee=" + baseFee + ", extraWeight=" + extraWeight
				+ ", extraFee=" + extraFee + ", isdefault=" + isdefault
				+ ", status=" + status + "]";
	}
	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
}
