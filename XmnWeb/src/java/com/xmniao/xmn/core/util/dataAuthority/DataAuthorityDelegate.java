package com.xmniao.xmn.core.util.dataAuthority;

import org.springframework.web.context.WebApplicationContext;

import com.xmniao.xmn.core.util.dataAuthority.handler.AreaDataAuthorityHandler;

public class DataAuthorityDelegate {

	
	
	private DataAuthorityHandler dataAuthorityHandler = new AreaDataAuthorityHandler();
	
	
	
	public boolean isHandlerMethod(String currentMethod){
		
		return dataAuthorityHandler.isHandlerMethod(currentMethod);
	}
	
	public void setApplicationContext(WebApplicationContext context){
		dataAuthorityHandler.setApplicationContext(context);
	};
	
	public String buildSQL(Object parameterObject){
		
		return dataAuthorityHandler.buildSQL(parameterObject);
	}
	
	
}
