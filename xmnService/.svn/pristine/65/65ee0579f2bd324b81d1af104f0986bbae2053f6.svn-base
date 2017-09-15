/**
 * 
 */
package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 项目名称：xmnService
 * 
 * 类名称：ModifyXmerInfoRequest
 * 
 * 类描述：用户个人资料修改请求
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-23上午9:59
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class ModifyXmerInfoRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1961475877559932063L;
	
	private Integer uid;//用户ID
	
	@NotNull(message="字段名称不能为空")
	private String fieldname;//字段名称
	
	@NotNull(message="字段值不能为空")
	private String fieldvalue;//字段值

	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * @return the fieldname
	 */
	public String getFieldname() {
		return fieldname;
	}

	/**
	 * @param fieldname the fieldname to set
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	/**
	 * @return the fieldvalue
	 */
	public String getFieldvalue() {
		return fieldvalue;
	}

	/**
	 * @param fieldvalue the fieldvalue to set
	 */
	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModifyXmerInfoRequest [uid=" + uid + ", fieldname=" + fieldname
				+ ", fieldvalue=" + fieldvalue + "]";
	}
	
}
