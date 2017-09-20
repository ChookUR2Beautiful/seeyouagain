package com.xmniao.xmn.core.marketingmanagement.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;
import com.xmniao.xmn.core.marketingmanagement.entity.TIntegralRule;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
* 
* @项目名称：XmnWeb
* 
* @类名称：TActivityDao
* 
* @类描述： 营销活动管理
* 
* @创建人：yang'xu
* 
* @创建时间：2015年01月14日10时12分24秒
* 
* @Copyright©广东寻蜜鸟网络技术有限公司
*/
public interface TActivityManagermentDao extends BaseDao<TActivity> {
	@DataSource("slave")
	public Long getType(TActivity tActivity);
	
	@DataSource("slave")
	public String getAid(TActivity tActivity);
	
	@DataSource("slave")
	public TActivity getObjectType(Long type);
	
	/**
	 * 商家上线时 查询指定活动 关联商家 
	 * @return
	 */
	@DataSource("slave")
	Integer getSpecifiedActivity();
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void addTActivityRule(TActivityRule temp);
	//添加积分活动规则
	@Transactional(propagation = Propagation.REQUIRED)
	public void addJiFenTActivityRule(TIntegralRule temp);

	@DataSource("slave")
	public List<TActivityRule> getActivityRuleList(@Param("aid")Long aid);
	//查询积分活动规则
	@DataSource("slave")
	public List<TIntegralRule> getIntegralRuleList(@Param("aid")Long aid);
	//查询积分活动规则中未添加的商家类别，用于显示在添加页面商家类别选项
	@DataSource("slave")
	public List<TIntegralRule> getIntegralRulsPrade();
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateDiscountActivity(TActivityRule temp);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateActivityRule(TActivityRule temp);
	//修改积分活动规则
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateIntegraRule(TIntegralRule temp);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteActivityRule(String[] rids);
	//删除积分活动规则
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteIntegralRule(String[] rids);
	
	public  TActivityRule getTActivityRule(TActivityRule tActivityRule);
	
}
