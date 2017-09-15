package com.xmniao.xmn.core.xmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：xmnService   
* 类名称：RegisterDao   
* 类描述：注册接口   
* 创建人：liuzhihao   
* 创建时间：2016年5月20日 上午9:41:19   
* @version    
*
 */
@Repository
public interface RegisterDao {

	//插入一条注册信息
	@DataSource("burs")
	int insertRegisterInfoToBurs(Map<Object,Object> param);
	
	//插入一条注册信息
	@DataSource("burs")
	int insertRegisterInfoToBursInfo(Map<Object,Object> param);
	
	//插入一条注册信息
	@DataSource("xmnpay")
	int insertRegisterInfoToWallet(Map<Object,Object> param);
}
