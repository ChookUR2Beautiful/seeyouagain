package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

@SuppressWarnings("serial")
public class OrderNoRequest extends BaseRequest{

	@NotNull(message="父订单ID 不能为空")
	private Long bid;//父订单ID

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "OrderInfoRequest [bid=" + bid + "]";
	}
}
