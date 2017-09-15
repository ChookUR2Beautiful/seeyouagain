/**
\ * 2016年8月10日 下午3:35:25
 */
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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.service.PersonalCenterService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PersonalCenterApi
 * @类描述：个人中心接口
 * @创建人： yeyu
 * @创建时间 2016年8月10日 下午3:35:25
 * @version
 */
@Controller
public class PersonalCenterApi implements BaseVControlInf {
	//日志
	private final Logger log = Logger.getLogger(PersonalCenterApi.class);
	
	//注入services
	@Autowired
	private PersonalCenterService personalcenterService;
	
	//注入validator
	@Autowired
	private Validator validator;
	
	//注入sessionTokenService
	@Autowired
	private SessionTokenService sessionTokenService;

	/**
	 * 
	* @Title: getPersonInfo
	* @Description: 根据用户uid查询直播用户信息
	* @return Object
	 */
	@RequestMapping(value="/live/personal/personalCenter",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryPersonInfo(BaseRequest baseRequest){
		//日志
		log.info("pcRequest data:" + baseRequest.toString());
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(baseRequest.getApiversion(), baseRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	private Object versionControlOne(Object object) {
		BaseRequest  baseRequest = (BaseRequest) object;
		//获取uid
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		return personalcenterService.queryLivePersonByUid(uid,baseRequest);
	}
	
}
