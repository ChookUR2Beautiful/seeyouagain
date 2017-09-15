package com.xmniao.xmn.core.api.controller.integral;

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
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.ProductInfoRequest;
import com.xmniao.xmn.core.integral.service.ProductInfoService;

/**
 * 
* 项目名称：xmnService   
* 类名称：IntegralProductInfoApi   
* 类描述：积分商城-积分商品详情页   
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 下午8:00:36   
* @version    
*
 */
@Controller
public class IntegralProductInfoApi{
	
	//日志
	private final Logger log = Logger.getLogger(IntegralProductInfoApi.class);
	
	//注入产品详情service
	@Autowired
	private ProductInfoService productInfoService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="integralProductInfo",method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object integralProductInfo(ProductInfoRequest productInfoRequest){
		List<ConstraintViolation> result = validator.validate(productInfoRequest);
		if(result.size() > 0 && result != null){
			log.info("请求参数不正确");
			return new BaseResponse(ResponseCode.DATAERR,"提交的参数不正确");
		}
		return productInfoService.productInfo(productInfoRequest);
	}

}
