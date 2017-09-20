/**   
 * 文件名：TSubjectReply.java   
 *    
 * 日期：2014年11月20日14时58分36秒  
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
 * 类名称：TSubjectReply
 * 
 * 类描述：话题回复
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月20日14时58分36秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSubjectReply extends BaseEntity {

	private static final long serialVersionUID = 3728460257443865607L;
	private Integer rid;// 回复ID
	private Integer subjectid;// 话题ID
	private Integer frid;// 父回复ID
	private Integer staffid;// 员工ID
	private String fullname;// 员工姓名
	private String content;// 回复内容
	private Date sdate;// 回复时间

	/**
	 * 创建一个新的实例 TSubjectReply.
	 * 
	 */
	public TSubjectReply() {
		super();
	}

	public TSubjectReply(Integer rid) {
		this.rid = rid;
	}

	/**
	 * rid
	 * 
	 * @return the rid
	 */
	public Integer getRid() {
		return rid;
	}

	/**
	 * @param rid
	 *            the rid to set
	 */
	public void setRid(Integer rid) {
		this.rid = rid;
	}

	/**
	 * subjectid
	 * 
	 * @return the subjectid
	 */
	public Integer getSubjectid() {
		return subjectid;
	}

	/**
	 * @param subjectid
	 *            the subjectid to set
	 */
	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	/**
	 * frid
	 * 
	 * @return the frid
	 */
	public Integer getFrid() {
		return frid;
	}

	/**
	 * @param frid
	 *            the frid to set
	 */
	public void setFrid(Integer frid) {
		this.frid = frid;
	}

	/**
	 * staffid
	 * 
	 * @return the staffid
	 */
	public Integer getStaffid() {
		return staffid;
	}

	/**
	 * @param staffid
	 *            the staffid to set
	 */
	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	/**
	 * fullname
	 * 
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
		return "TSubjectReply [rid=" + rid + ", subjectid=" + subjectid + ", frid=" + frid + ", staffid=" + staffid + ", fullname=" + fullname + ", content=" + content + ", sdate=" + sdate + ", ]";
	}
}
