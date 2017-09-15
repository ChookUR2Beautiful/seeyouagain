package com.xmniao.xmn.core.xmer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.xmer.service.ActivityService;
import com.xmniao.xmn.core.xmer.service.GetXmerMarkService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ActivityInfoApi   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年6月25日 上午11:25:16   
* @version    
*
 */
@Controller
public class ActivityInfoApi implements BaseVControlInf{
	
	/**
	 * 注入service
	 */
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="activityInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object activityInfo(IDRequest idRquest){
		
		return versionControl(idRquest.getApiversion(), idRquest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1 :
			return versionOne(object);
		default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确，请重新下载客户端");
	}
	}

	private Object versionOne(Object object) {
		IDRequest idRquest = (IDRequest)object;
		return activityService.activityInfo(idRquest);
	}

}
