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
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.integral.service.ProductInfoService;

/**
 * 
* 项目名称：xmnService   
* 类名称：IntegralRecodeApi   
* 类描述：用户积分记录接口   
* 创建人：liuzhihao   
* 创建时间：2016年6月21日 下午2:32:02   
* @version    
*
 */
@Controller
public class IntegralRecodeApi implements BaseVControlInf{
	
	//日志
	private final Logger log = Logger.getLogger(IntegralRecodeApi.class);

	//注入service
	@Autowired
	private ProductInfoService productInfoService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="integralRecode",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object integralRecode(PageRequest pageRequest){
		List<ConstraintViolation> result = validator.validate(pageRequest);
		if(result.size() >0 && result != null){
			log.info("你提交的数据有问题");
			return new BaseResponse(ResponseCode.DATAERR,"你提交的数据有问题");
		}
		return versionControl(pageRequest.getApiversion(), pageRequest);
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
		PageRequest pageRequest = (PageRequest) object;
		return productInfoService.integralRecode(pageRequest);
	}
}
