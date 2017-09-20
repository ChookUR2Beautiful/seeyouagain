package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称： XmnWeb 类名称： TSupplier.java 类描述：经销商实体 创建人： lifeng 创建时间：
 * 2016年6月16日下午6:01:20 Copyright (c) 广东寻蜜鸟网络技术有限公司
 * 
 * @version
 */
public class TSupplier extends BaseEntity {

	private static final long serialVersionUID = 2348813633841421311L;

	private Integer supplierId;// 供应商ID
	private String supplierName;// 供应商名
	private String contacts;// 联系人
	private String phone;// 联系电话
	private String address;// 地址
	private Integer status;// 经销商状态 0正常，1废弃
	private Date sdate;// 添加时间
	
	// 开始时间和结束时间，用于条件查询
	private Date sDate;
	private Date eDate;
	
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	
	@Override
	public String toString() {
		return "TSupplier [supplierId=" + supplierId + ", supplierName="
				+ supplierName + ", contacts=" + contacts + ", phone=" + phone
				+ ", address=" + address + ", status=" + status + ", sdate="
				+ sdate + "]";
	}

}
