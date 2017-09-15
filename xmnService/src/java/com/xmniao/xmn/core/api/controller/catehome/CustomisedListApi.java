package com.xmniao.xmn.core.api.controller.catehome;

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
import com.xmniao.xmn.core.common.request.seller.RecomSellerRequest;
import com.xmniao.xmn.core.seller.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: CustomisedListApi    
* @Description:专属口味列表   
* @author: liuzhihao   
* @date: 2016年12月6日 下午2:01:35
 */
@RequestMapping("/customise")
@Controller
public class CustomisedListApi implements BaseVControlInf{
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(CustomisedListApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 专属口味列表
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryCustomisedList(RecomSellerRequest recomSellerRequest){
		log.info("recomSellerRequest data:"+recomSellerRequest.toString());
		List<ConstraintViolation> result = validator.validate(recomSellerRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(recomSellerRequest.getApiversion(), recomSellerRequest);
	}

	public Object versionOne(Object object){
		RecomSellerRequest request = (RecomSellerRequest) object;
		return sellerService.queryCustomisedSellers(request);
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
