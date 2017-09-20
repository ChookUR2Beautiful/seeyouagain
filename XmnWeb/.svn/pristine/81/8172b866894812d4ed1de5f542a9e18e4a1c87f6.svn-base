package com.xmniao.xmn.core.userData_statistics.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.userData_statistics.entity.TUserTransactionFlow;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

@Repository
public interface UserTransactionFlowDao {

	/**
	 * 用户交易流水总数
	 * @param userTransactionFlow
	 * @return
	 */
	@DataSource("slave")
	Long count(TUserTransactionFlow userTransactionFlow);
	
	/**
	 * 用户交易流水列表
	 * @param userTransactionFlow
	 * @return
	 */
	@DataSource("slave")
	List<TUserTransactionFlow> getList(TUserTransactionFlow userTransactionFlow);
	
	/**
	 * 用户交易流水总额
	 * @return
	 */
	@DataSource("slave")
	TUserTransactionFlow getCensusTotal(TUserTransactionFlow transactionFlow);
	
}
