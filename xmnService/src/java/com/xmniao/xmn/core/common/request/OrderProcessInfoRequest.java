package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;


public class OrderProcessInfoRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2563095152996950766L;
	@NotNull(message="订单ID不能为空")
	public String bid;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "OrderProcessInfoRequest [bid=" + bid + "]";
	}
	
}
