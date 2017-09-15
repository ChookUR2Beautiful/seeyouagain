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
import com.xmniao.xmn.core.catehome.service.XmnHomeService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.catehome.SwitchPositionRequest;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: GetCityApi    
* @Description:获取城市列表   
* @author: liuzhihao   
* @date: 2016年11月23日 上午11:03:03
 */
@Controller
public class SwitchPositionApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SwitchPositionApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private XmnHomeService xmnHomeService;
	
	/**
	 * 获取城市接口
	 * @param baseRequest
	 * @return
	 */
	@RequestMapping(value="/switchPosition",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object switchPosition(SwitchPositionRequest switchPositionRequest){
		log.info("SwitchPositionRequest data:"+switchPositionRequest.toString());
		List<ConstraintViolation> result = validator.validate(switchPositionRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(switchPositionRequest.getApiversion(), switchPositionRequest);
	}

	
	public Object versionOne(Object object){
		SwitchPositionRequest request = (SwitchPositionRequest) object;
		return xmnHomeService.switchPosition(request);
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
