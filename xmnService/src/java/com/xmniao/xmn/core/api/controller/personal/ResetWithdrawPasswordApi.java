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
import com.xmniao.xmn.core.common.request.personal.ResetWithdrawPasswordRequest;
import com.xmniao.xmn.core.personal.service.PersonalInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ResetWithdrawPasswordApi   
* 类描述：   重置提现密码
* 创建人：yezhiyong   
* 创建时间：2016年11月15日 下午3:29:08   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class ResetWithdrawPasswordApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ResetWithdrawPasswordApi.class);
	
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
	* @Title: addReceivingAddress
	* @Description: 重置提现密码
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/resetWithdrawPassword" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object resetWithdrawPassword(ResetWithdrawPasswordRequest resetWithdrawPasswordRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(resetWithdrawPasswordRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(resetWithdrawPasswordRequest.getApiversion(),resetWithdrawPasswordRequest);
	}
	
	public Object versionOne(Object obj){
		ResetWithdrawPasswordRequest resetWithdrawPasswordRequest = (ResetWithdrawPasswordRequest)obj;
		return personalInfoService.resetWithdrawPassword(resetWithdrawPasswordRequest);
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
