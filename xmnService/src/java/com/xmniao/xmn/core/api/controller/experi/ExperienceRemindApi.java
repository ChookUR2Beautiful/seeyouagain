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
import com.xmniao.xmn.core.common.request.order.ExperienceRecommendRequest;
import com.xmniao.xmn.core.common.request.order.ExperienceRemindRequest;
import com.xmniao.xmn.core.common.service.AppUpdateService;
import com.xmniao.xmn.core.order.service.ExperienceConfigService;

@Controller
public class ExperienceRemindApi {
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(ExperienceRemindApi.class);
	
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;

	
	@Autowired
	private ExperienceConfigService experienceConfigService;
	
	@RequestMapping(value="/experience/remind")
	@ResponseBody
	public Object experienceRecommend(ExperienceRemindRequest experienceRemindRequest){
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(experienceRemindRequest);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(experienceRemindRequest);
	}
	
	private Object versionControl(Object object) {
		ExperienceRemindRequest  request=(ExperienceRemindRequest) object;
		return experienceConfigService.remindUserExperienceActivity(request);
	}
}
