/**
 * 
 */
package com.xmniao.xmn.core.businessman.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.ValueCard;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ValueCardDao
 * 
 * 类描述： 储值卡商家
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月17日 上午10:53:39 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface ValueCardDao extends BaseDao<ValueCard>{
	
	/**
	 * 
	 * 方法描述：获取区域代理下所有商家
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月17日下午2:17:59 <br/>
	 * @param id
	 * @return
	 */
	List<String> getAliance(Integer id);
	
	/**
	 * 
	 * 方法描述：获取商家列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月17日下午3:57:11 <br/>
	 * @return
	 */
	List<ValueCard> getSellerList(ValueCard vCard);
	
	/**
	 * 
	 * 方法描述：获取区域代理列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月18日上午11:08:47 <br/>
	 * @param vCard
	 * @return
	 */
	List<ValueCard> getAreaAgency(ValueCard vCard);
	
	/**
	 * 
	 * 方法描述：获取商家储值卡
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月18日下午2:13:44 <br/>
	 * @param vCard
	 * @return
	 */
	ValueCard getValueCard(ValueCard vCard);
	
	
	/**
	 * 
	 * 方法描述：获取连锁店下所有子商家
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月20日下午3:30:38 <br/>
	 * @param vcCard
	 * @return
	 */
	List<TSeller> getChildSeller(Object sellerid);
	
	/**
	 * 
	 * 方法描述：获取子商家数量
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月20日下午5:06:21 <br/>
	 * @param vCard
	 * @return
	 */
	Long countChildSeller(ValueCard vCard);
	
	/**
	 * 
	 * 方法描述：限制商户使用
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月21日上午9:58:22 <br/>
	 * @param vCard
	 * @return
	 */
	Integer limitSeller(ValueCard vCard);
	
	/**
	 * 根据id查询连锁商家
	 * @param id
	 * @return
	 */
	Map<String,Object> getAllianceById(Integer id);
	
	/**
	 * 
	 * 方法描述获取充值金额列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月3日上午10:02:07 <br/>
	 * @return
	 */
	List<Map<String,Object>> getRechargeValueList();
	
	/**
	 * 
	 * 方法描述：根据套餐id 查询套餐
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月6日下午5:05:19 <br/>
	 * @param id
	 * @return
	 */
	Map<String,Object> getRechargeValue(String id);
	
	/**
	 * 
	 * 方法描述：根据商家id获取商家信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月11日上午11:17:20 <br/>
	 * @param sellerid
	 * @return
	 */
	TSeller getSellers(String sellerid);
}
