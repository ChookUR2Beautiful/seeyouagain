package com.xmniao.xmn.core.market.controller.pay;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.market.pay.CouponListRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.MarketCouponService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: MarketCouponListController    
* @Description:积分商城优惠卷列表   
* @author: liuzhihao   
* @date: 2016年12月22日 下午4:01:06
 */
@RequestMapping("/api/v1/market/pay")
@Controller
public class MarketCouponListController implements BaseVControlInf{

	@Autowired
	private MarketCouponService marketCouponService;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//验证数据
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/coupon/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object list(CouponListRequest couponListRequest){
		//验证数据
		List<ConstraintViolation> result = validator.validate(couponListRequest);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return versionControl(couponListRequest.getApiversion(),couponListRequest);
	}

	public Object versionOne(Object object){
		CouponListRequest request = (CouponListRequest) object;
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
		return marketCouponService.getUsableCouponsByUid(uid, request.getCarts(), request.getBuyType());
		
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
