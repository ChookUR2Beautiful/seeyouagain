package com.xmniao.xmn.core.api.controller.live;

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
import com.xmniao.xmn.core.common.request.PhoneRequest;
import com.xmniao.xmn.core.live.service.LiveUserService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SecretMarkLiverInfoApi   
* 类描述：   在消息中心的，私信模块获取主播/观众信息
* 创建人：yezhiyong   
* 创建时间：2016年10月31日 下午3:12:32   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class SecretMarkLiverInfoApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(SecretMarkLiverInfoApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入liveUserService
	 */
	@Autowired 
	private LiveUserService liveUserService;

	/**
	 * 
	* @Title: queryLiverInfo
	* @Description: 在消息中心的，私信模块获取主播/观众信息
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/SecretMark/liverInfo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object querySecretMarkLiverInfo(PhoneRequest phoneRequest){
		//日志
		log.info("phoneRequest data : " + phoneRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(phoneRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		return versionControl(phoneRequest.getApiversion(),phoneRequest);
	}
	
	public Object versionOne(Object obj){
		PhoneRequest phoneRequest = (PhoneRequest)obj;
		return liveUserService.querySecretMarkLiverInfo(phoneRequest);
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
