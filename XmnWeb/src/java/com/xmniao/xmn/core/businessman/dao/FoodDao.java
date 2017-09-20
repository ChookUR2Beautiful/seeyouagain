package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TFood;
import com.xmniao.xmn.core.businessman.entity.TSellerPicApply;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：FootDao
 * 
 * @类描述：
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年7月6日 上午10:47:33
 * 
 */

public interface FoodDao extends BaseDao<TFood> {

	/**
	 * 更新商家的所有所有菜品是否为广告位字段为：否
	 * @author zhang'zhiwen
	 * @date 2015年7月24日 下午5:33:53
	 */
	void updateAllFoodAtagIsNot(TFood food);
	/**
	 * @Title:updateAllFoodAtagIsNot
	 * @Description:批量更新菜品图片
	 * @param food void
	 * @throw
	 */
	void batchUpdate(List<TSellerPicApply> foodList);
	
	/**
	 * @Title:batchAdd
	 * @Description:批量添加菜品图片
	 * @param sellerPicList Integer
	 * @throw
	 */
	Integer batchAdd(List<TFood> foodList);
}
