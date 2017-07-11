package com.xmn.saas.base;

import com.xmn.saas.exception.SaasException;
import com.xmn.saas.utils.CookieUtils;
import com.xmn.saas.utils.WebUtils;

import java.io.IOException;
import java.util.Map;

public class AbstractController {
	public final static String SESSION_TOKEN = "sessionToken";
	
	public String getToken(){
		return WebUtils.getRequest().getParameter(SESSION_TOKEN);
	}
	//获取cookie的token
	public String getCookieToken(){
	    return CookieUtils.getVal(SESSION_TOKEN, WebUtils.getRequest());
	}



	public void success() throws IOException {
		new Response(ResponseCode.SUCCESS, "请求成功!").write();
	}
	public void success(String info) throws IOException {
		new Response(ResponseCode.SUCCESS, info).write();
	}

	public void success(Object result) throws IOException {
		new Response(ResponseCode.SUCCESS, "请求成功!", result).write();
	}

	public void success(Object result,Map<Class<?>, String[]> conventions) throws IOException {
		new Response(ResponseCode.SUCCESS, "请求成功!", result).write(conventions);
	}

	public void success(Object result,Map<Class<?>, String[]> conventions,String format) throws IOException {
		new Response(ResponseCode.SUCCESS, "请求成功!", result).write(conventions,format);
	}

	public void success(Object result, String format) throws IOException {
		new Response(ResponseCode.SUCCESS, "请求成功!", result).write(format);
	}

	public void failure(SaasException e) throws IOException {
		new Response(e.getCode(), e.getMessage()).write();
	}

	public void failure(String info) throws IOException {
		new Response(ResponseCode.FAILURE, info).write();
	}

	public void failure() throws IOException {
		new Response(ResponseCode.FAILURE, "请求失败!" ).write();
	}
}
