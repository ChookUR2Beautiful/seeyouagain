/**   
 * 文件名：TLoginRecord.java   
 *    
 * 日期：2014年11月19日16时58分41秒  
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
 * 类名称：TLoginRecord
 * 
 * 类描述：商家账号登录记录
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日16时58分41秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLoginRecord extends BaseEntity  {
	
	private static final long serialVersionUID = -1337165133605589787L;

	private Integer id;// 记录ID
	private Integer sellerid;// 商家ID
	private Integer aid;// 账号ID
	private Date sdate;// 登录时间
	private String account;// 登录账号
	private Integer type;// 账号类型|1=商家管理账号|2=收银员账号|3=普通店员账号
	private String system;// 手机系统
	private String remarks;// 备注
	
	//新增虚拟字段
	private String sellername;//商户名称
	private Date sdateStart;//记录时间查询条件
	private Date sdateEnd;//记录时间查询条件
	
	public String getTypeText(){
		if(type == null || type == 0) return null;
		switch (type) {
		case 1:
			return "商家管理账号";
		case 2:
			return "收银员账号";
		case 3:
			return "普通店员账号";
		default:
			return null;
		}
	}
	/**   
	 * 创建一个新的实例 TLoginRecord.   
	 *      
	 */
	public TLoginRecord() {
		super();
	}
	
	public TLoginRecord(Integer id) {
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
	 * system
	 * 
	 * @return the system
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system
	 *            the system to set
	 */
	public void setSystem(String system) {
		this.system = system;
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
	
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
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
		return "TLoginRecord [id=" + id + ", sellerid=" + sellerid + ", aid=" + aid + ", sdate=" + sdate + ", account=" + account + ", type=" + type + ", system=" + system + ", remarks=" + remarks + ", ]";
	}
}
