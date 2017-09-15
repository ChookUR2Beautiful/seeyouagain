package com.xmniao.xmn.core.api.controller.common;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AppUpdateRequest;
import com.xmniao.xmn.core.common.service.AppUpdateService;

/**
 * 
* 项目名称：saasService   
* 类名称：AppUpdateApi   
* 类描述：APP版本更新接口   
* 创建人：liuzhihao   
* 创建时间：2016年4月18日 下午4:15:21   
* @version    
*
 */
@Controller
public class AppUpdateApi implements BaseVControlInf{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(AppUpdateApi.class);
	
	/**
	 * 注入service
	 */
	@Autowired
	private AppUpdateService appUpdateService;
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="/appUpdate")
	@ResponseBody
	public Object appUpdate(AppUpdateRequest appUpdateRequest){
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(appUpdateRequest);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(appUpdateRequest.getApiversion(), appUpdateRequest);
	}
	
	public Object versionAppUpdate(Object object){
		AppUpdateRequest appUpdateRequest = (AppUpdateRequest) object;
		return appUpdateService.queryAppUpdate(appUpdateRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionAppUpdate(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	

}
