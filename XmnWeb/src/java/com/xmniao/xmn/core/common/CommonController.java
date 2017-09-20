package com.xmniao.xmn.core.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.ResultFile;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;
import com.xmniao.xmn.core.util.FastFdsFile;
import com.xmniao.xmn.core.util.FastfdsConstant;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CommonController
 * 
 * 类描述： CommonController
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月3日 下午5:03:20
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */

@Controller
public class CommonController extends BaseController {

	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;
	
	/**
	 * 上传文件，返回ResultFile
	 * 
	 * @param filedata
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("filedata") MultipartFile filedata, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("文件上传Controller");
		String fileRootPath = "/files";
		boolean sltflag=request.getParameter("sltflag").equals("true")?true:false;
		Map<String,String> urlMap=new HashMap<String,String>();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		String result = "";
		String filename = filedata.getOriginalFilename();
		System.out.println("上传文件名："+filename+",sltflag:"+sltflag);
		String extendStr = filename.substring(filename.lastIndexOf("."));
		filename = System.currentTimeMillis() + extendStr;
		String filePath = request.getSession().getServletContext().getRealPath(fileRootPath) + "/" + filename;
		try {
			out = response.getWriter();
			//result =  FastFdsFile.getInstance().upload(filedata);
			urlMap=  FastFdsFile.getInstance().upload(filedata,sltflag);
			
		} catch (Exception e) {
			log.error("文件上传失败", e);
			out.println(JSON.toJSON(new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, "文件上传失败")));
			return;
		}
		ResultFile rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_SUCCESS, "文件上传成功");
		rf.setRelativePath(urlMap.get("url").split("\\.")[0]+"."+urlMap.get("url").split("\\.")[1]);
		if(urlMap.containsKey("breviary")){
		rf.setRelativeBreviaryPath(urlMap.get("breviary").split("\\.")[0]+"."+urlMap.get("breviary").split("\\.")[1]);
		}
		out.println(JSON.toJSON(rf));
	}
	@RequestMapping(value = "uploadFileTest", method = RequestMethod.POST)
	public void uploadFileTest(@RequestParam("filedata") MultipartFile filedata, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> urlMap=new HashMap<String,String>();
//		Resultable resultable = null;
		this.log.info("CommonController-->uploadFileTest filedata="+filedata);
		try {
			boolean flag=true;//是否产生缩略图开关
			urlMap  = FastFdsFile.getInstance().upload(filedata,flag);
			this.log.info("文件路径--》"+urlMap.get("url"));
//			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("文件上传失败", e);
//			resultable = new Resultable(false, "操作失败");
		}finally{
//			return resultable;
		}
		

	}
	
	
	/**
	 * 打开html页面
	 * @param response
	 * @param html
	 * @throws IOException
	 */
	@RequestMapping("openHtml")
	public void openHtml(HttpServletResponse response,@RequestParam("html")String html) throws IOException{
		response.setContentType("text/html");
		response.getWriter().print(html);
		
	}
	
	/*
	 * CkEditor 富媒体编辑器 文件上传
	 */
	@RequestMapping(value = "ckeditorUpload", method = {RequestMethod.POST})
	public void uploadFile3(@RequestParam("upload") MultipartFile filedata,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("文件上传Controller3");
		ckeditorUpdateService.ckeditorUpdate(filedata,request,response);
	}
	
	
	@RequestMapping("imageAddBatch/init")
	public Object freshImageAddBatch(){
		return "common/imageAddBatch";
		
	}
	

}
