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
import com.xmniao.xmn.core.common.request.market.pay.OrderSettlementRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.MarketOrderService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @ClassName: MarketOrderSettlementController 
* @Description:订单结算(新)
* @author: liuzhihao 
* @date 2017年4月18日 下午5:49:39 
*
 */
@Controller
@RequestMapping("/api/v1/market/pay")
public class MarketOrderSettlementController implements BaseVControlInf{

	//验证数据
	@Autowired
	private Validator validator;
	
	@Autowired
	private MarketOrderService marketOrderService;
	
	@RequestMapping(value="/orderSettlement",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object orderSettlement(OrderSettlementRequest orderSettlementRequest){
		
		List<ConstraintViolation> result = validator.validate(orderSettlementRequest);
		
		if(result != null && result.size() > 0){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		
		return versionControl(orderSettlementRequest.getApiversion(),orderSettlementRequest);
	}

	public Object versionOne(Object object){
		OrderSettlementRequest orderSettlementRequest = (OrderSettlementRequest) object;
		return marketOrderService.orderSettlement(orderSettlementRequest);
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
