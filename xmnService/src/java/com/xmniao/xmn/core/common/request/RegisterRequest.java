package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
* 项目名称：xmnService   
* 类名称：RegisterRequest   
* 类描述：注册接口实体
* 创建人：liuzhihao   
* 创建时间：2016年5月23日 下午5:01:50   
* @version    
*
 */
public class RegisterRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//邀请人id(当有推荐人时填写)
	
	@NotNull(message="注册电话不能为空")
	private String phone;//注册电话
	
	@NotNull(message="验证码不能为空")
	private String code;//验证码
	
	@NotNull(message="注册密码不能为空")
	private String password;//注册密码(MD5传输)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterRequest [id=" + id + ", phone=" + phone + ", code="
				+ code + ", password=" + password + "]";
	}
	
}
