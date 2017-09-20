/**   
 * 文件名：TSellerPic.java   
 *    
 * 日期：2014年11月11日15时56分48秒  
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
 * 类名称：TSellerMarketing
 * 
 * 类描述：商家营销活动
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerMarketing extends BaseEntity {

	private static final long serialVersionUID = -6831661026222043829L;
	
	private Integer id;//主键ID
	private Integer sellerid;// 消费商家ID
	private Integer aid;// 活动ID,对应活动表主键ID
	private Integer isattend;//默认0 参加营销   1不参加营销
	private Date sdate;// 营销开始时间
	private Date edate;// 营销结束时间
	private Date rdate;// 商家加入时间
	
	
	private String sellername;//商家名
	private String aname;//活动名
	private Date sdateStart;//开始时间查询条件
	private Date sdateEnd;//开始时间查询条件
	private Date edateStart;//结束时间查询条件
	private Date edateEnd;//结束时间查询条件
	
	private String startDataS;//结束时间查询条件
	private String startDataE;//结束时间查询条件
	private String endDataS;//结束时间查询条件
	private String endDataE;//结束时间查询条件
	
	private String startTimeS;//结束时间查询条件
	private String startTimeE;//结束时间查询条件
	private String endTimeS;//结束时间查询条件
	private String endTimeE;//结束时间查询条件
	
	private String sellerids;//批量设置活动时用的商家IDS
	private Integer activityType;//活动类型
	private Integer repel;//是否互斥：是否与其他活动互斥，0不互斥，1互斥
	private String doType;//操作类型

	public Integer getRepel() {
		return repel;
	}

	public void setRepel(Integer repel) {
		this.repel = repel;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	//获取是否参加的文字信息
	public String getIsattendText(){
		if(isattend == null) return null;
		if(isattend == 0) return "参加营销";
		if(isattend == 1) return "不参加营销";
		return null;
	}
	
	public String getSellerids() {
		return sellerids;
	}

	public void setSellerids(String sellerids) {
		this.sellerids = sellerids;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}
	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}
	public Date getEdateStart() {
		return edateStart;
	}

	public void setEdateStart(Date edateStart) {
		this.edateStart = edateStart;
	}
	public Date getEdateEnd() {
		return edateEnd;
	}

	public void setEdateEnd(Date edateEnd) {
		this.edateEnd = edateEnd;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getIsattend() {
		return isattend;
	}
	public void setIsattend(Integer isattend) {
		this.isattend = isattend;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	
	public String getStartTimeS() {
		return startTimeS;
	}

	public void setStartTimeS(String startTimeS) {
		this.startTimeS = startTimeS;
	}

	public String getStartTimeE() {
		return startTimeE;
	}

	public void setStartTimeE(String startTimeE) {
		this.startTimeE = startTimeE;
	}

	public String getEndTimeS() {
		return endTimeS;
	}

	public void setEndTimeS(String endTimeS) {
		this.endTimeS = endTimeS;
	}

	public String getEndTimeE() {
		return endTimeE;
	}

	public void setEndTimeE(String endTimeE) {
		this.endTimeE = endTimeE;
	}

	public String getStartDataS() {
		return startDataS;
	}

	public void setStartDataS(String startDataS) {
		this.startDataS = startDataS;
	}

	public String getStartDataE() {
		return startDataE;
	}

	public void setStartDataE(String startDataE) {
		this.startDataE = startDataE;
	}

	public String getEndDataS() {
		return endDataS;
	}

	public void setEndDataS(String endDataS) {
		this.endDataS = endDataS;
	}

	public String getEndDataE() {
		return endDataE;
	}

	public void setEndDataE(String endDataE) {
		this.endDataE = endDataE;
	}

	@Override
	public String toString() {
		return "TSellerMarketing [id=" + id + ", sellerid=" + sellerid
				+ ", aid=" + aid + ", isattend=" + isattend + ", sdate="
				+ sdate + ", edate=" + edate + "]";
	}
	
	
	
}