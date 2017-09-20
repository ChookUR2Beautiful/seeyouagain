package com.xmniao.xmn.core.common.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xmniao.xmn.core.util.FastFdsFile;
import com.xmniao.xmn.core.util.FastfdsConstant;

@Service
public class CkeditorUpdateService {

	private Logger log = LoggerFactory.getLogger(CkeditorUpdateService.class);
	
	/*
	 * 处理Ckeditor文件上传问题
	 */
	public void ckeditorUpdate(MultipartFile filedata,HttpServletRequest request, HttpServletResponse response){
		boolean sltflag=false;
		Map<String,String> urlMap=new HashMap<String,String>();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		String filename = filedata.getOriginalFilename();
		System.out.println("上传文件名："+filename+",sltflag:"+sltflag);
		String extendStr = filename.substring(filename.lastIndexOf("."));
		
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
			log.error("文件上传失败", e1);
			return;
		}
		
		/* 判断上传文件类型只有图片类型才允许上传   */
		String[] filetype = {".jpg",".png",".jpeg",".bmp",".gif"};
		boolean pass=false;
		for(String f:filetype){
			if(extendStr.toLowerCase().equals(f)){
				pass=true;
				break;
			}
		}
		if(!pass){
			System.out.println("只支付上传jpg,png,jpeg,bmp,gif格式的图片文件");
			String callback = request.getParameter("CKEditorFuncNum");   
		    out.println("<script type=\"text/javascript\">");    
		    out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");   
		    out.println("</script>");  
		    return ;  
		}
		
		filename = System.currentTimeMillis() + extendStr;
		try {
			urlMap=  FastFdsFile.getInstance().upload(filedata,sltflag);
			System.out.println("urlMap:"+urlMap);
			
		} catch (Exception e) {
			log.error("文件上传失败", e);
			String callback = request.getParameter("CKEditorFuncNum");   
		    out.println("<script type=\"text/javascript\">");    
		    out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'上传失败，请检查服务器状态');");   
		    out.println("</script>");  
			return;
		}
		
		
		String callback =request.getParameter("CKEditorFuncNum");   
		out.println("<script type=\"text/javascript\">");  
		out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP+ urlMap.get("url").substring(0,urlMap.get("url").lastIndexOf(".")) + "','')");   
		out.println("</script>");  
	}
}
