package com.xmn.saas.service.shop;

import java.util.List;

import com.xmn.saas.entity.shop.Trade;

public interface TradeServcie {

	/**
	 * 
	 * @Description: 根据父ID查询商家经营分类
	 * @author xiaoxiong
	 * @date 2016年9月24日
	 * @version
	 * pid父ID
	 */
	List<Trade> list(Integer pid);

}
