package com.xmniao.xmn.core.market.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

import com.xmniao.xmn.core.util.JsonUtils;

public class Response implements Serializable {
    public static String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private int state; // 返回状态

    private String info; // 返回信息

    private Object response;

    public Response(int state , String info) {
        this.state = state;
        this.info = info;
    }

    public Response(int state , String info , Object result) {
        this.state = state;
        this.info = info;
        this.response = result;
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

    
    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public void write(HttpServletResponse response) throws IOException{
        response.setContentType(CONTENT_TYPE_JSON);
        
        PrintWriter writer = response.getWriter();
        
        writer.write(JsonUtils.toJSONString(this, null,null));
        
    }

    public void write(Map<Class<?>, String[]> conventions,HttpServletResponse response) throws IOException{
        setConventions(conventions);
        
        response.setContentType(CONTENT_TYPE_JSON);
        
        PrintWriter writer = response.getWriter();
        
        writer.write(JsonUtils.toJSONString(this, conventions,null));
        
    }
    
    public void write(String dateFormat,HttpServletResponse response) throws IOException{
        response.setContentType(CONTENT_TYPE_JSON);
        
        PrintWriter writer = response.getWriter();
        
        writer.write(JsonUtils.toJSONString(this, null,dateFormat));
        
    }
    
    public void write(Map<Class<?>, String[]> conventions,String dateFormat,HttpServletResponse response) throws IOException{
        setConventions(conventions);
        response.setContentType(CONTENT_TYPE_JSON);
        
        PrintWriter writer = response.getWriter();
        
        writer.write(JsonUtils.toJSONString(this, conventions, dateFormat));
        
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setConventions(Map<Class<?>, String[]> conventions){
        if(conventions != null){
            conventions.put(this.getClass(), new String[]{"state","info","response"});
            if(response instanceof Map){
                String[] keys = (String[])((Map)response).keySet().toArray(new String[]{});
                conventions.put(Object.class, keys);
            }
        }
    }

}
