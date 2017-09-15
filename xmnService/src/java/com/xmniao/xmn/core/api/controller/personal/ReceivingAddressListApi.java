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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.urs.UserAddressRequest;
import com.xmniao.xmn.core.personal.service.ReceivingAddressService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ReceivingAddressListApi   
* 类描述：   收货地址列表
* 创建人：yezhiyong   
* 创建时间：2016年11月11日 下午5:16:17   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class ReceivingAddressListApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ReceivingAddressListApi.class);
	
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
	* @Description: 获取收货地址列表
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/receivingAddressList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryReceivingAddressList(UserAddressRequest userAddressRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(userAddressRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(userAddressRequest.getApiversion(),userAddressRequest);
	}
	
	public Object versionOne(Object obj){
		UserAddressRequest userAddressRequest = (UserAddressRequest)obj;
		return receivingAddressService.queryReceivingAddressList(userAddressRequest);
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
