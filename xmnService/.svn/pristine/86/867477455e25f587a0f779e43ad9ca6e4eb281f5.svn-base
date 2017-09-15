package com.xmniao.xmn.core.api.controller.integral;

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
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IntegralMallHomeRequest;
import com.xmniao.xmn.core.integral.service.IntegralMallService;

/**
 * 
* 项目名称：xmnService   
* 类名称：IntegralMallHomeApi   
* 类描述：积分商城-首页接口    
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 上午10:37:16   
* @version    
*
 */
@Controller
public class IntegralMallHomeApi implements BaseVControlInf{
	
	//报错日志
	private final Logger log = Logger.getLogger(IntegralMallHomeApi.class);
	
	//注入service
	@Autowired
	private IntegralMallService integralMallService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/integralMallHome",method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object integralMallHome(IntegralMallHomeRequest integralMallHomeRequest){
		List<ConstraintViolation> result = validator.validate(integralMallHomeRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new MapResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(integralMallHomeRequest.getApiversion(), integralMallHomeRequest);
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
		IntegralMallHomeRequest integralMallHomeRequest = (IntegralMallHomeRequest) object;
		return integralMallService.IntegralMallHome(integralMallHomeRequest);
	}
	

}
