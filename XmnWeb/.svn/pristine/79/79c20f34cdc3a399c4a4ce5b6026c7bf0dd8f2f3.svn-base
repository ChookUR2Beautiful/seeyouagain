package com.xmniao.xmn.core.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.dao.TradeDao;
import com.xmniao.xmn.core.common.entity.TTrade;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TradeService
 * 
 * 类描述： 经营(行业)类别
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年12月01日11时07分10秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TradeService extends BaseService<TTrade> {

	public static List<TTrade> trades;
	 
	@Autowired
	private TradeDao tradeDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return tradeDao;
	}
	
	public List<TTrade> getLdAll(TTrade trade){
		if(null == trades){
			synchronized (this) {
				if(null == trades){
					trades = tradeDao.getLdAll(trade);
				}
			}
		}
		return trades;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer addBatch(List<TTrade> list) {
		trades = null;
		return tradeDao.addBatch(list);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(TTrade t) {
		trades = null;
		tradeDao.add(t);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer delete(Object[] objects) {
		trades = null;
		return tradeDao.delete(objects);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer update(TTrade t) {
		trades = null;
		return tradeDao.update(t);
	}
	
	
	
}
