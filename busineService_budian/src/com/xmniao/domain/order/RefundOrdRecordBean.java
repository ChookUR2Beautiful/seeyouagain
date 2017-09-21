package com.xmniao.domain.order;

import java.io.Serializable;

/**
 * 订单退款处理记录实体Bean类
 * 
 * @author wenchunhong
 * @version [版本号, 2015年4月08日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RefundOrdRecordBean implements Serializable {
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -2122027961351935515L;

	/**
	 * 订单号
	 */
	private Long bid;

	/**
	 * 订单状态
	 */
	private int status;

	/**
	 * 处理时间
	 */
	private String sdate;

	/**
	 * 备注
	 */
	private String remarks;

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

}
