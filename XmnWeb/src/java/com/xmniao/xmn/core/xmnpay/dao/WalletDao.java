package com.xmniao.xmn.core.xmnpay.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;

public interface WalletDao extends BaseDao<Bwallet>{
	
	/*
	 * 是否含有用户钱包
	 */
	@DataSource("pay")
	int hasWallet(Bwallet wallet);
	
	/*
	 * 添加钱包
	 */
	@DataSource("pay")
	int addWallet(Bwallet wallet);
	/**
	 * @Title:selectWallet
	 * @Description:查询钱包信息
	 * @param uid
	 * @return List<Bwallet>
	 * @throw
	 * @author hls
	 */
	@DataSource("pay")
	List<Bwallet> selectWallet(Object[] objects);
	
	/**
	 * 返回钱包列表
	 * @param wallet
	 * @return
	 */
	@DataSource("pay")
	public List<Bwallet> getWalletList(Bwallet wallet);
	
	
	/**
	 * 返回钱包记录数
	 * @param wallet
	 * @return
	 */
	@DataSource("pay")
	public Long getWalletCount(Bwallet wallet);
}