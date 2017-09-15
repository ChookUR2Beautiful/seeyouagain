package com.xmniao.xmn.core.api.controller.personal;

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
import com.xmniao.xmn.core.common.request.personal.UserCouponRequset;
import com.xmniao.xmn.core.coupon.service.UserCouponService;

@Controller
public class UserCouponApi  implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserCouponApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private UserCouponService userCouponService;
	
	/**
	 * 
	* @Title: addReceivingAddress
	* @Description: 重置登录密码
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/personal/coupons" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object userCouponList(UserCouponRequset userCouponRequset){
		//验证参数
		List<ConstraintViolation> result = validator.validate(userCouponRequset);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(userCouponRequset.getApiversion(),userCouponRequset);
	}
	
	public Object versionOne(Object obj){
		UserCouponRequset userCouponRequset = (UserCouponRequset)obj;
		return userCouponService.queryUserCouponList(userCouponRequset);
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
