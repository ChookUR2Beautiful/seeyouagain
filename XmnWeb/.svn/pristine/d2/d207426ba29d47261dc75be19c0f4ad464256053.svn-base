package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerAccountDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.util.NMD5;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerAccountService
 * 
 * 类描述： 商家账号
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时42分53秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class SellerAccountService extends BaseService<TSellerAccount> {

	@Autowired
	private SellerAccountDao sellerAccountDao;
	
	@Autowired
	private SellerDao sellerDao;

	@Override
	protected BaseDao getBaseDao() {
		return sellerAccountDao;
	}

	public long getAcount(String account) {
		return sellerAccountDao.getAcount(account);

	};
	
	
	/**
	 * 查询账号列表信息
	 * @return
	 */
	public List<TSellerAccount> getAccountList(TSellerAccount sellerAccount){
		return sellerAccountDao.getAccountList(sellerAccount);
	}

	/**
	 * 获取商家账号信息
	 */
	public TSeller getSeller(String account){
		return sellerDao.getSellerBySellerAccount(account);
	}
	
	/**
	 * 更新账号信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer update(TSellerAccount account) {
		//编辑初始化时候默认显示000000，如果前台传过来的为此值则不不更新密码
		if (account.getOldpassword().equals(account.getPassword())
				|| account.getPassword().equals("......")) {
			account.setPassword(null);
		} else {
			account.setPassword(NMD5.Encode(account.getPassword()));// md5加密
		}
		return super.update(account);
	}
	/**
	 * 校验帐号唯一性
	 * @return
	 */
	public Long checkAccount(String account){
		long num = sellerAccountDao.getAcount(account);
		return num;
	}
	
	/**
	 * 校验连锁店帐号唯一性
	 * @return
	 */
	public Long checkMultipShopAccount(String account){
		long num = sellerAccountDao.checkMultipShopAccount(account);
		return num;
	}
}
