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
import com.xmniao.xmn.core.common.request.TypeRequest;
import com.xmniao.xmn.core.personal.service.PersonalInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalBankListAndIdentityApi   
* 类描述：   获取银行列表以及证件类型列表
* 创建人：yezhiyong   
* 创建时间：2016年11月26日 下午4:19:00   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class PersonalBankAndIdTypeListListApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(PersonalBankAndIdTypeListListApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入personalInfoService
	 */
	@Autowired 
	private PersonalInfoService personalInfoService;

	/**
	 * 
	* @Title: addPersonalMentionAccount
	* @Description: 获取银行列表以及证件类型列表
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/personalBankAndIdTypeList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryPersonalBankAndIdTypeList(TypeRequest typeRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(typeRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(typeRequest.getApiversion(),typeRequest);
	}
	
	public Object versionOne(Object obj){
		TypeRequest typeRequest = (TypeRequest)obj;
		return personalInfoService.queryPersonalBankAndIdTypeList(typeRequest);
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
