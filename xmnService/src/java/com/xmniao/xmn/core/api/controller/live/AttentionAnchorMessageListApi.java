package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.MessageManagerInfoRequest;
import com.xmniao.xmn.core.live.service.MessageManageService;

public class AttentionAnchorMessageListApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(AttentionAnchorMessageListApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired 
	private MessageManageService messageManageService;

	/**
	 * @Description 关注的主播列表
	 * @author yhl
	 * @param 
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/message/attentionAnchorList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object getOrderView(MessageManagerInfoRequest messageInfoRequest){
		
		List<ConstraintViolation> result = validator.validate(messageInfoRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}else {
			return versionControl(messageInfoRequest.getApiversion(),messageInfoRequest);
		}
	}
	
	public Object versionOne(Object obj){
		MessageManagerInfoRequest messageInfoRequest = (MessageManagerInfoRequest)obj;
		return messageManageService.getAttentionAnchorMsgList(messageInfoRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

}
