package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.util.StringUtils;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllianceAccount
 * 
 * 类描述：商家联盟店帐号实体
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-01-16 11:06:45
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class AllianceAccount extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1580179269654695209L;
	
	private Long aid;//帐号id
	private Long id;//编号id
	private String allianceName;//联盟商名称
	private String account;//帐号
	private String nname;//昵称
	private String fullname;//姓名
	private String password;//登录密码
	private String phone;//手机号
	private Date sdate;//添加时间
	private Date udate;//更新时间
	private Integer status;//'状态 默认0启用  1停用'
	
	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAllianceName() {
		return allianceName;
	}
	public void setAllianceName(String allianceName) {
		this.allianceName = allianceName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
//		if(StringUtils.hasLength(password)){
//			password = NMD5.Encode(password);
//		}
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AllianceAccount [aid=" + aid + ", id=" + id + ", account="
				+ account + ", nname=" + nname + ", fullname=" + fullname
				+ ", password=" + password + ", phone=" + phone + ", sdate="
				+ sdate + ", udate=" + udate + "]";
	}
	

}
