package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ResetWithdrawPasswordRequest   
* 类描述：   重置提现密码请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月15日 下午5:42:27   
* @version    
*
 */
public class ResetWithdrawPasswordRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1523462076730323428L;
	
	//手机号码
	@NotNull(message="手机号码不能为空")
	private String phone;
	
	//重置提现密码
	@NotNull(message="重置提现密码不能为空")
	private String password;
	
	//验证码
	@NotNull(message="验证码不能为空")
	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ResetWithdrawPasswordRequest [phone=" + phone + ", password="
				+ password + ", code=" + code + "]";
	}

}
