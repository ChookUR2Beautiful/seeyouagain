package com.xmniao.xmn.core.api.controller.experi;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.common.AppUpdateApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AppUpdateRequest;
import com.xmniao.xmn.core.common.request.order.ExperienceCardRequest;
import com.xmniao.xmn.core.common.service.AppUpdateService;
import com.xmniao.xmn.core.order.service.ExperienceOrderService;

@Controller
public class ExperienceOrderCancelApi {
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(AppUpdateApi.class);
	
	/**
	 * 注入service
	 */
	@Autowired
	private AppUpdateService appUpdateService;
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private ExperienceOrderService experienceOrderService;
	
	@RequestMapping(value="/experience/order/cancel")
	@ResponseBody
	public Object appUpdate(ExperienceCardRequest experienceCardRequest){
		//验证请求数据的合法性
		log.info("experienceCardRequest提交的数据:"+experienceCardRequest.toString());
		List<ConstraintViolation> result = validator.validate(experienceCardRequest);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(experienceCardRequest);
	}
	
	public Object versionAppUpdate(Object object){
		AppUpdateRequest appUpdateRequest = (AppUpdateRequest) object;
		return appUpdateService.queryAppUpdate(appUpdateRequest);
	}
	
	private Object versionControl(Object object) {
		ExperienceCardRequest  request=(ExperienceCardRequest) object;
		//获取uid
		return experienceOrderService.cancelExperienceOrderInfo(request);
	}
}
