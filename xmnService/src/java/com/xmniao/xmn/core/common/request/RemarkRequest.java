package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class RemarkRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2622892566903119454L;

	@NotNull(message="订单不能为空")
	private String orderNo;
	
	@NotNull(message="备注不能为空")
	private String remark;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
