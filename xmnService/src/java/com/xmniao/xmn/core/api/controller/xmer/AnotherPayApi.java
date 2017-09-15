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
import com.xmniao.xmn.core.common.request.OrderProcessInfoRequest;
import com.xmniao.xmn.core.xmer.service.SaasOrderService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AnotherPayApi   
* 类描述：   寻蜜客签约店铺代付接口
* 创建人：yezhiyong   
* 创建时间：2016年7月8日 上午10:21:12   
* @version    
*
 */
@Controller
public class AnotherPayApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(AnotherPayApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private SaasOrderService saasOrderService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/anotherPay",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object anotherPay(OrderProcessInfoRequest orderProcessInfoRequest){
		log.info("orderProcessInfoRequest data : " + orderProcessInfoRequest);
		List<ConstraintViolation> result = validator.validate(orderProcessInfoRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
			return versionControl(orderProcessInfoRequest.getApiversion(),orderProcessInfoRequest);
		
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionOne(Object object) {
	OrderProcessInfoRequest orderProcessInfoRequest=(OrderProcessInfoRequest) object;	
		return saasOrderService.anotherPay(orderProcessInfoRequest);
	}


}
