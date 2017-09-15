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
import com.xmniao.xmn.core.common.request.personal.DeleteReceivingAddressRequest;
import com.xmniao.xmn.core.personal.service.ReceivingAddressService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：DeleteReceivingAddressApi   
* 类描述：   删除收货地址
* 创建人：yezhiyong   
* 创建时间：2016年11月15日 上午9:54:07   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class DeleteReceivingAddressApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(DeleteReceivingAddressApi.class);
	
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
	* @Description: 删除收货地址
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/deleteReceivingAddress" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object deleteReceivingAddress(DeleteReceivingAddressRequest deleteReceivingAddressRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(deleteReceivingAddressRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(deleteReceivingAddressRequest.getApiversion(),deleteReceivingAddressRequest);
	}
	
	public Object versionOne(Object obj){
		DeleteReceivingAddressRequest deleteReceivingAddressRequest = (DeleteReceivingAddressRequest)obj;
		return receivingAddressService.deleteReceivingAddress(deleteReceivingAddressRequest);
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
