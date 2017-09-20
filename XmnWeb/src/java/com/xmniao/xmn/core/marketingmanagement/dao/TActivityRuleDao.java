package com.xmniao.xmn.core.marketingmanagement.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;

public interface TActivityRuleDao extends BaseDao<TActivityRule> {

 /**
  * @author dong'jietao 
  * @date 2015年5月22日 下午2:05:45
  * @TODO 获取活动规则
  * @param tActivityRule
  * @return
  */
 public  List<TActivityRule> getTActivityRules(TActivityRule tActivityRule);
 
 public  TActivityRule getTActivityRule(TActivityRule tActivityRule);
}
