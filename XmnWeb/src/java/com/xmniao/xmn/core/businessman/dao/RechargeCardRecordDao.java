/**
 * 
 */
package com.xmniao.xmn.core.businessman.dao;

import java.util.Map;



import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.RechargeCardRecord;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RechargeCardRecordDao
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月22日 上午11:56:35
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

public interface RechargeCardRecordDao extends BaseDao<RechargeCardRecord> {
	
	
	/**
	 * 
	 * 方法描述：根据uid获取用户信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月22日下午2:29:14 <br/>
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	Map<String,String> getLiver(Integer uid);
	
	
	Map<String,Object> getLedgerRecord(@Param("orderNo")String orderNo,@Param("type")Integer type);
	
	@DataSource("burs")
	Map<String,Object> getUsr(Map<String,String> paraMap);
}
