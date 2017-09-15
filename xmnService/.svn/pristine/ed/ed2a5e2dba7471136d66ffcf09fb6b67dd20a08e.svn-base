package com.xmniao.xmn.core.common.request.order;

import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 下单请求参数
 **/
public class BillOrderQueryRequest  extends BaseRequest{
	/**
	 */
	private static final long serialVersionUID = 438465465480038747L;
	
	//商户ID
//	@NotNull(message = "商户ID不能为空")
	private String orderNo;
	
	//用户UID
	private Integer uid;
	
	//用户UID
	private Integer page;
	
	//用户UID
	private Integer sellerId;
	
	//用户UID
	private Integer type = 0;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BillOrderQueryRequest [orderNo=" + orderNo + ", uid=" + uid
				+ ", page=" + page + ", sellerId=" + sellerId + ", type="
				+ type + "]";
	}
	
}
