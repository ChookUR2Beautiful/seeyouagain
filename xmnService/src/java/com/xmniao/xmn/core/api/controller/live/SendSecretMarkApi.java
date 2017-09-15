/**
 * 2016年8月18日 下午4:09:54
 */
package com.xmniao.xmn.core.api.controller.live;

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
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SendSecretMarkRequest;
import com.xmniao.xmn.core.live.service.PrivateMessageService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SendSecretMarkApi
 * @类描述：发送私信接口
 * @创建人： yeyu
 * @创建时间 2016年8月18日 下午4:09:54
 * @version
 */
@Controller
public class SendSecretMarkApi implements BaseVControlInf {
	//日志
	private Logger log=Logger.getLogger(SendSecretMarkApi.class);
	@Autowired
	private PrivateMessageService privatemessageService;
	
	//注入validator
	@Autowired
	private Validator validator;
	
	/**
	 * 
	* @Title: getPersonInfo
	* @Description: 根据用户uid查询直播用户信息
	* @return Object
	 */
	@RequestMapping(value="/live/message/sendSecretMark",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object sendSecretMark(SendSecretMarkRequest sendsecretmarkRequest){
		log.info("SendSecretMarkRequest Data:"+sendsecretmarkRequest.toString());

		List<ConstraintViolation> result = validator.validate(sendsecretmarkRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(sendsecretmarkRequest.getApiversion(), sendsecretmarkRequest);
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
		SendSecretMarkRequest sendsecretmarkRequest=(SendSecretMarkRequest) object;
		
//		return privatemessageService.queryPrivateMessageBysendId(sendsecretmarkRequest.getSessiontoken(), sendsecretmarkRequest.getAnchor_id(),sendsecretmarkRequest.getRecord_id(),sendsecretmarkRequest.getMessager_txt());
		return privatemessageService.sendSecretMark(sendsecretmarkRequest);
		
	}

}
