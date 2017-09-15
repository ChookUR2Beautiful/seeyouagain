package com.xmniao.xmn.core.coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.coupon.request.CouponCardInfoRequest;
import com.xmniao.xmn.core.coupon.request.CouponCardInfoSubRequest;
import com.xmniao.xmn.core.coupon.request.CouponCardsRequest;
import com.xmniao.xmn.core.coupon.service.CouponCardsService;
import com.xmniao.xmn.core.market.common.Response;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: CardsController    
* @Description:用户卡卷列表   
* @author: liuzhihao   
* @date: 2017年2月27日 下午6:21:37
 */
@Controller
@RequestMapping("coupon")
public class CardsController {
	
	//注入数据验证service
	@Autowired
	private Validator validator;
	
	//注入我的卡卷接口
	@Autowired
	private CouponCardsService couponCards;
	
	/**
	 * 获取我的卡卷列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="card/list",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object list(CouponCardsRequest request){
		//验证数据
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
			
		}
		return couponCards.getCouponCards(request);
	}

	/**
	 * 充值卡详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value="card/info",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object info(CouponCardInfoRequest request){
		//验证数据
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
			
		}
		return couponCards.getCardInfo(request);
	}
	
	/**
	 * 充值详情子列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="card/info/sub/list",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object querySubList(CouponCardInfoSubRequest request){
		//验证数据
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
			
		}
		return couponCards.getCardInfoSubList(request);
	}
}
