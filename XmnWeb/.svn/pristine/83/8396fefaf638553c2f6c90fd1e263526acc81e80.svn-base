package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllianceShop
 * 
 * 类描述：商家联盟店实体
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-01-16 11:06:45
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class AllianceShop extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7654771137669740923L;
	
	private Long id;//联盟商ID
	private String allianceName;//联盟商名称
	private String province;//省编号
	private String provinceNmae;//省名
	private String city;//市编号
	private String cityName;//市名
	private String area;//区编号
	private String areaName;//区名
	private Long zoneid;//所属商圈编号
	private String zoneName;//所属商圈名
	private String address;//详细地址
	private String phoneid;//联系人电话
	private Date sdate;//加入时间
	private Date udate;//更新时间
	private String description;//描述
	private String allAreaName;
	private String salesman;  //联系人
	private String subShopNum;//关联商户数
	
	public String getAllAreaName() {
		StringBuilder sb = new StringBuilder();
		if(StringUtils.hasLength(cityName)){
			sb.append(cityName);
		}
		if(StringUtils.hasLength(areaName)){
			sb.append("-");
			sb.append(areaName);
		}
		allAreaName = sb.toString();
		return allAreaName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAllianceName() {
		return allianceName;
	}
	public void setAllianceName(String allianceName) {
		this.allianceName = allianceName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Long getZoneid() {
		return zoneid;
	}
	public void setZoneid(Long zoneid) {
		this.zoneid = zoneid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProvinceNmae() {
		return provinceNmae;
	}
	public void setProvinceNmae(String provinceNmae) {
		this.provinceNmae = provinceNmae;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getSubShopNum() {
		return subShopNum;
	}
	public void setSubShopNum(String subShopNum) {
		this.subShopNum = subShopNum;
	}
	
	@Override
	public String toString() {
		return "AllianceShop [id=" + id + ", allianceName=" + allianceName
				+ ", province=" + province + ", provinceNmae=" + provinceNmae
				+ ", city=" + city + ", cityName=" + cityName + ", area="
				+ area + ", areaName=" + areaName + ", zoneid=" + zoneid
				+ ", zoneName=" + zoneName + ", address=" + address
				+ ", phoneid=" + phoneid + ", sdate=" + sdate + ", udate="
				+ udate + ", description=" + description + ", salesman="
				+ salesman + ", subShopNum=" + subShopNum + "]";
	}
	
}
