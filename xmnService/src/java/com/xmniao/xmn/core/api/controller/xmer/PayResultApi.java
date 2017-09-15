package com.xmniao.xmn.core.api.controller.xmer;

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
import com.xmniao.xmn.core.common.request.PayResultRequest;
import com.xmniao.xmn.core.xmer.service.SaasOrderService;

/**
 * 
* 项目名称：xmnService   
* 类名称：PayResultApi   
* 类描述：订单支付接口  
* 创建人：liuzhihao   
* 创建时间：2016年5月26日 下午5:06:57   
* @version    
*
 */
@Controller
public class PayResultApi implements BaseVControlInf{
	
	//日志
	private Logger log = Logger.getLogger(PayResultApi.class);
	
	//注入订单service
	@Autowired
	private SaasOrderService saasOrderService;

	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="queryPayResult",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryPayResult(PayResultRequest payResultRequest){
		List<ConstraintViolation> result = validator.validate(payResultRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(payResultRequest.getApiversion(), payResultRequest);
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
		PayResultRequest payResultRequest = (PayResultRequest) object;
		return saasOrderService.queryPayResult(payResultRequest);
	}
}
