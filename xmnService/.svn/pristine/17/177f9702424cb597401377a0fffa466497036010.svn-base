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
import com.xmniao.xmn.core.seller.entity.SellerCommentRequest;
import com.xmniao.xmn.core.seller.service.ExperienceCommentService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
 * @ClassName:SellerCommentListApi
 * @Description:商家 网红店铺列表
 * @Author:xw
 * @Date:2017年5月19日上午10:13:34
 */
@Controller
public class SellerCommentListApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(SellerCommentListApi.class);

	@Autowired
	private Validator validator;

	@Autowired
	private ExperienceCommentService experienceCommentService;

	@RequestMapping(value = "sellerCommentList", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Object sellerCommentList(SellerCommentRequest request) {
		log.info("SellerCommentRequest data:" + request.toString());
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

	private Object versionControlOne(Object object) {
		SellerCommentRequest request = (SellerCommentRequest) object;
		try {
			return experienceCommentService.sellerCommentList(request);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "网红点评查询失败");
		}
	}

}
