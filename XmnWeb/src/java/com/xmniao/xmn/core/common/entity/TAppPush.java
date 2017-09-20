/**   
 * 文件名：TAppPush.java   
 *    
 * 日期：2014年11月12日17时25分33秒  
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
 * 类名称：TAppPush
 * 
 * 类描述：APP推送信息
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日17时25分33秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TAppPush extends BaseEntity {

	private static final long serialVersionUID = -2064321200602932764L;
	private Integer tid;// 推送ID
	private String title;// 信息标题
	private String content;// 信息内容
	private Integer status;// 发送状态|0=待发送|1=已发送
	private Integer type;// 1=打开应用|2=网址|3=activity
	private String action;// 后续动作网页地址activity名称等
	private Date sdate;// 创建时间
	private Date tdate;// 推送时间为空即时发送非空按时间发送
	private Date edate;// 过期时间|说明:过期时间需要至少晚于发送时间30分钟
	private String object;// 发送给谁，为空发送给所有用户。不为空发送给指定用户
	private Integer remind;//提醒方式， 0=声音|1=震动|2=呼吸灯
	private Integer client;// 1 寻蜜鸟客户端|2 商户客户端|3 合作商客户端
	private Integer contenttype;//内容类型， 1=提示信息|2=订单提醒|3=营销信息|4 系统消息
	private String province;//省编号
	private String city;//市编号
	private Integer sendType;//发送对象 0:全部 1：特定城市 2 自定义选择
	
	private Date edateStart;//过期时间(搜索)
	private Date edateEnd;//过期时间(搜索)
	
	private Integer sendObjectType;//发送对象类型参数（0：所有用户，1：部分用户）

	//获取发送状态文字说明
	public String getStatusText(){
		if(status == null) return null;
		if(status == 0) return "待发送";
		if(status == 1) return "已发送";
		return null;
	}
	
	//获取后续动作类型文字说明
	public String getTypeText(){
		if(type == null) return null;
		if(type == 1) return "打开应用";
		if(type == 2) return "网址";
		if(type == 3) return "activity";
		return null;
	}
	
	//获取提醒方式文字说明
	public String getRemindText(){
		if(remind == null)return null;
		if(remind == 0) return "声音";
		if(remind == 1) return "震动";
		if(remind == 2) return "呼吸灯";
		return null;
	}
	
	//获取客户端类型文字说明
	public String getClientText(){
		if(client == null) return null;
		if(client == 1) return "寻蜜鸟客户端";
		if(client == 2) return "商户客户端";
		if(client == 3) return "合作商客户端";
		return null;
	}
	
	//获取内容类型文字说明
	public String getContenttypeText(){
		if(contenttype == null) return null;
		if(contenttype == 1) return "提示信息";
		if(contenttype == 2) return "订单提醒";
		if(contenttype == 3) return "营销信息";
		if(contenttype == 4) return "系统消息";
		return null;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Object getTdateText(){
		return tdate != null ? tdate : edate;
	}
	public String getSendWay(){
		return tdate != null ? "定时发送" : "即时发送";
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
	
	public Integer getSendObjectType() {
		return sendObjectType;
	}

	public void setSendObjectType(Integer sendObjectType) {
		this.sendObjectType = sendObjectType;
	}

	/**
	 * 创建一个新的实例 TAppPush.
	 * 
	 */
	public TAppPush() {
		super();
	}

	public TAppPush(Integer tid) {
		this.tid = tid;
	}

	/**
	 * tid
	 * 
	 * @return the tid
	 */
	public Integer getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	public void setTid(Integer tid) {
		this.tid = tid;
	}

	/**
	 * title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * remind
	 * 
	 * @return the remind
	 */
	public Integer getRemind() {
		return remind;
	}

	/**
	 * @param remind
	 *            the remind to set
	 */
	public void setRemind(Integer remind) {
		this.remind = remind;
	}

	/**
	 * client
	 * 
	 * @return the client
	 */
	public Integer getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Integer client) {
		this.client = client;
	}

	/**
	 * contenttype
	 * 
	 * @return the contenttype
	 */
	public Integer getContenttype() {
		return contenttype;
	}

	/**
	 * @param contenttype
	 *            the contenttype to set
	 */
	public void setContenttype(Integer contenttype) {
		this.contenttype = contenttype;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}


	@Override
	public String toString() {
		return "TAppPush [tid=" + tid + ", title=" + title + ", content="
				+ content + ", status=" + status + ", type=" + type
				+ ", action=" + action + ", sdate=" + sdate + ", tdate="
				+ tdate + ", edate=" + edate + ", object=" + object
				+ ", remind=" + remind + ", client=" + client
				+ ", contenttype=" + contenttype + ", edateStart=" + edateStart
				+ ", edateEnd=" + edateEnd + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString() {
		return "TAppPush [tid=" + tid + ", title=" + title + ", content=" + content + ", status=" + status + ", type=" + type + ", action=" + action + ", sdate=" + sdate + ", tdate=" + tdate + ", edate=" + edate + ", object=" + object + ", remind=" + remind + ", client=" + client + ", contenttype=" + contenttype + ", ]";
	}*/
}
