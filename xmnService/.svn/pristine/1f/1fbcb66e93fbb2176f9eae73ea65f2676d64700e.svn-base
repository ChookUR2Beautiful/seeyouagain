package com.xmniao.xmn.core.api.controller.billPay;

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
import com.xmniao.xmn.core.common.request.order.BillpaymentRequest;
import com.xmniao.xmn.core.common.request.order.PaymentOrderRequest;
import com.xmniao.xmn.core.payment.service.BillPaymentService;

@Controller
public class BillOrderPaymentApi  implements BaseVControlInf {

	private final Logger log = Logger.getLogger(BillOrderPaymentApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private BillPaymentService billPaymentService;
	
	@RequestMapping(value="/bill/order/pay",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object billPayment(PaymentOrderRequest paymentOrderRequest){
		//日志
		log.info("paymentOrderRequest Data:"+paymentOrderRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(paymentOrderRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(paymentOrderRequest.getApiversion(), paymentOrderRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	private Object versionControlOne(Object object) {
		PaymentOrderRequest  paymentOrderRequest=(PaymentOrderRequest) object;
		//获取uid
		return billPaymentService.paymentOrder(paymentOrderRequest);
	}
	
	
}
