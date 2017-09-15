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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;

@Controller
public class RelieveXmerApi implements BaseVControlInf{
	
	//日志
	private final Logger log = Logger.getLogger(RelieveXmerApi.class);

	//注入验证
	@Autowired
	private Validator validator;
	
	//注入寻蜜客service
	@Autowired
	private XmerInfoService xmerInfoService;
	@RequestMapping(value="relieveXmer",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object relieveXmer(BaseRequest baseRequest){
		
		//验证
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result.size() > 0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		
		return versionControl(baseRequest.getApiversion(),baseRequest);
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
		BaseRequest baseRequest = (BaseRequest) object;
		return xmerInfoService.modifyXmerStatus(baseRequest);
	}

}
