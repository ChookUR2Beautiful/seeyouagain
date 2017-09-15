package com.xmniao.xmn.core.api.controller.personal;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SendCodeRequest;
import com.xmniao.xmn.core.personal.service.SwitchSettingService;
import com.xmniao.xmn.core.util.utilClass;
import com.xmniao.xmn.core.xmer.service.SendCodeService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 开关设置
 */
@Controller
public class SwitchSettingApi implements BaseVControlInf{
	
	//日志报错
	private final Logger log = Logger.getLogger(SwitchSettingApi.class);
	
	//验证
	@Autowired
	private Validator validator;
	@Autowired
	private SwitchSettingService switchSettingService;
    
	
	//验证码
	int code = 0;
	
	@RequestMapping(value="/live/switch",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object sendMsg(BaseRequest request){
		//验证参数是否为空
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(request.getApiversion(),request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	public Object versionControlOne(Object object){
		BaseRequest request= (BaseRequest) object;
		return switchSettingService.switchSettingProperties(request);
	}
}
