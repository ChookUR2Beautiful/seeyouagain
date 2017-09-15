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
import com.xmniao.xmn.core.common.request.personal.PersonalUnbundlingAccountRequest;
import com.xmniao.xmn.core.personal.service.PersonalInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：DeletePersonalMentionAccountListApi   
* 类描述：   解绑用户提现银行卡
* 创建人：yezhiyong   
* 创建时间：2016年11月26日 下午2:13:17   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class PersonalUnbundlingAccountApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(PersonalUnbundlingAccountApi.class);
	
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
	* @Title: deletePersonalMentionAccount
	* @Description: 解绑用户提现银行卡
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/personalUnbundlingAccount" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object personalUnbundlingAccount(PersonalUnbundlingAccountRequest personalUnbundlingAccountRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(personalUnbundlingAccountRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(personalUnbundlingAccountRequest.getApiversion(),personalUnbundlingAccountRequest);
	}
	
	public Object versionOne(Object obj){
		PersonalUnbundlingAccountRequest personalUnbundlingAccountRequest = (PersonalUnbundlingAccountRequest)obj;
		return personalInfoService.personalUnbundlingAccount(personalUnbundlingAccountRequest);
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
