/**   
 * 文件名：TStaff.java   
 *    
 * 日期：2014年11月19日11时26分27秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.business_cooperation.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TStaff
 * 
 * 类描述：合作商员工
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时26分27秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TStaff extends BaseEntity {

	private static final long serialVersionUID = 9062194700840665023L;
	private Integer staffid;// 员工ID
	private Integer jointid;// 合作商ID
	private String corporate;//合作商名称（t_staff没有这个字段，连表查用于显示）
	private Integer category;// 1=员工|2=合作商管理员
	private String fullname;// 员工姓名
	private Integer sex;// 1=男|2=女
	private String nickname;// 昵称
	private String headurl;// 头像URL
	private String phoneid;// 手机号码
	private String password;// 登录密码
	private Integer quota;// 月业务指标
	private String area;// 负责区域
	private String areaTitle;// 区域名称
	private Integer status;// 默认|0=启用|1=停用
	private Date sdate;// 加入时间
	private String sdateStr;//加入时间(用于展示)
	private String imei;// 绑定手机IMEI
	private String remarks;// 备注
	private Double amount;// 总分账金额
	private Double baseagio;// 提成折扣
	
	

	public String getAreaTitle() {
		return areaTitle;
	}

	public void setAreaTitle(String areaTitle) {
		this.areaTitle = areaTitle;
	}

	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	/**
	 * 创建一个新的实例 TStaff.
	 * 
	 */
	public TStaff() {
		super();
	}

	public TStaff(Integer staffid) {
		this.staffid = staffid;
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
	 * jointid
	 * 
	 * @return the jointid
	 */
	public Integer getJointid() {
		return jointid;
	}

	/**
	 * @param jointid
	 *            the jointid to set
	 */
	public void setJointid(Integer jointid) {
		this.jointid = jointid;
	}

	/**
	 * category
	 * 
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Integer category) {
		this.category = category;
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
	 * sex
	 * 
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * nickname
	 * 
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * headurl
	 * 
	 * @return the headurl
	 */
	public String getHeadurl() {
		return headurl;
	}

	/**
	 * @param headurl
	 *            the headurl to set
	 */
	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	/**
	 * phoneid
	 * 
	 * @return the phoneid
	 */
	public String getPhoneid() {
		return phoneid;
	}

	/**
	 * @param phoneid
	 *            the phoneid to set
	 */
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	/**
	 * password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * quota
	 * 
	 * @return the quota
	 */
	public Integer getQuota() {
		return quota;
	}

	/**
	 * @param quota
	 *            the quota to set
	 */
	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	/**
	 * area
	 * 
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * sdate
	 * 
	 * @return the sdate
	 */
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
	
	public String getSdateStr() {
		if(null != sdate) return DateUtil.smartFormat(sdate);
		if(null != sdate) return "--";
		return sdateStr;
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}
	/**
	 * imei
	 * 
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
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

	/**
	 * amount
	 * 
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * baseagio
	 * 
	 * @return the baseagio
	 */
	public Double getBaseagio() {
		return baseagio;
	}

	/**
	 * @param baseagio
	 *            the baseagio to set
	 */
	public void setBaseagio(Double baseagio) {
		this.baseagio = baseagio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TStaff [staffid=" + staffid + ", jointid=" + jointid + ", category=" + category + ", fullname=" + fullname + ", sex=" + sex + ", nickname=" + nickname + ", headurl=" + headurl + ", phoneid=" + phoneid + ", password=" + password + ", quota=" + quota + ", area=" + area + ", status=" + status + ", sdate=" + sdate + ", imei=" + imei + ", remarks=" + remarks + ", amount=" + amount + ", baseagio=" + baseagio + ", ]";
	}
}
