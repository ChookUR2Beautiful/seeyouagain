package com.xmniao.xmn.core.api.controller.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PushRequest;
import com.xmniao.xmn.core.live.service.PushSingleService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * @author wdh
 *
 */
@Controller
public class PushXinGeMessageApi {

	//接口日志
	private final Logger log = Logger.getLogger(PushXinGeMessageApi.class);
	
	@Autowired
	private Validator validator;
	@Autowired
	private PushSingleService pushSingleService;
	
    	@RequestMapping(value="/pushXingeMessage",method={RequestMethod.POST, RequestMethod.POST},produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object pushApi(PushRequest request){
	    	List<ConstraintViolation> result = validator.validate(request);
			if(result.size() >0 && result != null){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new MapResponse(ResponseCode.DATAERR,result.get(0).getMessage());
			}
			return pushSingleService.pushMessage(request);
			
		}

	
}
