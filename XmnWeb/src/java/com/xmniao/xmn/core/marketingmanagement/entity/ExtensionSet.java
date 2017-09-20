/**   
 * 文件名：BillRecord.java   
 *    
 * 日期：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.marketingmanagement.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

import java.util.Date;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：ExtensionSet
 * 
 * 类描述：推广设置
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年03月16日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class ExtensionSet extends BaseEntity {

	private static final long serialVersionUID = -634638426217814980L;
	private Integer id;
	private Integer sellerId;
	private Date dateStart;
	private Date dateEnd;
	private String isAddMarketingList;
	private String extensionType;
	private String sort;
	private String dataState;
	private String updator;
	private String sellername;
	private String province;
	private String title;//区域名称
	private String city;
	private String area;
	private String tel;
	private String address;
	private String jointid;
	private String account;
	private String corporate;
	private String provinceName;
	private String isFirst;
	
	public String getIsFirst() {
		return isFirst;
	}


	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}


	public ExtensionSet() {
		super();
	}


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
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getIsAddMarketingList() {
		return isAddMarketingList;
	}

	public void setIsAddMarketingList(String isAddMarketingList) {
		this.isAddMarketingList = isAddMarketingList;
	}

	public String getExtensionType() {
		return extensionType;
	}

	public void setExtensionType(String extensionType) {
		this.extensionType = extensionType;
	}

	
	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}


	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJointid() {
		return jointid;
	}

	public void setJointid(String jointid) {
		this.jointid = jointid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
