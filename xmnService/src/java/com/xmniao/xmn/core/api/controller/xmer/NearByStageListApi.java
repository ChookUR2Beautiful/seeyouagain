package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.NearbyStageRequest;
import com.xmniao.xmn.core.xmer.service.StageService;

public class NearByStageListApi extends BaseVControlInf{
	//日志
	private final Logger log = Logger.getLogger(StageListApi.class);
	
	//注入service
	@Autowired
	private StageService stageService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="nearbyStageList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryStageList(NearbyStageRequest request){
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("亲，你提交的数据有问题!^_^");
			return new BaseResponse(ResponseCode.DATAERR,"亲，你提交的数据有问题!^_^");
		}
		return versionControl(request.getApiversion(),request);
	}
	
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1: return versionControlOne(object);
			case 2: return versionControlOne(object);
			default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	public Object versionControlOne(Object object){
		NearbyStageRequest request = (NearbyStageRequest) object;
		return stageService.nearbyStageList(request);
	}
}
