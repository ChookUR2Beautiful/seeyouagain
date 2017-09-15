/**
 * 
 */
package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
 * 项目名称：xmnService
 * 
 * 类名称：BalanceDetailInfoRequest
 *
 * 类描述：余额明细接口请求
 *
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-23下午5:53:05
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BalanceDetailInfoRequest extends BaseRequest{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4268131047786584101L;

	private Integer uid;//用户ID
	
	@NotNull(message="时间类型不能为空")
	private String type;//时间类型      0:前三个月 1：近三个月
	
	@NotNull(message="页码不能为空")
	private Integer page;//页码：默认1， 每页20条

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BalanceInfoRequest [uid=" + uid + ", type=" + type + ", page="
				+ page + "]";
	}
	
	


}
