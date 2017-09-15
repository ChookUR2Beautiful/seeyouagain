package com.xmniao.xmn.core.api.controller.common;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.LoginRequest;
import com.xmniao.xmn.core.xmer.service.LoginService;

@Controller
public class LoginApi implements BaseVControlInf{
	
	//报错日志
	private Logger log = Logger.getLogger(LoginApi.class);

	//注入loginService
	@Autowired
	private LoginService loginService;
	
	//注入validator
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="login",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object login(LoginRequest loginRequest){
		List<ConstraintViolation> result = validator.validate(loginRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		//验证session
		String token = loginRequest.getSessiontoken();
		if(StringUtils.isEmpty(token)){
			//如果session为空，则第一次登录
			return versionControl(loginRequest.getApiversion(),loginRequest);
		}
		//非第一次登录
		return loginService.checkSession(token);
	}
	
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		LoginRequest request = (LoginRequest) object;
		return loginService.login(request);
	}

}
