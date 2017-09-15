package com.xmniao.xmn.core.common.request.live;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：TlsSigRequest   
* 类描述：   游客模式下,获取腾讯sig签名请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月8日 上午10:07:56   
* @version    
*
 */
public class GetVisitorTlsSigRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1125988925467947778L;

	/**
	 * 获取签名类型   0 指定设备sig签名    1 自定义sig签名  2 已有的自定义账号签名
	 */
	@NotNull(message="获取签名类型不能为空")
	private Integer type;
	
	/**
	 * 设备账号
	 */
	private String account;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
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
		return "GetVisitorTlsSigRequest [type=" + type + ", account=" + account
				+ "]";
	}

}
