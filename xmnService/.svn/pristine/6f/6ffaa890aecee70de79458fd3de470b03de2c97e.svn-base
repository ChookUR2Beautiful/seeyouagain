package com.xmniao.xmn.core.vod;

import java.lang.reflect.Method;
import java.util.TreeMap;

import com.xmniao.xmn.core.vod.Module.Base;


/**
 * @brief 模块调用类
 * @author robinslsun
 *
 */
public class QcloudApiModuleCenter {
	
	private final String SECRETID="AKIDEwqY2L6IWff56p4UrNTB9q0Ov0szBd9z";
	private final String SECRETKEY="ZTqh0rJBuE4hQCpLTGGmjHBQ7eeI21Bj";

	private Base module;
	
	/**
	 * 构造模块调用类
	 * @param module 实际模块实例
	 * @param config 模块配置参数
	 */
	public QcloudApiModuleCenter(Base module,String requestMethod){
		this.module = module;
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", SECRETID);
		config.put("SecretKey",SECRETKEY);
//		/* 请求方法类型 POST、GET */
		config.put("RequestMethod",requestMethod);
//		/* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
		config.put("DefaultRegion", "gz");
//		
		this.module.setConfig(config);
	}
	
	/**
	 * 生成Api调用地址
	 * @param actionName 模块动作名称
	 * @param params 模块请求参数
	 * @return Api调用地址
	 */
	public String generateUrl(String actionName, TreeMap<String, Object> params){
		return module.generateUrl(actionName, params);
	}
	
	/**
	 * Api调用
	 * @param actionName 模块动作名称
	 * @param params 模块请求参数
	 * @return json字符串
	 * @throws Exception
	 */
	public String call(String actionName, TreeMap<String, Object> params) throws Exception
	{
		for(Method method : module.getClass().getMethods()){
			if(method.getName().equals(actionName)){
				try {
					return (String) method.invoke(module, params);
				} catch (Exception e) {
					throw e;
				} 
			}
		}
		return module.call(actionName, params);
	}
	
	public void setConfigSecretId(String secretId) {
		module.setConfigSecretId(secretId);
	}

	public void setConfigSecretKey(String secretKey) {
		module.setConfigSecretKey(secretKey);
	}

	public void setConfigDefaultRegion(String region) {
		module.setConfigDefaultRegion(region);
	}

	public void setConfigRequestMethod(String method) {
		module.setConfigRequestMethod(method);
	}
}
