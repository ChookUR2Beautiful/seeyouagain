package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: OrderListRequest    
* @Description:订单列表请求类   
* @author: liuzhihao   
* @date: 2016年12月27日 下午7:08:13
 */
@SuppressWarnings("serial")
public class OrderInfoRequest extends BaseRequest{

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
