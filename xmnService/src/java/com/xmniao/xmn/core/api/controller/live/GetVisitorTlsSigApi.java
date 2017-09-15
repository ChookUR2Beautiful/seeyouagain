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
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.GetVisitorTlsSigRequest;
import com.xmniao.xmn.core.live.service.LiveUserService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：GetVisitorTlsSigApi   
* 类描述：   游客模式下,获取腾讯sig签名
* 创建人：yezhiyong   
* 创建时间：2017年1月9日 下午4:52:23   
* @version    
*
 */
@Controller
@RequestMapping("/live/login")
public class GetVisitorTlsSigApi {
	
	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory.getLogger(GetVisitorTlsSigApi.class);
	
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
	
	@RequestMapping(value="/getVisitorTlsSig",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getVisitorTlsSig(GetVisitorTlsSigRequest getVisitorTlsSigRequest){
		//日志
		log.info("tlsSigRequest data:" + getVisitorTlsSigRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(getVisitorTlsSigRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//获取游客模式的腾讯sig签名
		return liveUserService.getVisitorTlsSig(getVisitorTlsSigRequest);
		
	}

}
