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
import com.xmniao.xmn.core.common.request.market.pay.GenerateOrderRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.MarketOrderService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @ClassName: GenerateOrderController 
* @Description:生成订单 
* @author: liuzhihao 
* @date 2017年4月19日 下午4:12:16 
*
 */
@Controller
@RequestMapping("/api/v1/market/pay")
public class GenerateOrderController implements BaseVControlInf{

	//验证数据
		@Autowired
		private Validator validator;
		
		@Autowired
		private MarketOrderService marketOrderService;
		
		@RequestMapping(value="/generateOrder",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object generateOrder(GenerateOrderRequest generateOrderRequest){
			
			List<ConstraintViolation> result = validator.validate(generateOrderRequest);
			
			if(result != null && result.size() > 0){
				String message ="";
				for(ConstraintViolation vo : result){
	                message+=vo.getMessage()+",";
	            }
				
				return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
			}
			
			return versionControl(generateOrderRequest.getApiversion(),generateOrderRequest);
		}

		public Object versionOne(Object object){
			GenerateOrderRequest generateOrderRequest = (GenerateOrderRequest) object;
			return marketOrderService.generateOrder(generateOrderRequest);
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
