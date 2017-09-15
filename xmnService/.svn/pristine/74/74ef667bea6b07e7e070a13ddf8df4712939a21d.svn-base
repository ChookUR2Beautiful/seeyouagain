package com.xmniao.xmn.core.api.controller.weixin;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.util.CookieUtils;


@Controller
@RequestMapping("/weixin/authz")
public class AuthzController {
	private static final Logger logger = LoggerFactory.getLogger(AuthzController.class);
	
	public static final String LOGIN_CALLBACK_URL_KEY = "login_callback_url_key"; 
	public static final String WEIXIN_OPENID_KEY = "weixin_openid_key";
	
    private String weixinAppid;
    
    private String weixinSecret;
    
    private String weixinLoginCallback;
    
    private String localDomain;
	
	@RequestMapping("/authorize")
	public String authorize(String callback,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(StringUtils.isNotEmpty(callback)){
			logger.info("Callback URL : {}", URLEncoder.encode(callback,"utf-8"));
			CookieUtils.setVal(LOGIN_CALLBACK_URL_KEY, URLEncoder.encode(callback,"utf-8"), response);
		}else{
			CookieUtils.delVal(LOGIN_CALLBACK_URL_KEY, response);
		}
		
		logger.info("GET LOGIN CALLBACK URL :{}",CookieUtils.getVal(LOGIN_CALLBACK_URL_KEY, request));
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize";
		
		String appid = weixinAppid;
		String redirectUri = weixinLoginCallback;
	
		redirectUri = URLEncoder.encode(redirectUri,"utf-8");
		
		url += "?appid=" + appid + "&redirect_uri=" + redirectUri + "&response_type=code&scope=snsapi_base&state=#wechat_redirect";
		return "redirect:" + url;
	}
	
	@RequestMapping("/callback")
	public String callback(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String code = request.getParameter("code");
		
		String openid = null;
		logger.info("Get code from Weixin Callback,value : {}",code);
		
		if(StringUtils.isNotEmpty(code)){
			String appid = weixinAppid;
			String secret = weixinSecret;
			
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
			logger.info("获取openID=============================="+url);
			
			try {
				String result = ClientCustomSSL.doGet(url);
				logger.info("Get Weixin openid,result:{}",result);
				
				if(result != null){
					JSONObject object = JSON.parseObject(result);
					if(StringUtils.isNotEmpty(object.getString("openid"))){
						openid = object.getString("openid");
					}
				}
			} catch (Exception e) {
				logger.error("Get Weixin openid Error,Message:{}",e.getMessage());
			}
			
		}
		
		PrintWriter out = response.getWriter();
		
		if(openid != null){
			String url = localDomain + "/weixin/authz/after_callback?openid=" + openid;
			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\">");
			sb.append("window.location.href='"+url+"'");
			sb.append("</script>");
			
			out.print(sb.toString());
			
		}else{
			out.print("Can't get weixin openid !!!!!!");
			out.flush();
		}
		
		return null;
	}
	
	@RequestMapping("/after_callback")
	public String afterCallback(String openid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CookieUtils.setVal(WEIXIN_OPENID_KEY, openid, response);
		
		String callback = CookieUtils.getVal(LOGIN_CALLBACK_URL_KEY, request);
		
		logger.info("GET LOGIN CALLBACK URL :{}",CookieUtils.getVal(LOGIN_CALLBACK_URL_KEY, request));
		
		PrintWriter out = response.getWriter();
		if(callback != null){
			return "redirect:" + URLDecoder.decode(callback,"utf-8");
		}else{
			out.print("Get weixin openid  successful,but there is not callback url !!!!!!");
			out.flush();
		}
		
		return null;
	}
	

	@Autowired
	public void setWeixinAppid(String weixinAppid) {
		this.weixinAppid = weixinAppid;
	}

	@Autowired
	public void setWeixinSecret(String weixinSecret) {
		this.weixinSecret = weixinSecret;
	}

	@Autowired
	public void setWeixinLoginCallback(String weixinLoginCallback) {
		this.weixinLoginCallback = weixinLoginCallback;
	}

	@Autowired
	public void setLocalDomain(String localDomain) {
		this.localDomain = localDomain;
	}
	
	
	
}
