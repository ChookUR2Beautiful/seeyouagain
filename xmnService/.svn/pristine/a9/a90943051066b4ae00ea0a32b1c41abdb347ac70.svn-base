package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;


/**
 * 粉丝订单取消
 * */
public class LiveFansOrderCancleRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959037552617374030L;
	
	
	private Integer uid ;
	
	@NotNull(message="订单编号不能为空")
	private String orderNo;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "LiveFansOrderCancleRequest [uid=" + uid + ", orderNo="
				+ orderNo + "]";
	}
	
	
}
