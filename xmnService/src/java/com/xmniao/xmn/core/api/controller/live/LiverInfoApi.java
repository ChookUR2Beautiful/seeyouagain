package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiverInfoByPhoneRequest;
import com.xmniao.xmn.core.common.request.live.LiverInfoRequest;
import com.xmniao.xmn.core.live.service.LiveUserService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：UserInfoApi   
* 类描述：   获取主播/观众信息
* 创建人：yezhiyong   
* 创建时间：2016年8月24日 上午9:27:24   
* @version    
*
 */
@Controller
@RequestMapping("/live/room")
public class LiverInfoApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiverInfoApi.class);
	
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
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 
	* @Title: queryLiverInfo
	* @Description: 获取主播/观众信息
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/liverInfo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiverInfo(LiverInfoRequest liverInfoRequest){
		//日志
		log.info("liverInfoRequest data : " + liverInfoRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liverInfoRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}  
		
		//查看用户信息
		return versionControl(liverInfoRequest.getApiversion(), liverInfoRequest);
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
	
	private Object versionOne(Object object) {
		LiverInfoRequest liverInfoRequest = (LiverInfoRequest)object;
		return liveUserService.queryLiverInfo(liverInfoRequest);
	}

	/**
	* @Description: 获取主播/观众信息
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/liverInfoByPhone" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiverInfoByPhone(LiverInfoByPhoneRequest liverInfoByPhoneRequest){
		//日志
		log.info("liverInfoRequest data : " + liverInfoByPhoneRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liverInfoByPhoneRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return liveUserService.queryLiverInfoByPhone(liverInfoByPhoneRequest);
	}
	
	/**
	* @Description: 获取主播/观众信息
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/liverDetailByPhone" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object LiverInfoByPhone(LiverInfoByPhoneRequest liverInfoByPhoneRequest){
		//日志
		log.info("liverInfoRequest data : " + liverInfoByPhoneRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liverInfoByPhoneRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		String uid = sessionTokenService.getStringForValue(liverInfoByPhoneRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		return liveUserService.queryliveInfoDetail(liverInfoByPhoneRequest);
	}

}
