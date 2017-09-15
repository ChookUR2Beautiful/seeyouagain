package com.xmniao.xmn.core.common.request.login;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class LoginRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2026595869331148116L;

	@NotNull(message="登录账号不能为空")
	public String phone;
	
	@NotNull(message="密码不能为空")
	public String passWord;
	
	//手机品牌
	public String brand;
	
	//手机型号
	public String model;
	
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

	@Override
	public String toString() {
		return "LoginRequest [phone=" + phone + ", passWord=" + passWord
				+ ", brand=" + brand + ", model=" + model + "]";
	}

	
}
