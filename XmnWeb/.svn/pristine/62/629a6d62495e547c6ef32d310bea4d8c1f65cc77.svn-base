/**   
 * 文件名：TSystemMsg.java   
 *    
 * 日期：2014年11月19日11时05分04秒  
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
 * 类名称：TSystemMsg
 * 
 * 类描述：系统信息推送
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时05分04秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSystemMsg extends BaseEntity {

	private static final long serialVersionUID = 2409043657967266884L;
	private Integer sid;// 记录ID
	private Date sdate;// 创建时间
	private Date tdate;// 发送时间
	private Date edate;// 过期时间
	private Integer status;// 发送状态|=待发送|1=已发送
	private String content;// 信息内容
	private Integer number;// 阅读数
	private String object;// 发送对象|为空发送给所有用户|不为空发送给指定用户
	private Integer ispush;// 是否推送|0=不推送|1=推送
	private Integer actiontype;// 后续动作|1=打开指定网页|2=打开activity
	private String action;// 填网址或activity
	private Integer type;// 通知类型|0=用户系统通知|1=向蜜客系统通知

	//新增虚拟字段
	private Date sdateStart;
	private Date sdateEnd;
	private Date tdateStart;
	private Date tdateEnd;
	private Date edateStart;
	private Date edateEnd;
	
	//获取发送状态文字说明
	public String getStatusText(){
		if(status != null && status == 0) return "待发送";
		if(status != null && status == 1) return "已发送";
		return null;
	}
	
	//获取是否推送文字说明
	public String getIspushText(){
		if(ispush != null && ispush == 0) return "不推送";
		if(ispush != null && ispush == 1) return "推送";
		return null;
	}
	
	//获取后续动作文字说明
	public String getActiontypeText(){
		if(actiontype != null && actiontype == 1) return "打开指定网页";
		if(actiontype != null && actiontype == 2) return "打开activity";
		return null;
	}
	
	//获取通知类型文字说明
	public String getTypeText(){
		if(type != null && type == 0) return "用户系统通知";
		if(type != null && type == 1) return "向蜜客系统通知";
		return null;
	}
	
	/**
	 * 创建一个新的实例 TSystemMsg.
	 * 
	 */
	public TSystemMsg() {
		super();
	}

	public TSystemMsg(Integer sid) {
		this.sid = sid;
	}

	/**
	 * sid
	 * 
	 * @return the sid
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * @param sid
	 *            the sid to set
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
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
	 * tdate
	 * 
	 * @return the tdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getTdate() {
		return tdate;
	}

	/**
	 * @param tdate
	 *            the tdate to set
	 */
	public void setTdate(Date tdate) {
		this.tdate = tdate;
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

	/**
	 * ispush
	 * 
	 * @return the ispush
	 */
	public Integer getIspush() {
		return ispush;
	}

	/**
	 * @param ispush
	 *            the ispush to set
	 */
	public void setIspush(Integer ispush) {
		this.ispush = ispush;
	}

	/**
	 * actiontype
	 * 
	 * @return the actiontype
	 */
	public Integer getActiontype() {
		return actiontype;
	}

	/**
	 * @param actiontype
	 *            the actiontype to set
	 */
	public void setActiontype(Integer actiontype) {
		this.actiontype = actiontype;
	}

	/**
	 * action
	 * 
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * type
	 * 
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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

	public Date getTdateStart() {
		return tdateStart;
	}

	public void setTdateStart(Date tdateStart) {
		this.tdateStart = tdateStart;
	}

	public Date getTdateEnd() {
		return tdateEnd;
	}

	public void setTdateEnd(Date tdateEnd) {
		this.tdateEnd = tdateEnd;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSystemMsg [sid=" + sid + ", sdate=" + sdate + ", tdate=" + tdate + ", edate=" + edate + ", status=" + status + ", content=" + content + ", number=" + number + ", object=" + object + ", ispush=" + ispush + ", actiontype=" + actiontype + ", action=" + action + ", type=" + type + ", ]";
	}
}
