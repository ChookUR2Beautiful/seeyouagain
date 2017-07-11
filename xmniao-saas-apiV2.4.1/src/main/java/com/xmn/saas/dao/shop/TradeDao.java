package com.xmn.saas.dao.shop;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.shop.Trade;


@Repository
public interface TradeDao {

	List<Trade> queryTrade(Integer pid);
	

}
