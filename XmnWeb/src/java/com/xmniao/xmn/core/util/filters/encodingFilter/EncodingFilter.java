package com.xmniao.xmn.core.util.filters.encodingFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AuthorityFilter
 * 
 * 类描述：编码过滤器
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-02-10 10:00:17
 *
 **/
public class EncodingFilter implements Filter{
	/**
	 * 编码
	 */
	private String encoding = "UTF-8";
    public void setEncoding(String encoding) { this.encoding = encoding; }
    public String getEncoding() { return encoding; }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		setEncoding(filterConfig.getInitParameter("encoding"));
	}
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		if("GET".equals(request.getMethod())){
			// 对URL中的参数进行编码转换 
			request = new GetHttpServletRequestWrapper(request, getEncoding());
		}
		chain.doFilter(request, response);
	}
}
