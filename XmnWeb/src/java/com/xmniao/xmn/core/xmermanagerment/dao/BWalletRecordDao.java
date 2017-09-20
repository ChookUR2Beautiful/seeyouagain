package com.xmniao.xmn.core.xmermanagerment.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmermanagerment.entity.BWalletRecord;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BWalletRecordDao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-26下午3:39:00
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BWalletRecordDao extends BaseDao<BWalletRecord> {
	
	/**
	 * 根据钱包记录id删除信息
	 * @param id
	 * @return
	 */
	@DataSource("pay")
    int deleteByPrimaryKey(Integer id);


	/**
	 * 插入钱包记录
	 * @param record
	 * @return
	 */
	@DataSource("pay")
    int insertSelective(BWalletRecord record);

	/**
	 * 根据主键ID查询钱包记录
	 * @param id
	 * @return
	 */
	@DataSource("pay")
    BWalletRecord selectByPrimaryKey(Integer id);

	/**
	 * 根据主键ID更新钱包记录
	 * @param record
	 * @return
	 */
	@DataSource("pay")
    int updateByPrimaryKeySelective(BWalletRecord record);
	
	/**
	 * 返回钱包记录列表
	 * @param record
	 * @return
	 */
	@DataSource("pay")
	public List<BWalletRecord> getWalletRecordList(BWalletRecord record);
	
	/**
	 * 返回钱包记录条数
	 * @param record
	 * @return
	 */
	@DataSource("pay")
	public long getWalletRecordCount(BWalletRecord record);
	
}