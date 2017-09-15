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
import com.xmniao.xmn.core.common.request.personal.CheckWalletPwdRequest;
import com.xmniao.xmn.core.personal.service.PersonalInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CheckWalletPwdApi   
* 类描述：   验证提现密码
* 创建人：yezhiyong   
* 创建时间：2016年11月25日 下午4:19:37   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class CheckWalletPwdApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(CheckWalletPwdApi.class);
	
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
	* @Title: checkWalletPwd
	* @Description: 验证提现密码
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/checkWalletPwd" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryCheckWalletPwd(CheckWalletPwdRequest checkWalletPwdRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(checkWalletPwdRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(checkWalletPwdRequest.getApiversion(),checkWalletPwdRequest);
	}
	
	public Object versionOne(Object obj){
		CheckWalletPwdRequest checkWalletPwdRequest = (CheckWalletPwdRequest)obj;
		return personalInfoService.queryCheckWalletPwd(checkWalletPwdRequest);
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
