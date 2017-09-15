package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.TlsSigRequest;
import com.xmniao.xmn.core.live.service.LiveUserService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：GetTlsSigApi   
* 类描述：   
* 创建人：账号登录集成:独立模式,获取sig接口   
* 创建时间：2016年8月4日 上午10:05:01   
* @version    
*
 */
@Controller
@RequestMapping("/live/login")
public class GetTlsSigApi implements BaseVControlInf {
	
	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory.getLogger(GetTlsSigApi.class);
	
	/**
	 * 注入liveUserService
	 */
	@Autowired
	private LiveUserService liveUserService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/getTlsSig",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getTlsSig(TlsSigRequest tlsSigRequest){
		//验证参数
		log.info("tlsSigRequest data:"+tlsSigRequest.toString());
		List<ConstraintViolation> result = validator.validate(tlsSigRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(tlsSigRequest.getApiversion(), tlsSigRequest);
		
	}

	public Object versionOne(Object obj){
		TlsSigRequest tlsSigRequest = (TlsSigRequest) obj;
		return liveUserService.getTlsSig(tlsSigRequest);
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
