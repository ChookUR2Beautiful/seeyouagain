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
import com.xmniao.xmn.core.common.request.SellerInfoRequest;
import com.xmniao.xmn.core.xmer.service.SellerInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SellerInfoApi   
* 类描述：   查询商户信息
* 创建人：xiaoxiong   
* 创建时间：2016年5月23日 上午9:41:05   
* @version    
*
 */
@Controller
public class SellerInfoApi implements BaseVControlInf{
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerInfoApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private SellerInfoService sellerInfoService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;

	
	@RequestMapping(value="sellerInfo",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object sellerInfo(SellerInfoRequest sellerInfoRequest){
		
		List<ConstraintViolation> result = validator.validate(sellerInfoRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
			return versionControl(sellerInfoRequest.getApiversion(),sellerInfoRequest);
		
	}


	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	/**
	 * 
	* @Title: versionOne
	* @Description:  查询商户信息
	* @return Object    返回商户信息 和 成功失败信息
	* @throws
	 */
	private Object versionOne(Object object) {
	
	SellerInfoRequest sellerInfo=(SellerInfoRequest) object;	
		return sellerInfoService.querSellerInfo(sellerInfo);
	}


}
