package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

@SuppressWarnings("serial")
public class PayConfirmRequest extends BaseRequest{

	private String ordersn;//订单ID

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	
}
