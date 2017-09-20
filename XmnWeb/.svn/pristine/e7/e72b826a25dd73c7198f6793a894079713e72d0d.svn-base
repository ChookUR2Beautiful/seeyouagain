package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.FoodDao;
import com.xmniao.xmn.core.businessman.entity.TFood;
import com.xmniao.xmn.core.businessman.entity.TFoodClass;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：FoodService
 * 
 * @类描述：
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年7月6日 下午4:26:09
 * 
 */
@Service
public class FoodService extends BaseService<TFood> {
	@Autowired
	private FoodDao foodDao;

	@Override
	protected BaseDao<TFood> getBaseDao() {
		return foodDao;
	}

	/**
	 * 添加菜品并更新菜品数量
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月13日 上午10:49:50
	 * @param food
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public synchronized void addOrUpdateFood(TFood food) {
		//一个商家只能设置一个菜品为广告位
		if(food.getAtag()==1){
			//更新其他的菜品为不是广告位
			this.log.info("将其他菜品设置为不是广告位");
			foodDao.updateAllFoodAtagIsNot(food);
		}
		if (food.getSdate() != null) {
			foodDao.add(food);
		} else {
			foodDao.update(food);
		}
	}

}
