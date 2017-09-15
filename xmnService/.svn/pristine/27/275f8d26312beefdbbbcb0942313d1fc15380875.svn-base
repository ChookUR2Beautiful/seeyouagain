package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ResetLoginPasswordRequest   
* 类描述：   重置登录密码请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月15日 上午11:08:05   
* @version    
*
 */
public class ResetLoginPasswordRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1523462076730323428L;
	//当前密码
	@NotNull(message="当前密码不能为空")
	private String oldPassword;
	
	//新密码
	@NotNull(message="新密码不能为空")
	private String newPassword;
	
	//确认密码
	@NotNull(message="确认密码不能为空")
	private String confirmPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ResetLoginPasswordRequest [oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", confirmPassword="
				+ confirmPassword + "]";
	}

}
