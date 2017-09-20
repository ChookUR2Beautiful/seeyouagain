package com.xmniao.xmn.core.fresh.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.TFreshActivityAuction;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFreshActivityAuctionDao
 * 
 * 类描述： 小鸟超市竞拍活动Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-1 下午4:57:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TFreshActivityAuctionDao extends BaseDao<TFreshActivityAuction>{
	
	/**
	 * 
	 * 方法描述：根据主键ID删除竞拍活动 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-1下午6:07:17 <br/>
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	
	/**
	 * 
	 * 方法描述：获取竞拍活动当前最高金额和参与人数等信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-2下午8:54:52 <br/>
	 * @param parameter
	 * @return
	 */
	List<TFreshActivityAuction> getAuctionRecordList(Map<String,Object> parameter); 
	
}