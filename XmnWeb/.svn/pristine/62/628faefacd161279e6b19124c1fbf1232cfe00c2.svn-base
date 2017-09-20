package com.xmniao.xmn.core.marketingmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;
import com.xmniao.xmn.core.marketingmanagement.service.TActivityRuleService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * @author dong'jietao 
 * @date 2015年5月22日 下午5:08:05
 * @TODO 活动规则
 */
@RequestLogging(name="营销管理")
@Controller
@RequestMapping(value = "marketingManagement/activityrulemanagement")
public class TActivityRuleController extends BaseEntity {
	@Autowired
	public TActivityRuleService  tActivityRuleService;
	
	/**
	 * @author dong'jietao 
	 * @date 2015年5月22日 下午2:18:39
	 * @TODO 获取活动规则
	 * @param tActivityRule
	 * @return
	 */
	@RequestMapping(value = "/getTActivityRuleByAid")
	@ResponseBody
	public Object getTActivityRuleByAid(TActivityRule tActivityRule){
		 Resultable resultable =new Resultable();
		 tActivityRuleService.getTActivityRules(tActivityRule,resultable);
		 return resultable;
	}
	
}
