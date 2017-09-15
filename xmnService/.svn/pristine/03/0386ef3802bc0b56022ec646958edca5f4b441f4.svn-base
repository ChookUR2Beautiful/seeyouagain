package com.xmniao.xmn.core.api.controller.order;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.BillOrderQueryRequest;
import com.xmniao.xmn.core.order.service.BillOrderInfoService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

@Controller
public class BillOrderRedpacketQueryApi  implements BaseVControlInf {

	private final Logger log = Logger.getLogger(BillOrderRedpacketQueryApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private BillOrderInfoService billOrderInfoService;
	
	@RequestMapping(value="/bill/order/redpacket",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object billPayment(BillOrderQueryRequest billOrderQueryRequest){
		//日志
		log.info("billOrderQueryRequest Data:"+billOrderQueryRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(billOrderQueryRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		if (StringUtils.isEmpty(billOrderQueryRequest.getOrderNo()) || "null".equalsIgnoreCase(billOrderQueryRequest.getOrderNo())) {
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(billOrderQueryRequest.getApiversion(), billOrderQueryRequest);
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
		BillOrderQueryRequest  billOrderQueryRequest=(BillOrderQueryRequest) object;
		//获取uid
		return billOrderInfoService.queryBillOrderRedPacketView(billOrderQueryRequest);
	}
	
	
}
