package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
 * 项目名称：xmnService
 * 
 * 类名称：WalletRecordDao
 *
 * 类描述：用户余额DAO
 *
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-23下午6:31:48
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Repository
public interface WalletRecordDao {

	/**
	 * 
	* @Title: queryXmkWalletByRtype
	* @Description:根据寻蜜客钱包id查询寻蜜客钱包记录
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("xmnpay")
	public List<Map<Object, Object>> queryXmkWalletByRtype(Map<Object,Object> map);

}