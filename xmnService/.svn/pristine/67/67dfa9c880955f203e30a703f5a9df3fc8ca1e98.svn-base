package com.xmniao.xmn.core.api.controller.seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.UserCouponRequest;
import com.xmniao.xmn.core.seller.service.UserSellerAndPlatCouponSevice;

/**
 * 
* @projectName: xmnService 
* @ClassName: RestaurantSellerCouponApi    
* @Description:我的礼券---餐饮商家礼券和平台礼券接口
* @author: liuzhihao   
* @date: 2016年11月26日 下午2:22:47
 */
@RequestMapping("/user/coupon")
@Controller
public class UserSellerAndPlatCouponApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserSellerAndPlatCouponApi.class);
	
	//我的优惠卷service 
	@Autowired
	private UserSellerAndPlatCouponSevice userSellerAndPlatCouponSevice;
	
	@Autowired
	private Validator validator;
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object queryRestaurantSellerCoupon(UserCouponRequest userCouponRequest){
		//验证请求实体
		log.info("userCouponRequest data:"+userCouponRequest.toString());
		List<ConstraintViolation> result = validator.validate(userCouponRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(userCouponRequest.getApiversion(), userCouponRequest);
		
	}

	public Object versionOne(Object object){
		Map<Object,Object> map = new HashMap<Object,Object>();
		UserCouponRequest request = (UserCouponRequest) object;
		
		try{
			//查询商家券/平台券/粉丝券
			List<Map<Object,Object>> result = userSellerAndPlatCouponSevice.queryUserCoupons(request);
			map.put("coupons", result);
			
			//如果是粉丝券,则查询推荐的粉丝券 1、根据用户定位，按距离显示附近店铺粉丝券   2、根据可使用时间，由近至远排序
			if (request.getType() ==2) {
				if (request.getPage() == 1) {
					//获取推荐粉丝券列表
					List<Map<Object,Object>> recommendFansCouponList = userSellerAndPlatCouponSevice.queryRecommendFansCoupon(request);
					map.put("recommendFansCouponList", recommendFansCouponList);
					
				}else {
					map.put("recommendFansCouponList", new ArrayList<>());
				}
			}
			
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询异常");
		}
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
