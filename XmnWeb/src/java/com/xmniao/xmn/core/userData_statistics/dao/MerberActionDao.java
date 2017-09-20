package com.xmniao.xmn.core.userData_statistics.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.userData_statistics.entity.TMerberAction;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

@Repository
public interface MerberActionDao {
	
	
	/**
	 * 列表
	 * @return
	 */
	@DataSource("slave")
	List<TMerberAction> getList(TMerberAction merberAction);
	
	/**
	 * 总计
	 * @return
	 */
	@DataSource("slave")
	TMerberAction getCensusTotal(TMerberAction merberAction);
}
