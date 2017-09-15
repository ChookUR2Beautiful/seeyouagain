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

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.FeedBackRequest;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;

/***
 * 
* 项目名称：xmnService   
* 类名称：SendMassageApi   
* 类描述：信息发送接口   
* 创建人：liuzhihao   
* 创建时间：2016年7月12日 上午10:55:24   
* @version    
*
 */
@Controller
public class FeedBackApi implements BaseVControlInf{
	
	/**
	 * 日志报错
	 */
	private final Logger log = Logger.getLogger(FeedBackApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入寻蜜客service
	 */
	@Autowired
	private XmerInfoService xmerInfoService;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	@RequestMapping(value="feedBackInfo",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object feedBack(FeedBackRequest feedBackRequest){
		List<ConstraintViolation> result = validator.validate(feedBackRequest);
		if(result.size() > 0 && result != null){
			log.info(result);
			log.info("你提交的数据有问题");
			return new BaseResponse(ResponseCode.DATAERR,"你提交的数据有问题，请联系管理员!^_^");
		}
		return versionControl(feedBackRequest.getApiversion(), feedBackRequest);
	}
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本匹配，请下载最新版本");
		}
	}
	
	private Object versionControlOne(Object object) {
		FeedBackRequest feedBackRequest = (FeedBackRequest) object;
		return xmerInfoService.addFeedBack(feedBackRequest);
	}

}
