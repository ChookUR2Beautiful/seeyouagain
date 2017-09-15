package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;
import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 购买粉丝抵用券请求参数
 * */
public class LiveQueryCouponRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959037552617374030L;
	
	//页码
	@NotNull(message="页码不能为空")
	private Integer page;
	
	//寻蜜鸟会员ID
	private Integer uid ;
	
	//订单的总额
	private double orderAmount = 0D ;
	
	private Integer source=0;
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "LiveQueryCouponRequest [page=" + page + ", uid=" + uid
				+ ", orderAmount=" + orderAmount + ", source=" + source + "]";
	}

	
}
