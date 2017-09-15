package com.xmniao.xmn.core.common.request.login;


import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class UserValidateRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2026595869331148116L;

	@NotNull(message="手机号码不能为空")
	public String phone;
	
	public String code;
	
	//发送验证码来源  0 忘记密码  1 注册
	public Integer source=0;

	public String password;
	
	public String getCode() {
		return code;
	}
	
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserValidateRequest [phone=" + phone + ", code=" + code
				+ ", source=" + source + ", password=" + password + "]";
	}

	
}
