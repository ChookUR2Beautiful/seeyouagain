/**   
 * 文件名：TSellerAccount.java   
 *    
 * 日期：2014年11月11日15时42分53秒  
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
 * 类名称：TSellerAccount
 * 
 * 类描述：商家账号
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时42分53秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerAccount extends BaseEntity  {
	
	private static final long serialVersionUID = -6028741203214044962L;

	private Integer aid;// 账号ID
	private Integer sellerid;// 商家ID
	private String account;// 登录账号
	private String nname;// 账号昵称
	private String fullname;// 账号姓名
	private String password;// 登录密码
	private String levelpass;// 二级密码
	private Integer type;// 账号类型1=商家管理账号|2=收银员账号|3=普通店员账号
	private Date sdate;// 添加时间
	private String remarks;// 备注
	private String phone;//手机号	
	private String iostoken;//ios令牌
	
	private String oldpassword;// 老登录密码
	private String oldlevelpass;// 老二级密码
		
	//虚拟字段
	private Date sdateStart;//添加时间查询条件
	private Date sdateEnd;//添加时间查询条件
	private Integer  uid; //员工绑定的会员id
	private Integer userStatus;//0正常，1删除
    
	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public TSellerAccount(Integer aid,Integer sellerid, String  account,String nname,String fullname,
			String password, String levelpass,Integer type,Date sdate,String remarks) {
		this.aid=aid;
		this.sellerid = sellerid;
		this.account = account;
		this.nname =nname;
		this.fullname = fullname;
		this.password =password;
		this.levelpass=levelpass;
		this.type =type;
		this.sdate = sdate;
		this.remarks = remarks;
	}
	
	public TSellerAccount(Integer aid,Integer sellerid, String  account,String nname,String fullname,
			String password, String levelpass,Integer type,Date sdate) {
		this(aid,sellerid,account,nname,fullname,password,levelpass,type,sdate,null);
	}
	
	public TSellerAccount(Integer aid,Integer sellerid, String  account,String nname,String fullname,
			String password, String levelpass,Integer type) {
		this(aid,sellerid,account,nname,fullname,password,levelpass,type,null);
	}
	
	public TSellerAccount(Integer aid,Integer sellerid, String  account,String nname,String fullname,
			String password, String levelpass) {
		this(aid,sellerid,account,nname,fullname,password,levelpass,null);
	}
	
	public TSellerAccount(Integer aid,Integer sellerid, String  account,String nname,String fullname,
			String password ) {
		this(aid,sellerid,account,nname,fullname,password,null);
	}
	
	public TSellerAccount(Integer aid,Integer sellerid, String  account,String nname,String fullname) {
		this(aid,sellerid,account,nname,fullname,null);
	}
	
	public TSellerAccount(Integer aid,Integer sellerid, String  account,String nname) {
		this(aid,sellerid,account,nname,null);
	}
	
	public TSellerAccount(Integer aid,Integer sellerid, String  account) {
		this(aid,sellerid,account,null);
	}
	public TSellerAccount(Integer aid,Integer sellerid) {
		this(aid,sellerid,null);
	}
	
	
	
	/**   
	 * 创建一个新的实例 TSellerAccount.   
	 *      
	 */
	public TSellerAccount() {
		this(null);
	}
	
	public TSellerAccount(Integer aid) {
		this(aid,null);
	}

	public String getTypeText(){
		if(type == null) return null;
		switch(type){
		case 1: return "商家管理账号";
		case 2: return "收银员账号";
		case 3: return "普通店员账号";
		case 4: return "连锁店账号";
		default : return null;
		}
		
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
	 * sellerid
	 * 
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid
	 *            the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	
	/**
	 * account
	 * 
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * nname
	 * 
	 * @return the nname
	 */
	public String getNname() {
		return nname;
	}

	/**
	 * @param nname
	 *            the nname to set
	 */
	public void setNname(String nname) {
		this.nname = nname;
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
	 * levelpass
	 * 
	 * @return the levelpass
	 */
	public String getLevelpass() {
		return levelpass;
	}

	/**
	 * @param levelpass
	 *            the levelpass to set
	 */
	public void setLevelpass(String levelpass) {
		this.levelpass = levelpass;
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}
	
	public String getIostoken() {
		return iostoken;
	}

	public void setIostoken(String iostoken) {
		this.iostoken = iostoken;
	}
	
	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getOldlevelpass() {
		return oldlevelpass;
	}

	public void setOldlevelpass(String oldlevelpass) {
		this.oldlevelpass = oldlevelpass;
	}

	@Override
	public String toString() {
		return "TSellerAccount [aid=" + aid + ", sellerid=" + sellerid
				+ ", account=" + account + ", nname=" + nname + ", fullname="
				+ fullname + ", password=" + password + ", levelpass="
				+ levelpass + ", type=" + type + ", sdate=" + sdate
				+ ", remarks=" + remarks + ", phone=" + phone + ", iostoken="
				+ iostoken + ", oldpassword=" + oldpassword + ", oldlevelpass="
				+ oldlevelpass + ", sdateStart=" + sdateStart + ", sdateEnd="
				+ sdateEnd + "]";
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
/*	@Override
	public String toString() {
		return "TSellerAccount [aid=" + aid + ", sellerid=" + sellerid + ", account=" + account + ", nname=" + nname + ", fullname=" + fullname + ", password=" + password + ", levelpass=" + levelpass + ", type=" + type + ", sdate=" + sdate + ", remarks=" + remarks + ", ]";
	}*/
}
