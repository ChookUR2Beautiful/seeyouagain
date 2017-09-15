package com.xmniao.xmn.core.api.controller.login;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.login.LoginRequest;
import com.xmniao.xmn.core.login.UserLoginService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LoginApi   
* 类描述：用户登录
* 创建人：yhl  
* 创建时间：2017年4月27日16:31:22
* @version    
*
 */
@Controller
public class UserLogoutApi{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserLogoutApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private UserLoginService loginService;
	
	/**
	 * 
	* @Description: 登录请求
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/user/logout" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object addAnchorManager(BaseRequest request){
		//日志
		log.info("BaseRequest data:" + request.toString());
		//验证参数
		return loginService.userLogout(request);
	}
	
	
	
}
