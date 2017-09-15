package com.xmniao.xmn.core.api.controller.xmer;

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
import com.xmniao.xmn.core.common.request.ShopPicRequest;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;


@Controller
public class AddShopPicApi implements BaseVControlInf{
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerInfoApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private XmerInfoService xmerInfoService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@ResponseBody
	@RequestMapping(value="addShopPic",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	public Object addShopPic(ShopPicRequest shopPicRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(shopPicRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(shopPicRequest.getApiversion(),shopPicRequest);
			
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionOne(object);
		case 2: return versionTwo(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
		
	}
	//添加商铺图片
	private Object versionOne(Object object) {
		ShopPicRequest shopPicRequest=(ShopPicRequest) object;
		return xmerInfoService.addShopPic(shopPicRequest);
	}

	private Object versionTwo(Object object) {
		ShopPicRequest shopPicRequest=(ShopPicRequest) object;
		return xmerInfoService.addShopPicV2(shopPicRequest);
	}
}
