/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：Supplier
 * 
 * 类描述： 供应商
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月16日 上午10:30:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class Supplier extends BaseEntity{
	
	private static final long serialVersionUID = -2976181746223257446L;

	private String supplierId;//供应商id
	
	private String postTemplateId;//邮费模板id
	
	private Integer type;//类型
	
	private String name;//供应商名称
	
	private String contacts;//联系人
	
	private String mobile;//联系人电话
	
	private String address;//地址
	
	private Integer status;//状态
	
	private Date createTime;//添加时间
	
	private Integer province;//省id
	
	private Integer city;//市id
	
	private Integer area;//区id
	
	private String wxin;//微信号
	
	private Long qq; //qq号
	
	private String email;//邮箱
	 

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getPostTemplateId() {
		return postTemplateId;
	}

	public void setPostTemplateId(String postTemplateId) {
		this.postTemplateId = postTemplateId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getWxin() {
		return wxin;
	}

	public void setWxin(String wxin) {
		this.wxin = wxin;
	}

	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", postTemplateId="
				+ postTemplateId + ", type=" + type + ", name=" + name
				+ ", contacts=" + contacts + ", mobile=" + mobile
				+ ", address=" + address + ", status=" + status
				+ ", createTime=" + createTime + ", province=" + province
				+ ", city=" + city + ", area=" + area + ", wxin=" + wxin
				+ ", qq=" + qq + ", email=" + email + "]";
	}

	

}
