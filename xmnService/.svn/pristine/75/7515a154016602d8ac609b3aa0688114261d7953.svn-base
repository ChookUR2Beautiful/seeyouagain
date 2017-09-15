package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class LoginRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="登录账号不能为空")
	private String account;//登录账号
	@NotNull(message="登录密码不能为空")
	private String password;//登录密码
	@NotNull(message="密码类型不能为空")
	private Integer pwdtype;//密码类型，默认0 0 登录密码 1 随机密码
	
	private String phonemodel;//安卓机型号
	
	private String iostoken;//ios必传 ，ios令牌
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getPwdtype() {
		return pwdtype;
	}
	public void setPwdtype(Integer pwdtype) {
		this.pwdtype = pwdtype;
	}
	public String getPhonemodel() {
		return phonemodel;
	}
	public void setPhonemodel(String phonemodel) {
		this.phonemodel = phonemodel;
	}
	public String getIostoken() {
		return iostoken;
	}
	public void setIostoken(String iostoken) {
		this.iostoken = iostoken;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginRequest [account=" + account + ", password=" + password
				+ ", pwdtype=" + pwdtype + ", phonemodel=" + phonemodel
				+ ", iostoken=" + iostoken + "]";
	}
	
}
