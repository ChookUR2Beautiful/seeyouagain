/**
 * 
 */
package com.xmniao.xmn.core.fresh.entity;

import java.io.Serializable;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSalePropertyValue
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月4日上午8:52:16
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSalePropertyValue implements Serializable{

	private static final long serialVersionUID = -5817408488729016859L;
	
	private Integer id;// NOT NULL AUTO_INCREMENT COMMENT 'id'
	private Integer propertyId;// NOT NULL COMMENT '对应商品销售属性id'
	private String value;// DEFAULT NULL COMMENT '对应属性值'
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "TSalePropertyValue [id=" + id + ", propertyId=" + propertyId
				+ ", value=" + value + "]";
	}
	
}
