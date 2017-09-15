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
import com.xmniao.xmn.core.seller.entity.UnsignedSellerRequest;
import com.xmniao.xmn.core.seller.service.UnsignedSellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
 * @ClassName:AddUnsignedSellerApi
 * @Description:新增 点评非签约店铺 接口 
 * @Author:xw
 * @Date:2017年5月17日下午8:11:00
 */
@Controller
public class AddUnsignedSellerApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(AddUnsignedSellerApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private UnsignedSellerService unsignedSellerService;
	
	@RequestMapping(value="addUnsignedSeller",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object addUnsignedSeller(UnsignedSellerRequest request){
		log.info("UnsignedSellerRequest data:" + request.toString());
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
		UnsignedSellerRequest unsignedSellerRequest = (UnsignedSellerRequest) object;
		
		try {
			return unsignedSellerService.addUnsignedSeller(unsignedSellerRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"点评店铺添加失败");
		}
	}

	
}
