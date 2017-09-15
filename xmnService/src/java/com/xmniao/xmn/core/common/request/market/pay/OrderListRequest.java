package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: OrderListRequest    
* @Description: 订单列表请求类  
* @author: liuzhihao   
* @date: 2017年1月13日 下午5:16:54
 */
@SuppressWarnings("serial")
public class OrderListRequest extends BaseRequest{
	
	@NotNull(message="订单类型不能为空")
	private Integer type;//订单类型  0 全部  1待处理
	
	@NotNull(message="分页不能为空 ")
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "OrderListRequest [type=" + type + ", page=" + page + "]";
	}
	
}
