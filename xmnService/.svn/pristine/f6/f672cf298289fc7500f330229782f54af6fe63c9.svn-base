package com.xmniao.xmn.core.api.controller.order;

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
import com.xmniao.xmn.core.order.service.FreshOrderProcessService;

/**
 * 项目描述：XmnService
 * API描述：订单详情
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version
 * */

@Controller
@RequestMapping(value = "/integral")
public class IntegralOrderProcessViewApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(IntegralOrderProcessViewApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired 
	private FreshOrderProcessService orderProcessService;
	
	/**
	 * @Description 订单详情 view
	 * @author yhl
	 * @param OrderProcessInfoRequest
	 * @return josn
	 * */
	@RequestMapping(value = "/order/view" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object getOrderView(OrderProcessInfoRequest orderProcessInfoRequest){
		List<ConstraintViolation> result = validator.validate(orderProcessInfoRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(orderProcessInfoRequest.getApiversion(),orderProcessInfoRequest);
	}
	
	/**
	 * @Description 订单详情操作服务层操作
	 * @author yhl
	 * @param Object
	 * @return Object
	 * */
	public Object versionOne(Object obj){
		OrderProcessInfoRequest orderProcessInfoRequest = (OrderProcessInfoRequest)obj;
		return orderProcessService.queryIntegralOrderView(orderProcessInfoRequest);
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
	
}
