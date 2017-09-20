package com.xmniao.xmn.core.util;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.system_settings.entity.TUser;

public class ResultUtil {
	/**
	 * 返回页面
	 * 
	 * @param response
	 * @param obj 返回的数据
	 */
	public static void result(HttpServletResponse response, Object obj) {
		PrintWriter p=null;
		String msg=null;	
		try {
			msg=JSONObject.toJSONString(obj);
			response.setContentType("application/json;charset=UTF-8");
			p = response.getWriter();
			p.write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (null != p) {
				p.flush();
				p.close();
			}
		}
	}
	
	/**
	 * 获取当前用户
	 * @param request
	 * @return
	 */
	public static TUser getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			return (TUser)session.getAttribute("currentUs");
		}
		return null;
	}
	
}
