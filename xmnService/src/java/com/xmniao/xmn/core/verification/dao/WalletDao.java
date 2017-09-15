package com.xmniao.xmn.core.verification.dao;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.verification.entity.Wallet;

/**
 * person DAO
 * @author Administrator
 *
 */
@Repository
public interface WalletDao {
	
	/**
	 * 通过用户ID获取钱包信息	
	 * @return
	 */
	@DataSource("xmnpay")
	public Wallet getWalletByUid(Integer uid);
	
	/**
	 * 
	* @Title: addUserWallert
	* @Description: 为用户新增钱包
	* @return void
	 */
	@DataSource("xmnpay")
	public void addUserWallert(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: getXmerWallet
	* @Description: 获取寻蜜客钱包
	* @return Map<Object,Object>
	 */
	@DataSource("xmnpay")
	public Map<Object,Object> getXmerWallet(Integer uid);
	
}
