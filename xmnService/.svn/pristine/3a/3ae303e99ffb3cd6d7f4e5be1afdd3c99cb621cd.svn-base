package com.xmniao.xmn.core.api.controller.live;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiverInfoByPhoneRequest;
import com.xmniao.xmn.core.common.request.live.LiverInfoRequest;
import com.xmniao.xmn.core.common.request.live.LiverInfoV2Request;
import com.xmniao.xmn.core.live.service.LiveUserService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 获取主播/用户信息
 */
@Controller
@RequestMapping("/live/room")
public class LiverInfoV2Api implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiverInfoV2Api.class);
	
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
	* @Description: 获取用户信息
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/userInfo" ,method=RequestMethod.POST, produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiverInfo(LiverInfoV2Request liverInfoV2Request){
		//日志
		log.info("liverInfoV2Request data : " + liverInfoV2Request.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liverInfoV2Request);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		//查看用户信息
		return versionControl(liverInfoV2Request.getApiversion(), liverInfoV2Request);
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
		LiverInfoV2Request liverInfoV2Request = (LiverInfoV2Request)object;
		return liveUserService.queryLiverInfo(liverInfoV2Request);
	}

}
