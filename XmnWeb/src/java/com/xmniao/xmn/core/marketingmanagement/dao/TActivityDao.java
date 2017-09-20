package com.xmniao.xmn.core.marketingmanagement.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSellerAgio;
import com.xmniao.xmn.core.marketingmanagement.entity.HotWords;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.entity.TGiveMoneyInfo;
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
public interface TActivityDao extends BaseDao<TActivity> {
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
	
	
	
	/**
	 * 查询折扣补贴列表
	 * @param activity
	 * @return
	 */
	@DataSource("slave")
	List<TActivity> getDiscountList(TActivity activity);
	
	
	/**
	 * 查询折扣补贴列表总数
	 * @param activity
	 * @return
	 */
	@DataSource("slave")
	Long getDiscountListCount(TActivity activity);
	
	/**
	 * 查询折扣补贴给出金额明细
	 * @param giveMoneyInfo
	 * @return
	 * 
	 */
	@DataSource("slave")
	List<TGiveMoneyInfo> getDiscountGiveMoneyList(TGiveMoneyInfo giveMoneyInfo);
	
	/**
	 * 查询折扣补贴给出金额明细总数
	 * @param giveMoneyInfo
	 * @return
	 *
	 *update by wangzhimin 2015/08/28 12:16:30
	 */
	@DataSource("slave")
	//Long getDiscountGiveMoneyListCount(TGiveMoneyInfo giveMoneyInfo);
	Long getDiscountGiveMoneyListCountNumber(TGiveMoneyInfo giveMoneyInfo);
	
	/**
	 * 根据编号查活动询信息
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	TActivity getDiscountInfo(String id);
	
	Integer updateDataByAid(TActivity tActivity);

	
	
	/**
	 * 查询佣金补贴列表
	 * @param activity
	 * @return
	 */
	@DataSource("slave")
	public List<TActivity> getCommissionList(TActivity activity);

	/**
	 * 查询佣金补贴列表总数
	 * @param activity
	 * @return
	 */
	@DataSource("slave")
	public Long getCommissionCount(TActivity activity);

	/**
	 * 根据编号查询佣金补贴活动详细信息
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	public TActivity getCommissionInfo(String aid);

	/**
	 * 查询佣金补贴 已补贴金额 明细
	 * @param giveMoneyInfo
	 * @return
	 * 
	 */
	@DataSource("slave")
	public List<TGiveMoneyInfo> getCommissionGiveMoneyList(TGiveMoneyInfo giveMoneyInfo);

	/**
	 * 查询佣金补贴 已补贴金额 明细记录数
	 * @param giveMoneyInfo
	 * @return
	 * 
	 */
	@DataSource("slave")
	public Long getCommissionGiveMoneyListCount(TGiveMoneyInfo giveMoneyInfo);
}
