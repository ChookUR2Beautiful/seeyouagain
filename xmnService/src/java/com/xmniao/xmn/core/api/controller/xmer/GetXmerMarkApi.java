package com.xmniao.xmn.core.api.controller.xmer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.service.GetXmerMarkService;

/**   
 * 项目名称：xmnService   
 * 类名称：GetXmerMarkApi   
 * 类描述：寻蜜客标示查询(在进入寻蜜客主页前，调用此接口查询是否有注册寻蜜客资料)   
 * 创建人：zhengyaowen
 * 创建时间：2016年5月28日 下午2:36:00   
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version     
 */
@Controller
public class GetXmerMarkApi implements BaseVControlInf {

	/**
	 * 注入service
	 */
	@Autowired
	private GetXmerMarkService getXmerMarkService;
	
	@RequestMapping(value="getXmerMark", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getXmerMark(BaseRequest baseRequest){
		
		return versionControl(baseRequest.getApiversion(), baseRequest);
	}
	
	/**
	 * 控制版本
	 * */
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1 :
				return versionGetXmerMark(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确，请重新下载客户端");
		}
	}
	
	public Object versionGetXmerMark(Object obj){
		BaseRequest baseRequest = (BaseRequest)obj;
		return getXmerMarkService.getXmerMark(baseRequest);
		
	}

}
