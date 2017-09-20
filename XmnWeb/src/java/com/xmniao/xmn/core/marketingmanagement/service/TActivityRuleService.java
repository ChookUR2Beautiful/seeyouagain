package com.xmniao.xmn.core.marketingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.marketingmanagement.dao.TActivityRuleDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;
@Service
public class TActivityRuleService extends BaseService<TActivityRule> {

	
	@Autowired
	public TActivityRuleDao activityRuleDao;
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return activityRuleDao;
	}
	
	public Resultable getTActivityRules(TActivityRule tActivityRule,Resultable resultable){
		
		List<TActivityRule> TActivityRules=activityRuleDao.getTActivityRules(tActivityRule);
		
		return resultable;
	}
	
	public List<TActivityRule> getTActivityRules(TActivityRule tActivityRule){
		List<TActivityRule> TActivityRules=activityRuleDao.getTActivityRules(tActivityRule);
		return TActivityRules;
	}
	public TActivityRule getTActivityRule(TActivityRule tActivityRule){
		
		TActivityRule TActivityRule=activityRuleDao.getTActivityRule(tActivityRule);
		
		return TActivityRule;
	}

}
