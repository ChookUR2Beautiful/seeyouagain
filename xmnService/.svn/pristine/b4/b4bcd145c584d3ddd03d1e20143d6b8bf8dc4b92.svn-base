package com.xmniao.xmn.core.xmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：xmnService   
* 类名称：WithDrawCashDao   
* 类描述：提现接口   
* 创建人：liuzhihao   
* 创建时间：2016年5月23日 上午9:26:32   
* @version    
*
 */
@Repository
public interface WithDrawCashDao {

	//查询寻蜜客钱包表
	@DataSource("xmnpay")
	public Map<Object,Object> queryXmerWalletId(Integer uid);
	
	@DataSource("xmnpay")
	public void insertXmerWalletRecord(Map<String, Object> xmerWalleRecordtMap);
}
