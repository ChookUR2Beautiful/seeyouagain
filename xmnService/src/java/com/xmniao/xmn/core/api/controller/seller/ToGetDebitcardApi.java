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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.ToGetDebitcardRequest;
import com.xmniao.xmn.core.seller.service.ToGetDebitcardService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
/**
 * 判断用户是否在该商家充值，进入换卡/充值页面
 * @author Administrator
 *
 */
@Controller
public class ToGetDebitcardApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(ToGetDebitcardApi.class);
	
	@Resource
	private Validator validator;
	
	@Resource
	private ToGetDebitcardService toGetDebitcardService;
	
	@Resource
	private SessionTokenService sessionTokenService;
	
	
	@RequestMapping(value="/seller/toGetDebitcard",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object toGetDebitcard(ToGetDebitcardRequest toGetDebitcardRequest){
		//日志
		log.info("toGetDebitcardRequest data:"+toGetDebitcardRequest.toString());
		//验证请求参数
		List<ConstraintViolation> result = validator.validate(toGetDebitcardRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(toGetDebitcardRequest.getApiversion(),toGetDebitcardRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionOne(object);

		default:
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确");
		}
	}

	private Object versionOne(Object object) {
		ToGetDebitcardRequest toGetDebitcardRequest = (ToGetDebitcardRequest)object;
		
		try {
			return toGetDebitcardService.queryDebitcardPayOrder(toGetDebitcardRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询失败");
		}
	}

}
