package com.xmniao.xmn.core.common.request.order;


import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 下单请求参数
 **/
public class PreSellOrderQueryRequest  extends BaseRequest{
	/**
	 */
	private static final long serialVersionUID = 438465465480038747L;
	
	//商户ID
//	@NotNull(message = "商户ID不能为空")
	private String orderNo;
	
	//用户UID
	private Integer page = 1;
	
	//用户UID   1 套餐 2 粉丝卷 3 体验官
	private Integer type = 0;

	private String maxTime;  //预售订单列表，第二页开始需要传

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


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

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	@Override
	public String toString() {
		return "PreSellOrderQueryRequest{" +
				"orderNo='" + orderNo + '\'' +
				", page=" + page +
				", type=" + type +
				", maxTime='" + maxTime + '\'' +
				'}';
	}
}
