package com.xmn.saas.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.service.base.RedisService;

/**
 * @Description:token过滤器实现
 * @author zhouxiaojian
 * @date 2016/09/19
 */
public class SessionTokenFilter implements Filter {

	// 排除路径
	private String pathToBeIgnored;

	@Autowired
	private RedisService sessionTokenService;

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		if (StringUtils.isNotBlank(pathToBeIgnored)) {
			String path = req.getRequestURI();//访问路径
			if(pathToBeIgnored.contains(",")){
			    String [] pathToBeIgnoreds =  pathToBeIgnored.split(",");
			    for(String p:pathToBeIgnoreds){
			        if(path.contains(p)){
		                chain.doFilter(request, response);
		                return;
		            }
			    }
			}else if(path.contains(pathToBeIgnored)){
			    chain.doFilter(request, response);
                return;
			}
		}
		String sessiontoken = req.getParameter("sessionToken");

		// 若session为空
		if (StringUtils.isEmpty(sessiontoken) || !sessionTokenService.checkSessionCacheObject(sessiontoken)) {
			Response map = new Response(ResponseCode.TOKENERR, "会话令牌错误!");
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(map));
			out.close();
			
			response.getWriter().write(JSON.toJSONString(map));
			
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {

	}

	public String getPathToBeIgnored() {
		return pathToBeIgnored;
	}

	public void setPathToBeIgnored(String pathToBeIgnored) {
		this.pathToBeIgnored = pathToBeIgnored;
	}

}
