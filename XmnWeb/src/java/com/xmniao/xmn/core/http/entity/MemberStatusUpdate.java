package com.xmniao.xmn.core.http.entity;


/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：Member
 * 
 * 类描述： PHP用户数据处理实体
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月22日 下午2:11:18
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class MemberStatusUpdate extends InterfacRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5177937791638613212L;
	private String status;// 用户的状态 1正常 2锁定 3注销	4.黑名单
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MemberStatusUpdate [status=" + status + "]";
	}

}
