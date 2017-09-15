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
import com.xmniao.xmn.core.common.service.HelpService;


/**
 * 
*  查询版主列表  
* 项目名称：xmnService   
* 类名称：HelpListApi   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年8月9日 下午5:44:59   
* @version    
*
 */
@Controller
public class HelpListApi implements BaseVControlInf{

	/**
	 * 日志报错
	 */
	private final Logger log = Logger.getLogger(HelpListApi.class);
	
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
	
	
	@RequestMapping(value="helplist",method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object helpList(BaseRequest baseRequest){
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result.size() > 0 && result != null){
			log.info(result);
			log.info("你提交的数据有问题");
			return new BaseResponse(ResponseCode.DATAERR,"你提交的数据有问题，请联系管理员!^_^");
		}
		return versionControl(baseRequest.getApiversion(), baseRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本匹配，请下载最新版本");
		}
	}


		private Object versionControlOne(Object object) {
			BaseRequest baseRequest=(BaseRequest) object;
			return heopService.getHelpList(baseRequest);
		}

}
