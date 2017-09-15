package com.xmniao.xmn.core.api.controller.personal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.request.personal.PersonalInfoDetailRequest;
import com.xmniao.xmn.core.personal.service.PersonalInfoDetailService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalInfoDetailApi   
* 类描述：   用户/主播详情 活动行为
* 创建人：yezhiyong   
* 创建时间：2016年11月16日 下午3:42:29   
* @version    
*
 */
@RequestMapping("/personal")
@Controller
public class PersonalInfoDetailApi{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(PersonalInfoDetailApi.class);
	
	/**
	 * 注入personalInfoDetailService
	 */
	@Autowired 
	private PersonalInfoDetailService personalInfoDetailService;

	/**
	 * 
	* @Title: queryPersonalInfoDetail
	* @Description:	用户/主播详情 活动行为
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/personalInfoDetail" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryPersonalInfoDetail(PersonalInfoDetailRequest personalInfoDetailRequest){
		//日志
		log.info("personalInfoDetailRequest data:" + personalInfoDetailRequest.toString());
		//用户/主播详情 活动行为
		return personalInfoDetailService.queryPersonalInfoDetail(personalInfoDetailRequest);
	}
	
}
