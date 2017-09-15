package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CheckWalletPwdRequest   
* 类描述：   验证提现密码请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月25日 下午4:34:24   
* @version    
*
 */
public class CheckWalletPwdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778876969621019526L;
	
	/**
	 * 提现密码
	 */
	@NotNull(message="提现密码不能为空")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CheckWalletPwdRequest [password=" + password + "]";
	}
	
}
