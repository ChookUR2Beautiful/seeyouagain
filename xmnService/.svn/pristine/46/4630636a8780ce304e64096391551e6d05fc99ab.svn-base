/**
 * 2016年10月24日 下午2:11:14
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService
 * @类名称：CancelPreSellOrderRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月24日 下午2:11:14
 * @version
 */
public class CancelPreSellOrderRequest extends BaseRequest {

	/**
	 *long
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="订单编号不能为空")
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "CancelPreSellOrderRequest [orderNo=" + orderNo + "]";
	}
	
}
