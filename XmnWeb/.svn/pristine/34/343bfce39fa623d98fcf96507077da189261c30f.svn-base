/**   
 * 文件名：TSubject.java   
 *    
 * 日期：2014年11月19日10时52分25秒  
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
 * 类名称：TSubject
 * 
 * 类描述：话题
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时52分25秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSubject extends BaseEntity {

	private static final long serialVersionUID = 1385695086120484805L;
	private Integer subjectid;// 话题ID
	private String content;// 话题内容
	private Integer number;// 回复数量
	private Integer staffid;// 员工ID
	private String fullname;// 员工姓名
	private Date sdate;// 提交时间
	private String remarks;// 备注
	
	//新增虚拟字段
	private String phoneid;// 手机号码
	private String area;// 负责区域
	private Date sdateStart;//提交时间查询条件
	private Date sdateEnd;//提交时间查询条件

	/**
	 * 创建一个新的实例 TSubject.
	 * 
	 */
	public TSubject() {
		super();
	}

	public TSubject(Integer subjectid) {
		this.subjectid = subjectid;
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
	 * remarks
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSubject [subjectid=" + subjectid + ", content=" + content + ", number=" + number + ", staffid=" + staffid + ", fullname=" + fullname + ", sdate=" + sdate + ", remarks=" + remarks + ", ]";
	}
}
