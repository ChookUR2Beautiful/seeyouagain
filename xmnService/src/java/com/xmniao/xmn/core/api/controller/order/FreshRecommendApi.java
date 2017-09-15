package com.xmniao.xmn.core.api.controller.order;

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
 * 查看订单物流详情
 * @author yhl 
 * 2016年6月21日10:07:29
 * */
@Controller
public class FreshRecommendApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(FreshRecommendApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired 
	private FreshOrderProcessService freshOrderProcessService;

	/**
	 * @Description 查看订单物流详情操作
	 * @author yhl
	 * @param OrderProcessInfoRequest
	 * @return josn
	 * */
	@RequestMapping(value = "/live/fresh/recommend" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object getOrderView(OrderProcessInfoRequest orderProcessInfoRequest){
		if (null != orderProcessInfoRequest && orderProcessInfoRequest.getBid()!=null) {
			return versionControl(orderProcessInfoRequest.getApiversion(),orderProcessInfoRequest);
		}else {
			log.info("提交的数据不正确! 订单编号为空 null");
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
	}
	
	/**
	 * @Description 订单获取物流信息操作服务层操作
	 * @author yhl
	 * @param Object
	 * @return Object
	 * */
	public Object versionOne(Object obj){
		OrderProcessInfoRequest orderProcessInfoRequest = (OrderProcessInfoRequest)obj;
		return freshOrderProcessService.queryRecommend(orderProcessInfoRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
			return versionOne(object);
	}
	
}
