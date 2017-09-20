package com.xmniao.xmn.core.userData_statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.userData_statistics.dao.UserTransactionFlowDao;
import com.xmniao.xmn.core.userData_statistics.entity.TUserTransactionFlow;

/**
 * 用户交易流水统计
 * @author Administrator
 *
 */
@Service
public class UserTransactionFlowService {
	
	@Autowired UserTransactionFlowDao userTransactionFlowDao;
	/**
	 * 用户交易流水总数
	 * @param userTransactionFlow
	 * @return
	 */
	public Long count(TUserTransactionFlow userTransactionFlow){
		return userTransactionFlowDao.count(userTransactionFlow);
	}
	
	/**
	 * 用户交易流水列表
	 * @param userTransactionFlow
	 * @return
	 */
	public List<TUserTransactionFlow> getList(TUserTransactionFlow userTransactionFlow){
		return userTransactionFlowDao.getList(userTransactionFlow);
	}
	
	/**
	 * 用户交易流水总额
	 * @return
	 */
	public TUserTransactionFlow getCensusTotal(TUserTransactionFlow transactionFlow){
		return userTransactionFlowDao.getCensusTotal(transactionFlow);
	}
}
