package com.xmniao.xmn.core.base;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.xmniao.xmn.core.base.export.ConcurrentStrategy;
import com.xmniao.xmn.core.base.export.ExcelWriter;
import com.xmniao.xmn.core.base.export.QueryCallback;
import com.xmniao.xmn.core.base.export.strategy.MultipleQueriesConcurrentStrategy;
import com.xmniao.xmn.core.base.export.strategy.OnceQueryConcurrentStrategy;
import com.xmniao.xmn.core.util.DateEditor;
import com.xmniao.xmn.core.util.StringUtils;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BaseController
 * 
 * 类描述： 基础控制器
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月3日 下午5:03:03
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */

public class BaseController {

	protected final Logger log = Logger.getLogger(getClass());
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	
	public void doExport(HttpServletRequest request, HttpServletResponse response, String tempFileName, Map<String, Object> params){
		Long size=null;
		for(String key : params.keySet()){
			if(StringUtils.hasLength(key)){
				size =(long)((List<?>)params.get(key)).size();
			}	
		}
		params.put("size",size);
		ConcurrentStrategy concurrentStrategy = new OnceQueryConcurrentStrategy(params);
		writeExcel(request, response, tempFileName, concurrentStrategy);
		
	}
	
	public void doLocal(HttpServletRequest request, String localFileName, String tempFileName, Map<String, Object> params){
		Long size=null;
		for(String key : params.keySet()){
			if(StringUtils.hasLength(key)){
				size =(long)((List<?>)params.get(key)).size();
			}	
		}
		params.put("size",size);
		ConcurrentStrategy concurrentStrategy = new OnceQueryConcurrentStrategy(params);
		writeLocalExcel(request, localFileName, tempFileName, concurrentStrategy);
		
	}
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @param tempFileName 模版名称
	 * @param concurrentStrategy 导出策略
	 */
	private void writeLocalExcel(HttpServletRequest request, String localFileName,String tempFileName,ConcurrentStrategy concurrentStrategy){
        InputStream is = null;
        OutputStream os = null;
		try {
            
	        is = new FileInputStream(tempFileName);
	        os = new FileOutputStream(localFileName);
    		ExcelWriter excelWriter = new ExcelWriter(concurrentStrategy);
    		Long s = System.currentTimeMillis();
    		excelWriter.writeExcel(is,  os);
    		Long e = System.currentTimeMillis();
			log.info("导出时间---"+(e-s));
		} catch (Exception e){
			log.error("导出数据出错，请重试", e);
			if(is != null){
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	protected <T> void  multipleQueriesExport(HttpServletRequest request, HttpServletResponse response, String tempFileName,long size , QueryCallback<T> callback,Object service,T entity){
		multipleQueriesExport(request,response,tempFileName,size,callback,service,entity,null);
	}
	
	protected <T> void multipleQueriesExport(HttpServletRequest request, HttpServletResponse response, String tempFileName,long size , QueryCallback<T> callback,Object service,T entity,Integer limit){
		
			ConcurrentStrategy concurrentStrategy =null;;
			if(limit==null){
				concurrentStrategy = new MultipleQueriesConcurrentStrategy<T>(size, callback, entity, service);
			}else{
				concurrentStrategy = new MultipleQueriesConcurrentStrategy<T>(size, limit , callback, entity, service);
			}
			writeExcel(request, response, tempFileName, concurrentStrategy);
		
		
	}
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @param tempFileName 模版名称
	 * @param concurrentStrategy 导出策略
	 */
	private void writeExcel(HttpServletRequest request, HttpServletResponse response,String tempFileName,ConcurrentStrategy concurrentStrategy){
    	try {
    		response.setContentType("application/vnd.ms-excel");     
            response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
            InputStream tempStream = request.getServletContext().getResourceAsStream("/WEB-INF/xls/" + tempFileName);
            
    		ExcelWriter excelWriter = new ExcelWriter(concurrentStrategy);
    		Long s = System.currentTimeMillis();
    		excelWriter.writeExcel(tempStream,  response.getOutputStream());
    		Long e = System.currentTimeMillis();
			log.info("导出时间---"+(e-s));
    		
		} catch (Exception e){
			log.error("导出数据出错，请重试", e);
			response.reset();
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out;
			String url = request.getContextPath() + "/main.jhtml";
			try {
				out = response.getWriter();
				StringBuffer buffer = new StringBuffer();
				buffer.append("<script language='javascript' type='text/javascript'>");
				buffer.append("alert('导出数据出错，请重试！');window.top.location.href='" + url + "'");
				buffer.append("</script>");
				out.println(buffer.toString());
			} catch (IOException ee) {
			}
		}
	}
	
	
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response,String templatePath,String title) {
		InputStream in=null;
        try {
			in = request.getServletContext().getResourceAsStream("/WEB-INF/xls/"+templatePath );
			BufferedInputStream bin = new BufferedInputStream(in);
			OutputStream out = response.getOutputStream();
			  byte[] buff =new byte[2048];
			  int len=-1;
			  do{
				  len =  bin.read(buff);
				  out.write(buff);
			  }while(len>-1);
			  response.setContentType("application/vnd.ms-excel");  
			  response.setHeader("Content-disposition", "attachment;filename=" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + ".xls");
		} catch (Exception e) {
			response.reset();
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out;
			String url = request.getContextPath() + "/main.jhtml";
			try {
				out = response.getWriter();
				StringBuffer buffer = new StringBuffer();
				buffer.append("<script language='javascript' type='text/javascript'>");
				buffer.append("alert('模版不存在，请重试！');window.top.location.href='" + url + "'");
				buffer.append("</script>");
				out.println(buffer.toString());
				out.flush();
			} catch (IOException ee) {
			}
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
