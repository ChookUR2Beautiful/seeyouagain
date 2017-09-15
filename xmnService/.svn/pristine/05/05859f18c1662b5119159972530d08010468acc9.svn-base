package com.xmniao.xmn.core.api.controller.personal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.request.personal.PersonalInfoRequset;
import com.xmniao.xmn.core.personal.service.PersonalInfoDetailService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalInfoApi   
* 类描述：   用户/主播详情
* 创建人：yezhiyong   
* 创建时间：2016年11月16日 上午11:43:34   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class PersonalInfoApi{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ReceivingAddressListApi.class);

	/**
	 * 注入personalInfoDetailService
	 */
	@Autowired 
	private PersonalInfoDetailService personalInfoDetailService;

	/**
	 * 
	* @Title: queryPersonalInfo
	* @Description:	获取用户/主播详情
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/personalInfo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryPersonalInfo(PersonalInfoRequset personalInfoRequset){
		//日志
		log.info("personalInfoRequset data:" + personalInfoRequset.toString());
		//获取用户/主播详情
		return personalInfoDetailService.queryPersonalInfo(personalInfoRequset);
	}
	
}
