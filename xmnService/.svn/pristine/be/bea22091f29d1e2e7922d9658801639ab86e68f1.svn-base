package com.xmniao.xmn.core.xmer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.jsoup.Connection.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.xmer.service.ActivityService;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：EnrollActivityApi   
* 类描述：   活动报名
* 创建人：xiaoxiong   
* 创建时间：2016年6月25日 下午3:21:36   
* @version    
*
 */
@Controller
public class EnrollActivityApi implements BaseVControlInf{
	
	/**
	 * 注入service
	 */
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private Validator validator;

	@RequestMapping(value="enrollActivity",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object enrollActivity(IDRequest idRequest){
		List<ConstraintViolation> result = validator.validate(idRequest);
		if(result.size() >0 && result != null){
			return new BaseResponse(ResponseCode.DATAERR,"亲，你提交的数据有问题!^_^");
		}
		return versionControl(idRequest.getApiversion(), idRequest);

		
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
		return activityService.enrollActivity(idRquest);
	}
}
