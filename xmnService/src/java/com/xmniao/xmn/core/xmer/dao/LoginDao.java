package com.xmniao.xmn.core.xmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：xmnService   
* 类名称：LoginDao   
* 类描述：登录接口 
* 创建人：liuzhihao   
* 创建时间：2016年5月19日 下午7:51:42   
* @version    
*
 */
@Repository
public interface LoginDao {
	
	/**
	 * 
	* @Title: login
	* @Description: 登录DAO
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("burs")
	public Map<Object,Object> login(Map<Object,Object> param);
	
	//检查登录账号是否正确
	@DataSource("burs")
	public int checkAccount(String account);
	
	//插入一条登录记录
	@DataSource("burs")
	public int addLoginRecord(Map<Object,Object> param);
	
	//查询寻蜜客信息
	@DataSource("burs")
	public Map<Object,Object> queryXmerByUid(Integer id);
	
	//获取用户余额和积分
	@DataSource("xmnpay")
	public Map<Object,Object> queryWalletByUid(Integer id);
}
