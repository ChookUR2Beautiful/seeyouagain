package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class PayResultRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="订单号不能为空")
	private String orderid;//订单号
	
	@NotNull(message="订单类型不能为空")
	private Integer type;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PayResultRequest [orderid=" + orderid + ", type=" + type + "]";
	}

}
