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
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.xmer.service.StageService;

/**
 * 
* 项目名称：xmnService   
* 类名称：StageInfoApi   
* 类描述：驿站主页接口   
* 创建人：liuzhihao   
* 创建时间：2016年6月23日 下午4:51:03   
* @version    
*
 */
@Controller
public class StageInfoApi implements BaseVControlInf{
	
	//注入日志
	private final Logger log = Logger.getLogger(StageInfoApi.class);

	//注入Service
	@Autowired
	private StageService stageService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="stageInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object stageInfo(IDRequest idRequest){
		List<ConstraintViolation> result = validator.validate(idRequest);
		if(result.size() >0 && result != null){
			log.info("亲，你提交的数据有问题!^_^");
			return new BaseResponse(ResponseCode.DATAERR,"亲，你提交的数据有问题!^_^");
		}
		return versionControl(idRequest.getApiversion(), idRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1: return versionControlOne(object);
			case 2: return versionControlOne(object);
			default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	public Object versionControlOne(Object object){
		IDRequest idRequest = (IDRequest) object;
		return stageService.queryStageInfo(idRequest);
	}

	
}
