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
 * 
*    
* 项目名称：xmnService   
* 类名称：CateCommentApi   
* 类描述：   个人中心-积分订单 确认收货Api
* 创建人：yezhiyong   
* 创建时间：2016年6月21日 下午4:20:24   
* @version    
*
 */
@Controller
public class PickUpConfirmApi implements BaseVControlInf {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(PickUpConfirmApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private FreshOrderProcessService freshOrderProcessService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/pickUpConfirm",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object pickUpConfirm(OrderProcessInfoRequest orderProcessInfoRequest){
		//验证参数
		log.info("orderProcessInfoRequest data:"+orderProcessInfoRequest.toString());
		List<ConstraintViolation> result = validator.validate(orderProcessInfoRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(orderProcessInfoRequest.getApiversion(), orderProcessInfoRequest);
	}
	
	public Object versionOne(Object obj){
		OrderProcessInfoRequest orderProcessInfoRequest = (OrderProcessInfoRequest) obj;
		return freshOrderProcessService.pickUpConfirm(orderProcessInfoRequest);
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
