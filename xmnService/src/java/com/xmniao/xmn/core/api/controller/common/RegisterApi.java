package com.xmniao.xmn.core.api.controller.common;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.RegisterRequest;
import com.xmniao.xmn.core.xmer.service.RegisterService;

/**
 * 
* 项目名称：xmnService   
* 类名称：RegisterApi   
* 类描述：寻蜜客用户端注册接口   
* 创建人：liuzhihao   
* 创建时间：2016年5月23日 下午4:55:40   
* @version    
*
 */
@Controller
public class RegisterApi implements BaseVControlInf{

	//日志
	private final Logger log = Logger.getLogger(RegisterApi.class);
	
	//验证
	@Autowired
	private Validator validator;
	
	//注入service
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object xmerRegister(RegisterRequest registerRequest){
		//验证
		List<ConstraintViolation> result = validator.validate(registerRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题");
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(registerRequest.getApiversion(),registerRequest);
	}
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		
		}
	}
	public Object versionControlOne(Object object){
		RegisterRequest registerRequest = (RegisterRequest) object;
		return registerService.xmerRegister(registerRequest);
	}
}
