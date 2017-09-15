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
import com.xmniao.xmn.core.common.request.seller.CurrentSellerDebitcardRequest;
import com.xmniao.xmn.core.seller.service.CurrentSellerDebitcardService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 查询当前商家的鸟粉专享卡，并判断是否显示领卡图片
 * @author xw
 *
 */
@Controller
public class CurrentSellerDebitcardApi implements BaseVControlInf{
	
	@Resource
	private Validator validator;
	
	@Resource
	private CurrentSellerDebitcardService currentSellerDebitcardService;
	
	private final Logger log = Logger.getLogger(CurrentSellerDebitcardApi.class); 
	
	@RequestMapping(value="/seller/currenSellerDebitcard",method=RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Object currentSellerDebitcard(CurrentSellerDebitcardRequest currentSellerDebitcardRequest){
		//日志
		log.info("currentSellerDebitcardRequest Data:"+currentSellerDebitcardRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(currentSellerDebitcardRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(currentSellerDebitcardRequest.getApiversion(), currentSellerDebitcardRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionOne(object);

		default:
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	public Object versionOne(Object object){
		
		CurrentSellerDebitcardRequest currentSellerDebitcardRequest = (CurrentSellerDebitcardRequest)object;
		
		try {
			return currentSellerDebitcardService.queryCurrentSellerDebitcard(currentSellerDebitcardRequest);
		} catch (Exception e) {
			return new BaseResponse(ResponseCode.FAILURE,"商家专享卡信息查询失败");
		}
		
	}
}
