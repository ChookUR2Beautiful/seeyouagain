package com.xmn.saas.service.shop.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.dao.shop.TradeDao;
import com.xmn.saas.entity.shop.Trade;
import com.xmn.saas.service.shop.TradeServcie;

/**
 * 
*      
* 类名称：TradeServcieImpl   
* 类描述：   商家经营分类
* 创建人：xiaoxiong   
* 创建时间：2016年9月24日 下午4:29:06   
* 修改人：xiaoxiong   
* 修改时间：2016年9月24日 下午4:29:06   
* 修改备注：   
* @version    
*
 */
@Service
public class TradeServcieImpl  implements TradeServcie{
	
	@Autowired
	private TradeDao tradeDao;

	
	@Override
	public List<Trade> list(Integer pid) {
		try {
			//查询商家经营分类
			return tradeDao.queryTrade(pid);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
