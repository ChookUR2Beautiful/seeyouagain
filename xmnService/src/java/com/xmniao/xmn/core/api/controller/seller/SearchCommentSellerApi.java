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
import com.xmniao.xmn.core.seller.entity.SearchCommentSellerRequest;
import com.xmniao.xmn.core.seller.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
 * @ClassName:SearchCommentSellerApi
 * @Description:通过关键字搜索商圈下的 店铺
 * @Author:xw
 * @Date:2017年5月17日下午9:00:05
 */
@Controller
public class SearchCommentSellerApi implements BaseVControlInf {

	private Logger log = Logger.getLogger(SearchCommentSellerApi.class);

	@Autowired
	private SellerService sellerService;

	@Autowired
	private Validator validator;

	@RequestMapping(value = "searchCommentSeller", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Object searchCommentSeller(SearchCommentSellerRequest request) {
		log.info("BaseRequest data:" + request.toString());
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
		SearchCommentSellerRequest request = (SearchCommentSellerRequest) object;
		
		try {
			return sellerService.searchCommentSellerByKeyword(request);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "搜索店铺失败");
		}
	}

}
