/**   
 * 文件名：TSystemInfo.java   
 *    
 * 日期：2014年11月19日10时58分46秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.common.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSystemInfo
 * 
 * 类描述：系统信息发布
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时58分46秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSystemInfo extends BaseEntity {

	private static final long serialVersionUID = -1235858767743482521L;
	private Integer id;// 信息ID
	private String content;// 信息内容
	private Date sdate;// 创建时间
	private Date fdate;// 为空，为即时发送。
	private Date edate;// 过期时间需要至少晚于发送时间30分钟
	private Integer status;// 0=待发送|1=已发送
	private Integer number;// 阅读数
	private String object;// 发送给谁|为空发送给所有用户|不为空发送给指定用户
	
	private Date sdateStart;// 创建时间（搜索条件）
	private Date sdateeEnd;// 创建时间 （搜索条件
	private Date fdateStart;// 发送时间（搜索条件）
	private Date fdateEnd;// 发送时间 （搜索条件
	private Date edateStart;// 过期时间（搜索条件）
	private Date edateEnd;// 过期时间（搜索条件
	
	//获取状态文字说明
	public String getStatusText(){
		if(status == null) return null;
		if(status == 0) return "待发送";
		if(status == 1) return "已发送";
		return null;
	}
	
	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateeEnd() {
		return sdateeEnd;
	}

	public void setSdateeEnd(Date sdateeEnd) {
		this.sdateeEnd = sdateeEnd;
	}

	public Date getFdateStart() {
		return fdateStart;
	}

	public void setFdateStart(Date fdateStart) {
		this.fdateStart = fdateStart;
	}

	public Date getFdateEnd() {
		return fdateEnd;
	}

	public void setFdateEnd(Date fdateEnd) {
		this.fdateEnd = fdateEnd;
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

	/**
	 * 创建一个新的实例 TSystemInfo.
	 * 
	 */
	public TSystemInfo() {
		super();
	}

	public TSystemInfo(Integer id) {
		this.id = id;
	}

	/**
	 * id
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * fdate
	 * 
	 * @return the fdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getFdate() {
		return fdate;
	}

	/**
	 * @param fdate
	 *            the fdate to set
	 */
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	/**
	 * edate
	 * 
	 * @return the edate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEdate() {
		return edate;
	}

	/**
	 * @param edate
	 *            the edate to set
	 */
	public void setEdate(Date edate) {
		this.edate = edate;
	}

	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * number
	 * 
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * object
	 * 
	 * @return the object
	 */
	public String getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(String object) {
		this.object = object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSystemInfo [id=" + id + ", content=" + content + ", sdate=" + sdate + ", fdate=" + fdate + ", edate=" + edate + ", status=" + status + ", number=" + number + ", object=" + object + ", ]";
	}
}
