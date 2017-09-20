package com.xmniao.xmn.core.business_statistics.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.business_statistics.entity.TransactionFlow;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessActionDao
 * 
 * 类描述：交易金额流水数据交互类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-16 11:00:22
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Repository
public interface TransactionFlowDao {
	
	/**
	 * 统计列表
	 * @param businessAction
	 * @return
	 */
	@DataSource("slave")
	List<TransactionFlow> getList(TransactionFlow transactionFlow);
	
	/**
	 * 汇总
	 * @return
	 */
	@DataSource("slave")
	TransactionFlow getCensusTotal(TransactionFlow transactionFlow);
	
	/**
	 * 数量
	 * @param businessAction
	 * @return
	 */
	@DataSource("slave")
	Long count(TransactionFlow transactionFlow);

}
