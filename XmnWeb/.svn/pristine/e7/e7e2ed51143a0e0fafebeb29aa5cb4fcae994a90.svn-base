package com.xmniao.xmn.core.xmnpay.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmnpay.entity.LiveWallet;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveWalletDao
 *
 * 类描述：直播钱包服务DAO
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-15下午3:18:06
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface LiveWalletDao  extends BaseDao<LiveWallet>{
	
	/**
	 * 
	 * 方法描述：删除直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:17
	 * @param id
	 * @return
	 */
	@DataSource("pay")
    int deleteByPrimaryKey(Integer id);

	/**
	 * 
	 * 方法描述：添加直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:09
	 * @param record
	 * @return
	 */
	@DataSource("pay")
    int addLiveWallet(LiveWallet record);

	
	/**
	 * 
	 * 方法描述：查询直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:22
	 * @param id
	 * @return
	 */
	@DataSource("pay")
    LiveWallet selectByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 方法描述：查询直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:22
	 * @param uid
	 * @return
	 */
	@DataSource("pay")
	LiveWallet selectByUid(Integer uid);

	/**
	 * 
	 * 方法描述：更新直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:27
	 * @param record
	 * @return
	 */
	@DataSource("pay")
    int updateLiveWallet(LiveWallet record);
	
	/**
	 * 
	 * 方法描述：根据会员Uid更新直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:27
	 * @param record
	 * @return
	 */
	@DataSource("pay")
	int updateLiveWalletByUid(LiveWallet record);
	
	/**
	 * 
	 * 方法描述：通过用户ID数组查询用户直播钱包
	 * 创建人： huang'tao
	 * 创建时间：2016-8-22上午9:43:38
	 * @return
	 */
	@DataSource("pay")
	List<LiveWallet> selectLiveWalletByUids(Object[] uids);
}