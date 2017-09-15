/**
 * 2016年5月14日 上午11:45:05
 */
package com.xmniao.xmn.core.util.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;

/**
 * @项目名称：xmnService
 * @类名称：SessionInterceptor
 * @类描述：session过滤器
 * @创建人： zhangchangyuan
 * @创建时间 2016年5月14日 上午11:45:05
 * @version
 */
public class SessionInterceptor extends HandlerInterceptorAdapter   {
	
	/**
	 * 无需过滤的URL
	 */
	private List<String> excludedUrls;
	
	/**
	 * session操作
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * session操作
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        
    }

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2) throws Exception {
        
    	String msg = "验证SESSIONTOKEN失败，请重新登录！";
    	
        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }
        
        //过滤非法字段
        Enumeration  enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName = (String)enu.nextElement();
            if (!"_dc".equals(paraName) && !"node".equals(paraName)){//_dc的参数不要
                String [] arr = request.getParameterValues(paraName);
                if (arr != null && arr.length > 0){
                    boolean flag =  this.ConvertObjectArrToStr(arr);
                    if (!flag) {
                    	msg = "警告,请求包含非法字符!";
                    	this.printMessage(response, ResponseCode.FAILURE, msg);
                 		return false;
					}
                }
            }
        }
        
        //获取参数中的sessionToken
        String sessionToken =request.getParameter("sessiontoken"); 
        response.setContentType("application/json;charset=UTF-8");
        
        try {
        	 if(StringUtils.isNotEmpty(sessionToken)){
        		 
        		String uid =  sessionTokenService.getStringForValue(sessionToken)+"";
        		
              	if(StringUtils.isEmpty(uid)||uid.equals("null")){
             		printMessage(response,ResponseCode.TOKENERR,"");
             		return false;
             		
     			}else {
     				
     				String  userRedisKey = "USERID_"+uid;
     				Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(userRedisKey);
     				
     				if (userMap!=null && userMap.size()>0) {
     					String code = userMap.get("code")!=null?userMap.get("code").toString():"";
     					if (!sessionToken.equals(code)) {
     						
     						stringRedisTemplate.delete(userRedisKey);
     						stringRedisTemplate.delete(sessionToken);
     						
     						printMessage(response,ResponseCode.TOKENERR,msg);
    	             		return false;
						}
     					
					}else {
						printMessage(response,ResponseCode.TOKENERR,msg);
	             		return false;
					}
     				
				}
             	/**
             	 * session有效
             	 */
             	return true;
             }
             //未传递sessiontoken
        	 printMessage(response,ResponseCode.TOKENERR,msg);
             return false;
		} catch (Exception e) {
			printMessage(response,ResponseCode.TOKENERR,msg);
     		return false;
		}
        
    }
    
    public void printMessage(HttpServletResponse response,int state,String msg) throws IOException{
    	PrintWriter pw =	response.getWriter();
 		String resposeStr = JSONObject.toJSONString(new BaseResponse(state, msg));	
 		pw.print(resposeStr);
 		pw.flush();
 		pw.close();
    }
    
    /**
     * 过滤请求中的非法字符
     * */
    public boolean ConvertObjectArrToStr(Object [] arr) {
    	
    	boolean flag = false;
    	String reg = "<,>,<frame,<iframe,<img,<javascript,<a,<script,<FRAME,<IFRAME,<IMG,<JAVASCRIPT,<A,<SCRIPT";
    	
    	String [] regArr = reg.split(",");
    	
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
            	if (!StringUtils.isEmpty(arr[i]+"")) {
            		String fielValue = String.valueOf(arr[i]);
            		for (int j = 0; j < regArr.length; j++) {
    					if (fielValue.indexOf(regArr[j].toString())>=0) {
    						 return flag;
    					}
    				}
				}
            }
        }
        return true;
    }
    
    

}
