package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：TlsSigRequest   
* 类描述：   获取tlsSig签名
* 创建人：yezhiyong   
* 创建时间：2016年8月8日 上午10:07:56   
* @version    
*
 */
public class TlsSigRequest extends BaseRequest{
	
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

	@Override
	public String toString() {
		return "TlsSigRequest [sdkAppid=" + sdkAppid + ", identifier="
				+ identifier + ", account=" + account + "]";
	}

}
