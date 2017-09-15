package com.xmniao.xmn.core.api.controller.message;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.MessageActivityRequest;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.live.service.MessageService;
import com.xmniao.xmn.core.seller.entity.CommentRequest;
import com.xmniao.xmn.core.seller.service.ExperienceCommentService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

@Controller
public class MessageSystemApi implements BaseVControlInf {
	
	private final Logger log = Logger.getLogger(MessageSystemApi.class);
	
	@Resource
	private Validator validator;
	
	@Resource
	private MessageService messageService;

	@RequestMapping(value="/message/system",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object addComment(PageRequest request) {
		List<ConstraintViolation> result = validator.validate(request);
		if (result.size() > 0 && result != null) {
			log.info("提交的数据有问题:" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionControlOne(object);
		default:
			return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
		}
	}

	public Object versionControlOne(Object obj){
		PageRequest request = (PageRequest) obj;
		return messageService.getUserSystem(request);

	}
}
