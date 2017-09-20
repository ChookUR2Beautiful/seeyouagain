/**   
 * 文件名：TSellerAgio.java   
 *    
 * 日期：2014年11月11日15时40分23秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSellerAgio
 * 
 * 类描述：商户折扣设置
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时40分23秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerAgio extends BaseEntity {

	private static final long serialVersionUID = -7244336917274458129L;

	private Integer aid;// 折扣ID
	private Integer sellerid;// 商家ID
	private Integer type;// 1=常规折扣|2=周末折扣|3=自定义折扣
	private Double baseagio;// 小数表示如0.5
	private Integer status;// 1=启用=2关闭常规折扣类型默认开启|周末和自定义折扣类型默认关闭
	private Double income;// 营业收入
	private Double sledger;// 商户分账
	private Double yledger;// 用户分账
	private Double pledger;// 平台分账
	private Date stdate;// 自定义折扣有效
	private Date endate;// 自定义折扣有效
	private Date sdate;// 修改时间
	private String remarks;//备注
	private Double flatAgio;//平台补贴占比，默认为0  平台做促销活动时此字段表示平台给用户的折扣补贴
	
	//add by wangzhimin 2015-08-13
	private Double ratio; //佣金补贴比例(商户补贴比例)，补给商户向蜜客额外的钱。以活动形式发放。补贴比例 0-100%
	
	private Integer id;// 记录ID
	private Integer operation;//1 APP(商户版) 2 WEB(商户WEB版) 3 SYSTEM(运营平台)    
	private String week; //周末折扣此字段有效，格式  1,2,3,4,5,6,7  1表示 星期1 以此类推  
	private Integer weektime;//周末折扣此字段有效                                                                             
	private Integer eweektime;//周末时间段结束
	private Integer uid;//运营平台用户ID，商户在APP上修改时此字段为0，运营人员后台修改时为 运营人员用户ID                                                                                                           
	private Date excdate;//执行时间  
	
	
	private Integer selleridid;// 商家ID(临时字段)	
	private Date stdateStart;// 折扣开始时间（搜索条件）
	private Date stdateEnd;// 折扣开始时间（搜索条件）
	private Date endateStart;// 折扣结束时间（搜索条件）
	private Date endateEnd;// 折扣结束时间（搜索条件）

	public Integer getFlatAgioIntValue(){
		if(null != this.getFlatAgio()){
		    return this.getFlatAgio().intValue(); 
		}
		return 0;
	}
	
	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getBaseagio() {
		return baseagio;
	}

	public void setBaseagio(Double baseagio) {
		this.baseagio = baseagio;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getSledger() {
		return sledger;
	}

	public void setSledger(Double sledger) {
		this.sledger = sledger;
	}

	public Double getYledger() {
		return yledger;
	}

	public void setYledger(Double yledger) {
		this.yledger = yledger;
	}

	public Double getPledger() {
		return pledger;
	}

	public void setPledger(Double pledger) {
		this.pledger = pledger;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStdate() {
		return stdate;
	}

	public void setStdate(Date stdate) {
		this.stdate = stdate;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndate() {
		return endate;
	}

	public void setEndate(Date endate) {
		this.endate = endate;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	public Date getStdateStart() {
		return stdateStart;
	}

	public void setStdateStart(Date stdateStart) {
		this.stdateStart = stdateStart;
	}

	public Date getStdateEnd() {
		return stdateEnd;
	}

	public void setStdateEnd(Date stdateEnd) {
		this.stdateEnd = stdateEnd;
	}

	public Date getEndateStart() {
		return endateStart;
	}

	public void setEndateStart(Date endateStart) {
		this.endateStart = endateStart;
	}

	public Date getEndateEnd() {
		return endateEnd;
	}

	public void setEndateEnd(Date endateEnd) {
		this.endateEnd = endateEnd;
	}
	public Integer getSelleridid() {
		return selleridid;
	}

	public void setSelleridid(Integer selleridid) {
		this.selleridid = selleridid;
	}
	
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the operation
	 */
	public Integer getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	/**
	 * @return the week
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * @param week the week to set
	 */
	public void setWeek(String week) {
		this.week = week;
	}



	public Integer getWeektime() {
		return weektime;
	}

	public void setWeektime(Integer weektime) {
		this.weektime = weektime;
	}

	public Integer getEweektime() {
		return eweektime;
	}

	public void setEweektime(Integer eweektime) {
		this.eweektime = eweektime;
	}

	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * @return the excdate
	 */
	public Date getExcdate() {
		return excdate;
	}

	/**
	 * @param excdate the excdate to set
	 */
	public void setExcdate(Date excdate) {
		this.excdate = excdate;
	}

	public Double getFlatAgio() {
		return flatAgio;
	}

	public void setFlatAgio(Double flatAgio) {
		this.flatAgio = flatAgio;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return "TSellerAgio [aid=" + aid + ", sellerid=" + sellerid + ", type="
				+ type + ", baseagio=" + baseagio + ", status=" + status
				+ ", income=" + income + ", sledger=" + sledger + ", yledger="
				+ yledger + ", pledger=" + pledger + ", stdate=" + stdate
				+ ", endate=" + endate + ", sdate=" + sdate + ", remarks="
				+ remarks + ", flatAgio=" + flatAgio + ", id=" + id
				+ ", operation=" + operation + ", week=" + week + ", weektime="
				+ weektime + ", eweektime=" + eweektime + ", uid=" + uid
				+ ", excdate=" + excdate + ", selleridid=" + selleridid
				+ ", stdateStart=" + stdateStart + ", stdateEnd=" + stdateEnd
				+ ", endateStart=" + endateStart + ", endateEnd=" + endateEnd
				+ "]";
	}


/*
	@Override
	public String toString() {
		return "TSellerAgio [aid=" + aid + ", sellerid=" + sellerid + ", type=" + type + ", baseagio=" + baseagio + ", status=" + status + ", income=" + income + ", sledger=" + sledger + ", yledger=" + yledger + ", pledger=" + pledger + ", stdate=" + stdate + ", endate=" + endate + ", sdate=" + sdate + "]";
	}*/
}
