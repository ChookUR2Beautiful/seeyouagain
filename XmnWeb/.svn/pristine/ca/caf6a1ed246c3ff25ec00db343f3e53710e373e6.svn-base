package com.xmniao.xmn.core.businessman.dao;

import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.AllianceAccount;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllianceAccountDao
 * 
 * 类描述：商家联盟店帐号dao
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-01-16 11:06:45
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface AllianceAccountDao extends BaseDao<AllianceAccount>{
	/**
	 * 根据联盟商店id获取联盟店帐号
	 * @param map
	 * @return
	 */
	@DataSource("slave")
	AllianceAccount getObjectByallianceShopId(Map<String, String> map);
	
	Long checkAccount(String account);
		
}
