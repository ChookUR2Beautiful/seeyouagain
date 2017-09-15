package com.xmniao.xmn.core.api.controller.xmer;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.xmer.XmerDiscountRequest;
import com.xmniao.xmn.core.xmer.service.SellerService;
import com.xmniao.xmn.core.xmer.service.XmerDiscountService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 店铺连锁品牌
 */
@Controller
public class XmerSellerBrandApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(XmerSellerBrandApi.class);

	@Autowired
	private SellerService sellerService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	

	@RequestMapping(value="/seller/brand",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object brand(BaseRequest request){
		log.info("brand:"+request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(request.getApiversion(), request);
	}
	
	public Object versionOne(Object obj){
		BaseRequest request = (BaseRequest) obj;
 		return sellerService.findAllMultipleSeller(request);
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
