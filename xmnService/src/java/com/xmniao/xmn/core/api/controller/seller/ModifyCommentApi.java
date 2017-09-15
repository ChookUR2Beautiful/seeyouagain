package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.seller.entity.ModifyCommentRequest;
import com.xmniao.xmn.core.seller.service.ExperienceCommentService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

@Controller
public class ModifyCommentApi implements BaseVControlInf {
	
	private final Logger log = Logger.getLogger(ModifyCommentApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ExperienceCommentService experienceCommentService;

	@RequestMapping(value="modifyComment",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object modifyComment(ModifyCommentRequest request){
		log.info("ModifyCommentRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if (result != null && result.size() > 0) {
			log.info("提交的数据有问题" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据不正确!");
		}
		return versionControl(request.getApiversion(), request);
	}
	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionOne(object);
		default:
			return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
		}
	}

	private Object versionOne(Object object) {
		ModifyCommentRequest request = (ModifyCommentRequest) object;
		
		try {
			return experienceCommentService.updateComment(request);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"修改点评失败");
		}
	}

}
