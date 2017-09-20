package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class ExtensionSet extends BaseEntity {
	private Integer id;// 主键ID
	private Integer sellerId;// 商家编号
	private Date dateStart;// 推广开始时间
	private Date dateEnd;// 推广结束时间
	private Integer isAddMarketingList=0;// 是否添加到营销列表中，0：否，1：是，默认为否
	private Integer extensionType;// 推广类型 1：摇一摇，2：订单推广，3：列表推广
	private Integer sort;// 排序
	private Integer dataState;// 数据状态 -1：删除，0：有效
	private String creator;// 创建人
	private Date dateCreated;// 创建时间
	private String updator;// 修改人
	private Date dateUpdated;// 修改时间
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
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getIsAddMarketingList() {
		return isAddMarketingList;
	}
	public void setIsAddMarketingList(Integer isAddMarketingList) {
		this.isAddMarketingList = isAddMarketingList;
	}
	public Integer getExtensionType() {
		return extensionType;
	}
	public void setExtensionType(Integer extensionType) {
		this.extensionType = extensionType;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getDataState() {
		return dataState;
	}
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
