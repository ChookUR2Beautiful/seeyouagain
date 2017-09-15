package com.xmniao.xmn.core.verification.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.verification.dao.WalletDao;
import com.xmniao.xmn.core.verification.entity.Wallet;


/**
 * 用户钱包 Service实现类
 * @author Administrator
 *
 */
@Service
public class WalletService {
	
	@Autowired private WalletDao walletDao;
	
	
	/**
	 * 通过用户uID获取钱包信息	
	 * @param uid
	 * @return
	 */
	public Wallet getWalletByUid(Integer uid){
		return walletDao.getWalletByUid(uid);
	}
	
	
	/**
	 * 
	* @Title: addUserWallert
	* @Description: 为新用户创建钱包
	* @return void
	 */
	public void addUserWallert(Integer uid,String sign){
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uid", uid);
		map.put("sign", sign);
		map.put("adate", DateUtil.now());
		map.put("edate", DateUtil.now());
		map.put("ldate", DateUtil.now());
		walletDao.addUserWallert(map);
	}
}
