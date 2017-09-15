package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.RemoveCouponRequest;
import com.xmniao.xmn.core.seller.service.UserSellerAndPlatCouponSevice;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: RemoveCouponApi    
* @Description:删除用户的优惠卷   
* @author: liuzhihao   
* @date: 2016年11月26日 下午5:18:09
 */
@RequestMapping("/user/coupon")
@Controller
public class RemoveCouponApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(RemoveCouponApi.class);
	
	//我的优惠卷service 
	@Autowired
	private UserSellerAndPlatCouponSevice userSellerAndPlatCouponSevice;
	
	@Autowired
	private Validator validator;
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object queryRestaurantSellerCoupon(RemoveCouponRequest removeCouponRequest){
		//验证请求实体
		log.info("removeCouponRequest data:"+removeCouponRequest.toString());
		List<ConstraintViolation> result = validator.validate(removeCouponRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(removeCouponRequest.getApiversion(), removeCouponRequest);
		
	}

	public Object versionOne(Object object){
		RemoveCouponRequest request = (RemoveCouponRequest) object;
		try{
			int result =  userSellerAndPlatCouponSevice.removeCoupon(request);
			if(result != 0){
				return new BaseResponse(ResponseCode.SUCCESS,"删除成功");
			}
			return new BaseResponse(ResponseCode.FAILURE,"删除失败");
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
