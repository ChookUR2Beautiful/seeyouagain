package com.xmniao.xmn.core.member.entity;

import com.xmniao.xmn.core.http.entity.InterfacRequest;

/**
 * 
 * 项目名称：xmnWeb
 * 
 * 类名称：MemberProvodedRequest
 * 
 * 类描述： 会员提现请求参数实体
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-10 15:08:59
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class MemberProvodedRequest extends InterfacRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4793654966759946545L;
	private String usertype="2,3";//提现用户类型
	private String username;//提现用户昵称
	private String state;//提现状态
	private String source;//提现来源
	private String startdate;//提现起始日期
	private String enddate;//提现终止日期
	private String phoneid;//商家手机号码 商家提现查询使用 
	private String loginAccount;//提现方帐号
	
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	private String userid;
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	@Override
	public String toString() {
		return "MemberProvodedRequest [usertype=" + usertype + ", username="
				+ username + ", state=" + state + ", source=" + source
				+ ", startdate=" + startdate + ", enddate=" + enddate + "]";
	}
	
	

}
