package com.xmniao.xmn.core.userData_statistics.entity;

import java.io.Serializable;

import com.xmniao.xmn.core.util.StringUtils;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessAction
 * 
 * 类描述：用户统计接口查询条件
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-23 10:33:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class UserDateRequestWhere implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6467088063521216367L;
	
	private String regtype;//渠道号
	private String stadate;//开始日期
	private String enddate;//结束日期
	private Integer week;//周
	private Integer month;//月
	private Integer year;//年
	private Integer page=1;//页码  默认:1
	private Integer pagesLength=10;//每页长度 默认:10
	private Integer order=1;//是否排序:1顺序，0倒序
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getRegtype() {
		return regtype;
	}
	public void setRegtype(String regtype) {
		this.regtype = regtype;
	}

	public String getStadate() {
		return stadate;
	}
	public void setStadate(String stadate) {
		this.stadate = stadate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesLength() {
		return pagesLength;
	}
	public void setPagesLength(Integer pagesLength) {
		this.pagesLength = pagesLength;
	}
	
	public String getWhere(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("page=");
		buffer.append(page);
		buffer.append("&pagesLength=");
		buffer.append(pagesLength);
		if(order!=null){
			buffer.append("&order=");
			buffer.append(order);
		}
		if(StringUtils.hasLength(regtype)){
			buffer.append("&regtype=");
			buffer.append(regtype);
		}
		if(StringUtils.hasLength(stadate)){
			buffer.append("&stadate=");
			buffer.append(stadate);
		}
		if(StringUtils.hasLength(enddate)){
			buffer.append("&enddate=");
			buffer.append(enddate);
		}
		
		if(week!=null&&week<13&&week>0){
			buffer.append("&week=");
			buffer.append(week);
		}
		if(month!=null&&month<31&&month>0){
			buffer.append("&month=");
			buffer.append(month);
		}
		if(year!=null&&year>0){
			buffer.append("&month=");
			buffer.append(month);
		}
		return buffer.toString();
	}
	
}
