/**   
 * 文件名：TSellerAsk.java   
 *    
 * 日期：2014年11月19日10时48分28秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.business_cooperation.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSellerAsk
 * 
 * 类描述：产品问答
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时48分28秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerAsk extends BaseEntity {

	private static final long serialVersionUID = 8704756114124679522L;
	private Integer aid;// 问题ID
	private String askname;// 问题名称
	private String content;// 问题内容
	private Date sdate;// 提问时间
	
	private Date sdateStart;// 加入时间开始（搜索条件）
	private Date sdateEnd;// 加入时间结束 （搜索条件
	/**
	 * 创建一个新的实例 TSellerAsk.
	 * 
	 */
	public TSellerAsk() {
		super();
	}

	public TSellerAsk(Integer aid) {
		this.aid = aid;
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

	/**
	 * aid
	 * 
	 * @return the aid
	 */
	public Integer getAid() {
		return aid;
	}

	/**
	 * @param aid
	 *            the aid to set
	 */
	public void setAid(Integer aid) {
		this.aid = aid;
	}

	/**
	 * askname
	 * 
	 * @return the askname
	 */
	public String getAskname() {
		return askname;
	}

	/**
	 * @param askname
	 *            the askname to set
	 */
	public void setAskname(String askname) {
		this.askname = askname;
	}

	/**
	 * content
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSellerAsk [aid=" + aid + ", askname=" + askname + ", content=" + content + ", sdate=" + sdate + ", ]";
	}
}
