/**
 * 
 */
package com.xmniao.xmn.core.fresh.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaleProperty
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月4日上午8:48:07
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSaleProperty implements Serializable {

	private static final long serialVersionUID = -7909742524673077944L;
	
	private Integer id;// '主键'
	private String property;// DEFAULT NULL COMMENT '属性名'
	private Long codeId;//产品唯一标识编号
	private Integer sort;// DEFAULT '0' COMMENT '属性排序'
	private List<TSalePropertyValue> propertyValueList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<TSalePropertyValue> getPropertyValueList() {
		return propertyValueList;
	}
	public void setPropertyValueList(List<TSalePropertyValue> propertyValueList) {
		this.propertyValueList = propertyValueList;
	}
	
	public Long getCodeId() {
		return codeId;
	}
	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}
	@Override
	public String toString() {
		return "TSaleProperty [id=" + id + ", property="
				+ property + ", sort=" + sort + ", propertyValueList="
				+ propertyValueList + "]";
	}
	
}
