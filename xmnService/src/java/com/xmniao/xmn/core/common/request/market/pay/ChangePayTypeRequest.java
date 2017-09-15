package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: ChangePayTypeRequest    
* @Description:选择支付方式请求类   
* @author: liuzhihao   
* @date: 2017年1月3日 下午12:47:45
 */
@SuppressWarnings("serial")
public class ChangePayTypeRequest extends BaseRequest{

	@NotNull(message="订单ID不能为空")
	private Long bid;

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "ChangePayTypeRequest [bid=" + bid + "]";
	}
	
	
}
