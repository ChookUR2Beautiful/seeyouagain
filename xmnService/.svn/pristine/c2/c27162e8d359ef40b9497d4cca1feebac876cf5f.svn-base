package com.xmniao.xmn.core.api.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PhoneRequest;



@Controller
public class GetVerifyApi {
	
	//使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
		public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	
		@Autowired
		private SessionTokenService sessionService;
		
		/**
		 * 注入validator验证
		 */
		@Autowired
		private Validator validator;
		
	@RequestMapping(value="getVerify")
	public ModelAndView handleRequest(HttpServletRequest request,  
	    HttpServletResponse response,String phone) throws Exception { 
		response.setContentType("text/html; charset=utf-8");  
		if(phone==null||phone.length()==0){
			return null;
		}
		String sources=VERIFY_CODES;
		int codesLen = sources.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(4);
		for(int i = 0; i < 4; i++){
			verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
		}
		sessionService.setStringForValue(Constant.VALIDATE_CODE+phone, verifyCode.toString(), 5, TimeUnit.MINUTES);
		
		String filePath=request.getServletContext().getRealPath("/");
		
		File dir = new File(filePath+"/img/getVerify");
		int w =120, h = 50;
		File file = new File(dir, verifyCode + ".jpg");
		VerifyCodeUtils.outputImage(w, h, file, verifyCode.toString());
//	    out.write("img/getVerify/"+verifyCode+".jpg");   
	    FileInputStream fis = null;
	    response.setContentType("image/jpeg");
	    try {
	        OutputStream out = response.getOutputStream();
	        fis = new FileInputStream(file);
	        byte[] b = new byte[fis.available()];
	        fis.read(b);
	        out.write(b);
	        out.flush();
	    } catch (Exception e) {
	         e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	               fis.close();
	            } catch (IOException e) {
		        e.printStackTrace();
		    }   
	          }
	    }
	    
	    return null;
	}  
}
