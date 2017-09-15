package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ModifyTlsUserInfoRequest   
* 类描述：   设置腾讯云的用户资料请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月9日 下午2:23:01   
* @version    
*
 */
public class ModifyTlsUserInfoRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4700835124068806072L;

	//app应用
	@NotNull(message="sdkAppid不能为空")
	private String sdkAppid;
	
	//管理员账号
	@NotNull(message="管理员账号不能为空")
	private String identifier;
	
	//用户账号
	@NotNull(message="用户账号不能为空")
	private String account;
	
	//昵称
	private String nickName;
	
	//头像
	private String image;
	
	//个性签名
	private String selfSignature;

	public String getSdkAppid() {
		return sdkAppid;
	}

	public void setSdkAppid(String sdkAppid) {
		this.sdkAppid = sdkAppid;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSelfSignature() {
		return selfSignature;
	}

	public void setSelfSignature(String selfSignature) {
		this.selfSignature = selfSignature;
	}

	@Override
	public String toString() {
		return "ModifyTlsUserInfoRequest [sdkAppid=" + sdkAppid
				+ ", identifier=" + identifier + ", account=" + account
				+ ", nickName=" + nickName + ", image=" + image
				+ ", selfSignature=" + selfSignature + "]";
	}

}
