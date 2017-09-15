package com.xmniao.xmn.core.api.controller.live.coupon;

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
import com.xmniao.xmn.core.common.request.live.LiveBuyCouponRequest;
import com.xmniao.xmn.core.live.service.LiveBuyFansCouponService;


/**
 * 
* @Description: 用户调整到下单界面调用接口
* @return Object    返回类型
* @author yhl
* @throws
* 2016年8月15日11:31:15
 */
@Controller
public class LiveFansCouponToBuyApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveFansCouponToBuyApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private LiveBuyFansCouponService liveBuyFansCouponService;
	
	@RequestMapping(value = "/live/buy/fansCoupon" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object toBuyFansCoupon(LiveBuyCouponRequest liverBuyCouponRequest){
		log.info("liveRecordsRequest data:"+liverBuyCouponRequest.toString());
		List<ConstraintViolation> result = validator.validate(liverBuyCouponRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(liverBuyCouponRequest.getApiversion(), liverBuyCouponRequest);
	}
	
	public Object versionOne(Object obj){
		LiveBuyCouponRequest liverBuyCouponRequest = (LiveBuyCouponRequest)obj;
		return liveBuyFansCouponService.buyFansCoupon(liverBuyCouponRequest);
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
