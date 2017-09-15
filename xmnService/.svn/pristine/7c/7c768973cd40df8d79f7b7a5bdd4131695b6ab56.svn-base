package com.xmniao.xmn.core.common.request.login;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class UserRegisterRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2026595869331148116L;

	@NotNull(message="登录账号不能为空")
	public String phone;
	
	@NotNull(message="密码不能为空")
	public String passWord;
	
	@NotNull(message="确认密码不能为空")
	public String confirmPwd;
	
	@NotNull(message="验证码不能为空")
	public String pcode;
	
	@NotNull(message="是否同意服务协议")
	public Integer hasAgree=0;
	
	//注册用户来源
	public Integer regtype;
	
	//手机品牌
	public String brand;
	
	//手机型号
	public String model;
	
	//手机型号
	public String regIp;
	
	//手机型号
	public Integer regcity;
	
	//经度
	public double lon=0D;
	
	//纬度
	public double lat=0D;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public Integer getHasAgree() {
		return hasAgree;
	}

	public void setHasAgree(Integer hasAgree) {
		this.hasAgree = hasAgree;
	}

	public Integer getRegtype() {
		return regtype;
	}

	public void setRegtype(Integer regtype) {
		this.regtype = regtype;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public Integer getRegcity() {
		return regcity;
	}

	public void setRegcity(Integer regcity) {
		this.regcity = regcity;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "UserRegisterRequest [phone=" + phone + ", passWord=" + passWord
				+ ", confirmPwd=" + confirmPwd + ", pcode=" + pcode
				+ ", hasAgree=" + hasAgree + ", regtype=" + regtype
				+ ", brand=" + brand + ", model=" + model + ", regIp=" + regIp
				+ ", regcity=" + regcity + ", lon=" + lon + ", lat=" + lat
				+ "]";
	}
	
}
