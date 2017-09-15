package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.MoreActivityRequest;
import com.xmniao.xmn.core.xmer.service.ActivityService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：MoreActivityApi   
* 类描述：查询所有活动   
* 创建人：xiaoxiong   
* 创建时间：2016年6月27日 下午6:09:16   
* @version    
*
 */
@Controller
public class MoreActivityApi implements BaseVControlInf{
	/**
	 * 注入service
	 */
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private Validator validator;
	
	
	@RequestMapping(value="moreActivity",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object moreActivity(MoreActivityRequest request){
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			return new BaseResponse(ResponseCode.DATAERR,"亲，你提交的数据有问题!^_^");
		}
		return versionControl(request.getApiversion(), request);
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
		MoreActivityRequest request = (MoreActivityRequest)object;
		return activityService.moreActivity(request);
	}

}
