package com.xmniao.xmn.core.base;

import java.io.Serializable;

import com.xmniao.xmn.core.common.ConstantDictionary;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
/***
 * 
* 项目名称：saasService   
* 类名称：BaseRequest   
* 类描述：版本请求基类  
* 创建人：liuzhihao   
* 创建时间：2016年3月30日 上午9:45:14   
* @version    
*
 */
public class BaseRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2162750093470126053L;
	/**
	 * App端版本号 范围1.0.0-9.9.9
	 */
	@NotNull(message="App版本号错误！")
	private String appversion;	
	
	/**
	 * app端系统版本，如android 4.3或ios 7.1 
	 */
	@NotNull(message="App系统版本不能为空！")
	private String systemversion;
	
	/**
	 * API版本，默认版本1 范围1-99
	 */
	@Min(1)
	@Max(99)
	@NotNull(message="apiversion不能为空")
	private Integer apiversion=1;	
	
	/**
	 * 会话令牌
	 */
	private String sessiontoken;
	
	/**
	 * app来源(默认寻蜜鸟)
	 */
	private String appSource="xmn";

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getSystemversion() {
		return systemversion;
	}

	public void setSystemversion(String systemversion) {
		this.systemversion = systemversion;
	}

	public Integer getApiversion() {
		return apiversion;
	}

	public void setApiversion(Integer apiversion) {
		this.apiversion = apiversion;
	}

	public String getSessiontoken() {
		return sessiontoken;
	}

	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}

	public String getAppSource() {
		return appSource;
	}

	public void setAppSource(String appSource) {
		this.appSource = appSource;
	}

	@Override
	public String toString() {
		return "BaseRequest [appversion=" + appversion + ", systemversion=" + systemversion + ", apiversion="
				+ apiversion + ", sessiontoken=" + sessiontoken + ", appSource=" + appSource + "]";
	}

	
}
