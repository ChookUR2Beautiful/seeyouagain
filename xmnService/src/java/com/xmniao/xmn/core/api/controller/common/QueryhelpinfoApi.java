package com.xmniao.xmn.core.api.controller.common;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
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
import com.xmniao.xmn.core.common.service.HelpService;

/**
 * 
*  获取帮助信息  
* 项目名称：xmnService   
* 类名称：QueryhelpinfoApi   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年8月10日 上午11:57:27   
* @version    
*
 */
@Controller
public class QueryhelpinfoApi implements BaseVControlInf{
	/**
	 * 日志报错
	 */
	private final Logger log = Logger.getLogger(QueryhelpinfoApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 帮助或反馈接口
	 */
	@Autowired
	private HelpService heopService;
	
	
	
	@RequestMapping(value="queryhelpinfo",method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object getHelpInfo(IDRequest idRequest){
		List<ConstraintViolation> result = validator.validate(idRequest);
		if(result.size() > 0 && result != null){
			log.info(result);
			log.info("你提交的数据有问题");
			return new BaseResponse(ResponseCode.DATAERR,"你提交的数据有问题，请联系管理员!^_^");
		}
		return versionControl(idRequest.getApiversion(), idRequest);
	}



	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本匹配，请下载最新版本");
		}
	}



	private Object versionControlOne(Object object) {
		IDRequest idQeuest=(IDRequest) object;
		return heopService.getHelpInfo(idQeuest);
	}

}
