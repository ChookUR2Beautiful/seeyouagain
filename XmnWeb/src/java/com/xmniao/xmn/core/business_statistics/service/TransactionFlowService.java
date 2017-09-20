package com.xmniao.xmn.core.business_statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.business_statistics.dao.TransactionFlowDao;
import com.xmniao.xmn.core.business_statistics.entity.TransactionFlow;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessActionService
 * 
 * 类描述：交易金额流水业务类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-16 11:00:22
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TransactionFlowService {
	@Autowired TransactionFlowDao transactionFlowDao;
	
	/**
	 * 统计列表
	 * @param TransactionFlow
	 * @return
	 */
	public  List<TransactionFlow> getList(TransactionFlow transactionFlow){
		return transactionFlowDao.getList(transactionFlow);
	}
	
	/**
	 * 汇总
	 * @return
	 */
	public TransactionFlow getCensusTotal(TransactionFlow transactionFlow){
		return transactionFlowDao.getCensusTotal(transactionFlow);
	}
	
	/**
	 * 数量
	 * @param TransactionFlow
	 * @return
	 */
	public  Long count(TransactionFlow transactionFlow){
		return transactionFlowDao.count(transactionFlow);
	}

}
