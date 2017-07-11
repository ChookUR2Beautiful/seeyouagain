package com.xmn.saas.base;

import com.xmn.saas.utils.JsonUtils;
import com.xmn.saas.utils.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

public  class Response implements Serializable {
	public static String CONTENT_TYPE_JSON = "application/json;charset=utf-8" ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int state; // 返回状态

	private String info; // 返回信息
	
	private Object result;

   public Response(int state, String info){
		this.state = state;
		this.info = info;
   }
   
   public Response(int state, String info,Object result){
	   this.state = state;
	   this.info = info;
	   this.result = result;
   }



    public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public Object getResult() {
		return result;
	}

	public void write() throws IOException{
		HttpServletResponse response = WebUtils.getResponse();
		response.setContentType(CONTENT_TYPE_JSON);
		
		PrintWriter writer = response.getWriter();
		
		writer.write(JsonUtils.toJSONString(this, null,null));
		
	}

	public void write(Map<Class<?>, String[]> conventions) throws IOException{
		setConventions(conventions);
		
		HttpServletResponse response = WebUtils.getResponse();
		response.setContentType(CONTENT_TYPE_JSON);
		
		PrintWriter writer = response.getWriter();
		
		writer.write(JsonUtils.toJSONString(this, conventions,null));
		
	}
	
	public void write(String dateFormat) throws IOException{
		HttpServletResponse response = WebUtils.getResponse();
		response.setContentType(CONTENT_TYPE_JSON);
		
		PrintWriter writer = response.getWriter();
		
		writer.write(JsonUtils.toJSONString(this, null,dateFormat));
		
	}
	
	public void write(Map<Class<?>, String[]> conventions,String dateFormat) throws IOException{
		setConventions(conventions);
		
		HttpServletResponse response = WebUtils.getResponse();
		response.setContentType(CONTENT_TYPE_JSON);
		
		PrintWriter writer = response.getWriter();
		
		writer.write(JsonUtils.toJSONString(this, conventions, dateFormat));
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setConventions(Map<Class<?>, String[]> conventions){
		if(conventions != null){
			conventions.put(this.getClass(), new String[]{"state","info","result"});
			if(result instanceof Map){
				String[] keys = (String[])((Map)result).keySet().toArray(new String[]{});
				conventions.put(Object.class, keys);
			}
		}
	}
	
}
