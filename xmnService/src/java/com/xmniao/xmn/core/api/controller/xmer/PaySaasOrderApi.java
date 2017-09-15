package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.xmer.SaasPayRequest;
import com.xmniao.xmn.core.xmer.service.SaasOrderService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
 * @ClassName:PaySaasOrderApi
 * @Description:购买saas套餐支付接口
 * @Author:xw
 * @Date:2017年5月5日下午1:56:23
 */
@Controller
public class PaySaasOrderApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(MaterialpayApi.class);

	@Resource
	private Validator validator;
	
	@Resource
	private SaasOrderService saasOrderService;

	@RequestMapping(value = "/paySaasOrder", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Object paySaasOrder(SaasPayRequest saasPayRequest) {
		log.info("paySaasOrder参数：" + saasPayRequest.toString());

		List<ConstraintViolation> result = validator.validate(saasPayRequest);
		if (result != null && result.size() > 0) {
			log.info("提交的数据有问题" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据不正确!");
		}

		return versionControl(saasPayRequest.getApiversion(), saasPayRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return version(object);
		default:
			return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
		}
	}

	private Object version(Object object) {
		SaasPayRequest saasPayRequest = (SaasPayRequest) object;
		return saasOrderService.paySaasOrder(saasPayRequest);
	}
}
