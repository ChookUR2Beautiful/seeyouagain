package com.xmniao.xmn.core.xmermanagerment.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWalletRecord;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BXmerWalletRecordDao
 * 
 * 类描述： 寻蜜客钱包交易记录Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-9 下午1:59:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BXmerWalletRecordDao extends BaseDao<BXmerWalletRecord>{

	/**
	 * 
	 * 方法描述：根据ID获取寻蜜客钱包记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午6:26:42
	 * @param id
	 * @return
	 */
	@DataSource(value="pay")
    public BXmerWalletRecord selectById(Integer id);
	
	/**
	 * 
	 * 方法描述：根据寻蜜客钱包ID获取寻蜜客钱包记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午6:26:42
	 * @param id
	 * @return
	 */
	@DataSource(value="pay")
    public List<BXmerWalletRecord> getList(BXmerWalletRecord xmerWalletRecord);
	
	/**
	 * 统计寻蜜客钱包交易数量
	 */
	@DataSource(value="pay")
	public Long count(BXmerWalletRecord xmerWalletRecord);
    
	
	/**
	 * 
	 * 方法描述：根据寻蜜客钱包ID获取寻蜜客钱包记录列统计信息
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-8下午6:27:35
	 * @param xids
	 * @return
	 */
	@DataSource(value="pay")
    public List<BXmerWalletRecord> getListByXids(Object[] xids);


}