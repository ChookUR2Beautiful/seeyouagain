package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.user_terminal.entity.TSellerTrader;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
 * 商家分类处理类
 * @author ch
 *
 */
@Repository
public interface SellerTraderDao {
	
	/**
	 * 获取二级分类商家
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	List<TSeller> getTwoTraderAddSellerList(TSeller seller);
	
	/**
	 * 获取二级分类商家数量
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	Long getTwoTraderAddSellerListCount(TSeller seller);
	
	/**
	 * 获取未添加二级分类的商家
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	List<TSeller> getTwoTraderNoAddSellerList(TSeller seller);
	
	/**
	 * 获取未添加二级分类的商家数量
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	Long getTwoTraderNoAddSellerListCount(TSeller seller);
	
	/**
	 * 添加商家分类关系
	 * @param sellerTrader
	 */
	void add(TSellerTrader sellerTrader);
	
	/**
	 * 移除商家分类关系
	 * @param sellerTrader
	 */
	void delete(TSellerTrader sellerTrader);
}
