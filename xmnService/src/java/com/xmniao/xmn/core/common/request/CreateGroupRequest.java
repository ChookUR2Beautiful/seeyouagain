package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：CreateGroupRequest   
* 类描述：   创建群组请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月10日 下午6:17:45   
* @version    
*
 */
public class CreateGroupRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 642021500720117899L;

	//app应用
	@NotNull(message="sdkAppid不能为空")
	private String sdkAppid;
	
	//管理员账号
	@NotNull(message="管理员账号不能为空")
	private String identifier;
	
	//用户账号
	@NotNull(message="用户账号不能为空")
	private String account;
	
	//群昵称
	@NotNull(message="群昵称不能为空")
	private String name;
	
	//群组形态:Public（公开群），Private（私密群），ChatRoom（聊天室），AVChatRoom（互动直播聊天室）
	@NotNull(message="群组形态不能为空")
	private String type;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "CreateGroupRequest [sdkAppid=" + sdkAppid + ", identifier="
				+ identifier + ", account=" + account + ", Name=" + name
				+ ", type=" + type + "]";
	}

}
