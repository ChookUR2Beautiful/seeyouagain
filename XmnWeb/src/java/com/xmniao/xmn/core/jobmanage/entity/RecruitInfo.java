package com.xmniao.xmn.core.jobmanage.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称： XmnWeb
 * 类名称： RecruitInfo.java
 * 类描述：招聘信息实体类
 * 创建人： lifeng
 * 创建时间： 2016年5月30日下午4:36:48
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public class RecruitInfo extends BaseEntity {

	private static final long serialVersionUID = -6990335797271542484L;

	private Integer id;//主键ID
	private Integer sellerId;//商户id
	private String sellerName;//商户名称
	private String scale;//规模
	private String contact;//联系人
	private String phone;//联系电话
	private Integer status;//状态 默认1 正常  0 删除 
	private Date updateDate;//修改时间
	private Date createDate;//创建时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
