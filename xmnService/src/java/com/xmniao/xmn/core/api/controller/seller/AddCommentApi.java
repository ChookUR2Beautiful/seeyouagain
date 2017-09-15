package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.seller.entity.CommentRequest;
import com.xmniao.xmn.core.seller.service.ExperienceCommentService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

@Controller
public class AddCommentApi implements BaseVControlInf {
	
	private final Logger log = Logger.getLogger(AddCommentApi.class);
	
	@Resource
	private Validator validator;
	
	@Resource
	private ExperienceCommentService experienceCommentService;

	@RequestMapping(value="addComment",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object addComment(CommentRequest request) {
		log.info("CommentRequest data:" + request.toString());
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
		CommentRequest commentRequest = (CommentRequest) obj;
		
		try {
			return experienceCommentService.addComment(commentRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"提交点评失败");
		}
	}
}
