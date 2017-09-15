package com.xmniao.xmn.core.api.controller.xmer;

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
import com.xmniao.xmn.core.common.request.SaasOrderRequest;
import com.xmniao.xmn.core.xmer.service.BuySaasPackageService;

/**
 *@ClassName:AddSaasPackageApi
 *@Description:购买saas押金套餐创建订单接口
 *@author hls
 *@date:2016年5月19日下午2:08:37
 */
@Controller
public class BuySaasPackageApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(BuySaasPackageApi.class);
	/**
	 * 注入service
	 */
	@Autowired
	private BuySaasPackageService buySaasPackageService;

	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/buySaasPackage",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object addSaasOrder(SaasOrderRequest saasOrderRequest){
		log.info("buySaasPackage参数:"+saasOrderRequest.toString());
		List<ConstraintViolation> result = validator.validate(saasOrderRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(saasOrderRequest.getApiversion(),saasOrderRequest);
	}
	
	public Object versionOne(Object obj){
		SaasOrderRequest rechargeRequest = (SaasOrderRequest) obj;
		return buySaasPackageService.createSaasOder(rechargeRequest);
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
