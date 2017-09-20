package com.xmn.designer.filter;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.xmn.designer.base.Response;
import com.xmn.designer.base.ResponseCode;
import com.xmn.designer.service.base.RedisService;

/**
 * @Description:token过滤器实现
 * @author zhouxiaojian
 * @date 2016/09/19
 */
public class SessionTokenFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SessionTokenFilter.class);

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
		if (StringUtils.isEmpty(sessiontoken) || !sessionTokenService.checkDesignerSessionCacheObject(sessiontoken)) {
			Response map = new Response(ResponseCode.TOKENERR, "会话令牌错误!");
			log.info("会话令牌错误!");
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
