package com.xmniao.xmn.core.market.controller.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.market.pay.OrderListRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.OrderListService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: MarketOrderListController    
* @Description:积分超市列表   
* @author: liuzhihao   
* @date: 2017年1月13日 下午5:06:20
 */
@Controller
@RequestMapping("/api/v1/market/pay")
public class MarketOrderListController implements BaseVControlInf{

	//验证数据
	@Autowired
	private Validator validator;
	
	@Autowired
	private OrderListService orderListService;
	
	@RequestMapping(value="/order/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object directOrder(OrderListRequest orderListRequest){
		//验证数据
		List<ConstraintViolation> result = validator.validate(orderListRequest);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return versionControl(orderListRequest.getApiversion(),orderListRequest);
	}

	public Object versionOne(Object object){
		OrderListRequest orderListRequest = (OrderListRequest) object;
		return orderListService.list(orderListRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1:
				return versionOne(object);
			default:
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	
}
