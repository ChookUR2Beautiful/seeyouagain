package com.xmniao.xmn.core.api.controller.urs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.login.LoginRequest;
import com.xmniao.xmn.core.common.request.login.UserValidateRequest;
import com.xmniao.xmn.core.login.UserLoginService;
import com.xmniao.xmn.core.login.VerifyCodeService;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;

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
public class UserResetPwdApi{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserResetPwdApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private UserLoginService loginService;
	
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@Autowired
	private UrsDao ursDao;
	
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 
	* @Description: 发送验证码请求
	* @return Object    返回类型
	* @throws 
	 */
	@RequestMapping(value = "/user/reset/pwd" ,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object productionCode(UserValidateRequest request)throws Exception{
		
		try {
			//验证手机号码是否填写
			String regExp = "^[1][0-9]{10}$";  
			Pattern p = Pattern.compile(regExp);  
			Matcher hasNum = p.matcher(request.getPhone());
			if (!hasNum.matches() && request.getPhone().length()!=11) {
				return new MapResponse(ResponseCode.FAILURE, "请输入正确手机号码");
			}else {
				
				if (StringUtils.isEmpty(request.getCode()) || "null".equals(request.getCode()) ) {
					return new MapResponse(ResponseCode.FAILURE, "请输入短信验证码!");
				}
				if (StringUtils.isEmpty(request.getPassword()) || "null".equals(request.getPassword()) ) {
					return new MapResponse(ResponseCode.FAILURE, "请输入密码!");
				}
				//查询手机号码是否被注册过
				Urs urs = ursDao.getUrsByUname(request.getPhone());
				if (urs==null) {
					return new MapResponse(ResponseCode.FAILURE, "该手机未被注册,请核对!");
				}
				Map<Object, Object> editMap = new HashMap<Object, Object>();
				
				String smsCode = stringRedisTemplate.opsForValue().get("SMS_"+request.getPhone());
				
				if (!StringUtils.isEmpty(smsCode) && !"null".equals(smsCode) && smsCode.equalsIgnoreCase(request.getCode())) {
					editMap.put("uid", urs.getUid());
					editMap.put("password", request.getPassword());
				}else {
					return new MapResponse(ResponseCode.FAILURE, "验证码错误或已过期");
				}
				
				int resultNum = ursDao.updateUrsByUid(editMap);
				
				if (resultNum>0) {
					
					//自动登录
					LoginRequest loginRequest = new LoginRequest();
					loginRequest.setPhone(request.getPhone());
					loginRequest.setPassWord(request.getPassword());
					return userLoginService.userLogin(loginRequest);
				}else {
					return new MapResponse(ResponseCode.FAILURE, "修改密码失败,请重试");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "发送短信异常");
		}
		
	}
	
}
