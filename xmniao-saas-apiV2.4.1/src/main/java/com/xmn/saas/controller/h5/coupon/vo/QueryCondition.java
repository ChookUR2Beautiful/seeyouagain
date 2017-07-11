/**
 * 
 */
package com.xmn.saas.controller.h5.coupon.vo;

import java.util.Date;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   优惠券查询参数
 * 创建人：huangk   
 * 创建时间：2016年10月17日 下午7:58:36      
 */
public class QueryCondition {

	private int sellerid;//商户id
	private String type;//优惠券类型
	private int status;//活动状态
	private String startDate;//开始时间
	private String endDate;//结束时间
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
