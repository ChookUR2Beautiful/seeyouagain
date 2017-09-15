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
import com.xmniao.xmn.core.seller.entity.SellerPhotoRequest;
import com.xmniao.xmn.core.seller.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

@Controller
public class SellerPhotosApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(SellerPhotosApi.class);

	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;

	@RequestMapping(value="sellerPhotos",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object querySellerPhotos(SellerPhotoRequest request) {
		log.info("SellerPhotoRequest data:" + request.toString());
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
		SellerPhotoRequest request = (SellerPhotoRequest) obj;
		
		try {
			return sellerService.querySellerPhotos(request);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询商家相册失败");
		}
	}
}
