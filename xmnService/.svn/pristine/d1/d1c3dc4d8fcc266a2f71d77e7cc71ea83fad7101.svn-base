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
import com.xmniao.xmn.core.common.request.market.pay.PayOrderRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.MarketOrderService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: MarketOrderPayController    
* @Description:支付订单   
* @author: liuzhihao   
* @date: 2016年12月26日 下午5:59:32
 */
@RequestMapping("/api/v1/market/pay")
@Controller
public class MarketOrderPayController implements BaseVControlInf{

	//验证数据
		@Autowired
		private Validator validator;
		
		@Autowired
		private MarketOrderService marketOrderService;
		
		@RequestMapping(value="/success",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object payOrder(PayOrderRequest payOrderRequest){
			//验证数据
			List<ConstraintViolation> result = validator.validate(payOrderRequest);
			if(result != null && !result.isEmpty()){
				String message ="";
				for(ConstraintViolation vo : result){
	                message+=vo.getMessage()+",";
	            }
				
				return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
			}
			return versionControl(payOrderRequest.getApiversion(),payOrderRequest);
		}

		public Object versionOne(Object object){
			PayOrderRequest payOrderRequest = (PayOrderRequest) object;
			return marketOrderService.payOrder(payOrderRequest);
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
