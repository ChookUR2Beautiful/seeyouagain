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
import com.xmniao.xmn.core.seller.entity.CommentSellerRequest;
import com.xmniao.xmn.core.seller.service.ExperienceCommentService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 查询点评商家信息接口，(美食体验官新增点评、主播修改点评时查询点评店铺信息)
 * @ClassName:CommentSellerInfoApi
 * @Description:
 * @Author:xw
 * @Date:2017年5月24日下午5:51:48
 */
@Controller
public class CommentSellerInfoApi implements BaseVControlInf {
	
	private final Logger log = Logger.getLogger(CommentSellerInfoApi.class);
	
	@Resource
	private Validator validator;
	
	@Resource
	private ExperienceCommentService experienceCommentService;

	@RequestMapping(value="commentSellerInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object commentSellerInfo(CommentSellerRequest request){
		log.info("CommentSellerRequest data:" + request.toString());
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
		CommentSellerRequest request = (CommentSellerRequest) object;
		
		try {
			return experienceCommentService.commentSellerInfo(request);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"商家信息查询失败");
		}
	}

}
