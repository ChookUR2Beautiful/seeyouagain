package com.xmniao.xmn.core.api.controller.personal;

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
import com.xmniao.xmn.core.common.request.personal.AddReceivingAddressRequest;
import com.xmniao.xmn.core.personal.service.ReceivingAddressService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：modifyReceivingAddressApi   
* 类描述：   编辑收货地址
* 创建人：yezhiyong   
* 创建时间：2016年11月14日 下午5:50:37   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class ModifyReceivingAddressApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ModifyReceivingAddressApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入receivingAddressService
	 */
	@Autowired 
	private ReceivingAddressService receivingAddressService;

	/**
	 * 
	* @Title: queryReceivingAddressList
	* @Description: 编辑收货地址
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/modifyReceivingAddress" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object modifyReceivingAddress(AddReceivingAddressRequest addReceivingAddressRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(addReceivingAddressRequest);
		if ((result!=null && result.size()>0)||addReceivingAddressRequest.getReceivingAddressId() == null) {
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(addReceivingAddressRequest.getApiversion(),addReceivingAddressRequest);
	}
	
	public Object versionOne(Object obj){
		AddReceivingAddressRequest addReceivingAddressRequest = (AddReceivingAddressRequest)obj;
		return receivingAddressService.modifyReceivingAddress(addReceivingAddressRequest);
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

}
