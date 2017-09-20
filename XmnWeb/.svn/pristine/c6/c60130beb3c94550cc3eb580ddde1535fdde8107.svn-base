package com.xmniao.xmn.core.util.filters.authorityFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.UrlPathHelper;

import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.holder.DataAuthorityHolder;
import com.xmniao.xmn.core.util.holder.DataAuthorityInfo;



/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AuthorityFilter
 * 
 * 类描述：权限过滤器
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-02-10 10:00:17
 * 
 */
public  class AuthorityFilter implements Filter {

	/**
	 * 不需要过滤页面
	 */
	private List<String> skiPages =new CopyOnWriteArrayList<String>();
	
	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	/**
	 * 过滤类型页面
	 */
	private List<String> filterTypes=new CopyOnWriteArrayList<String>();
	
	/**
	 * 初始化加载页面
	 */
	private List<String> loginSkipPage=new CopyOnWriteArrayList<String>();
	
	private static final String MAIN_URL="/main.jhtml";
	private static final String LOGIN_URL="/login.jhtml?msg=";
	private static final String URLEncoder = "UTF-8";
	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		//不需要过滤的url
		StringUtils.paresToList(config.getInitParameter("skipPage"),skiPages,",");
		//过滤的后缀类型
		StringUtils.paresToList(config.getInitParameter("filterTypes"),filterTypes,",");
		//登录后跳过的url
		StringUtils.paresToList(config.getInitParameter("loginSkipPage"),loginSkipPage,",");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest)req;
		HttpServletResponse response =(HttpServletResponse)res;
		String requestUrl	= request.getServletPath();
		try{
			//是否是需要过滤的页面
			if(ListContainsUrl(requestUrl,filterTypes)){
				//是否是需要跳过的页面
				if(!ListContainsUrl(requestUrl,skiPages)){
					HttpSession session=request.getSession(false); 
					// seesion效验  返回状态码
					int status = sessionValidateHandle(request,response,session,requestUrl);
					//根据状态码取得对应消息
					String msg = msgHandle(status);
					//msg是否为空     false:可以访问url  true:进一步效验
					if(null != msg){
						validateHandel(status,request,response,msg);
						return;
					}		
				}
			}
		}catch(Exception e){}
		chain.doFilter(request, response);
		DataAuthorityHolder.remove();
	}


	@Override
	public void destroy() {}
	

	/**
	 * 判断url值是否存在容器中
	 * @param sArray 字符串容器
	 * @param value 比较的值
	 * @param toffset 下标 如果不为null则从该值开始比较
	 * @return
	 */
	private boolean contains(List<String> sArray, String value,Integer toffset) {
		for (int i = 0; i < sArray.size(); i++) {
			String v = sArray.get(i);
			//如果 toffset有值  则从 toffset开始比较字符串
			int to = (null != toffset ? toffset : value.length() - v.length());
			if(value.startsWith(v, to)){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	/**
	 * 判断url是否包含于容器中 
	 * @param requestUrl url
	 * @param urlContainer 容器
	 * @return
	 */
	private boolean ListContainsUrl(String requestUrl,List<String> urlContainer){
		return ListContainsUrl(requestUrl,urlContainer,null);
	}
	
	/**
	 * 判断url是否包含于容器中 
	 * @param requestUrl url
	 * @param urlContainer 元素容器
	 * @param toffset 容器元素与url比较的起始位置
	 * @return
	 */
	private boolean ListContainsUrl(String requestUrl,List<String> urlContainer,Integer toffset){
		return contains(urlContainer,requestUrl,toffset);
	}
	
	/**
	 * 验证是否是ajax请求
	 * @param request
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request){
		return StringUtils.hasLength(request.getHeader("x-requested-with")); 
		
	}
	
	/**
	 * 用户有效性与访问权限效验
	 * @param session
	 * @return
	 */
	private int sessionValidateHandle(HttpServletRequest request,HttpServletResponse response,HttpSession session,String requestUrl){
		return (session != null ? validSeesion(request,session,requestUrl):invalidSeesion(request,response));
	}
	
	/**
	 * seesion 有效  效验用户访问是否合法
	 * @param session
	 * @param requestUrl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int validSeesion(HttpServletRequest request,HttpSession session,String requestUrl){
		Integer  state = null;
		TUser user = (TUser)session.getAttribute("currentUs");
		//session存在 并且用户已经登录 
		if(user!=null){
			long roleId = user.getRoleId();
			
			
			
			//超级管理员跳过一切权限
			if(roleId==1L){
				state = 10000;
			}else{
				
				setDataAuthorityInfo(request,roleId);
				//获取对于存储 用户所能访问url路径范围的容器
				List<String> accessScope = (List<String>)session.getAttribute("accessScope");
				//判断容器是否为空 空 返回登录页面 并提示相应信息
				if(accessScope==null || accessScope.isEmpty()){
					state = 10002;
				}else {
					//判断访问url是否为用户登录后访问不需要效验的url
					if(ListContainsUrl(requestUrl, loginSkipPage)){
						//初始化页面
						state = 10000;
					}else{
						//是否具有访问权限
						state = ListContainsUrl(requestUrl,accessScope,1) ? 10000:10003;//10000 有权限 10003没有权限
					}
				}
			}
		}else{
			state = 10001;//未登录状态码
			
		}
		return state;
	}
	
	/**
	 * 设置数据权限信息
	 * @param request
	 * @param roleId
	 */
	private void setDataAuthorityInfo(HttpServletRequest request,long roleId){
		String url = urlPathHelper.getLookupPathForRequest(request);
		url = url.substring(1);
		url = url.substring(0, url.lastIndexOf("."));
		DataAuthorityInfo authorityInfo = new DataAuthorityInfo();
		authorityInfo.setRoleId(roleId);
		authorityInfo.setUrl(url);
		//保存至数据权限拦截对象中
		DataAuthorityHolder.putDataAuthorityInfo(authorityInfo);
	}
	
	/**
	 * seesion 无效 判断用户是否未登录或者登录超时
	 * @param request
	 * @param response
	 * @return
	 */
	private int invalidSeesion(HttpServletRequest request,HttpServletResponse response){
		Integer  state = null;
		if(request.getCookies() !=null){               // 如果Cookie不为空
	        for(Cookie cookie :request.getCookies()){  // 遍历Cookie
	           if(cookie.getName().equals("flag")){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
	        		state = 10004;//登录超时状态码
	        		break;
	        	   }  
	           }
	    }
		if(null==state){
			state= 10001;//未登录状态码
		}
		return state;
	}
	

	
	/**
	 * 登录消息处理
	 * @param state
	 * @return
	 */
	private String msgHandle(int state){
		String msg= null;
		switch (state) {
		case 10000:
			break;
		case 10001:
			msg = "您未登录，请先登录！";
			break;	
		case 10002:
			msg = "未取得访问权限！";
			break;
		case 10003:
			msg = "未取得访问权限！";
			break;
		case 10004:
				msg = "登录超时，请重新登录！";
				break;
		}
		return msg;
	}
	
	/**
	 * 登录超时处理
	 * @param isAjax 是否ajax请求
	 * @param request
	 * @param response
	 * @param msg 超时消息
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private void timeOutHandler(boolean isAjax,HttpServletRequest request,HttpServletResponse response,String msg) throws Exception{
		if(isAjax){
			//ajax请求超时处理
			response.setStatus(1000);
		}else{
			//非ajax请求超时处理
			writerScriptRedirect(request, response, msg);
		}
	}
	
	/**
	 * url有效性处理
	 * @param status
	 * @param request
	 * @param response
	 * @param msg
	 * @throws Exception
	 */
	private void validateHandel(int status,HttpServletRequest request,HttpServletResponse response,String msg) throws Exception{
		if(status==10004){
			boolean isAjax=isAjax(request);
			timeOutHandler(isAjax,request,response,msg);
		}else if(status==10003){
			//登录用户访问未取得权限的页面
			response.sendRedirect(request.getContextPath()+MAIN_URL);
		}else{
			//未登录
			writerScriptRedirect(request, response, msg);
			
		}
		
	}
	
	/**
	 * 使用script标签跳转
	 * @param request
	 * @param response
	 * @param msg
	 * @throws Exception
	 */
	private void writerScriptRedirect(HttpServletRequest request,HttpServletResponse response,String msg) throws Exception{
		response.getWriter().print("<html><script type='text/javascript'> top.location.href='"+request.getContextPath()+LOGIN_URL+java.net.URLEncoder.encode(msg, URLEncoder)+"';</script></html>");
		response.setContentType("text/html");
	}



}
