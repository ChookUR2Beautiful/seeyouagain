package com.xmniao.xmn.core.marketingmanagement.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerLandmark;
import com.xmniao.xmn.core.marketingmanagement.entity.BargainProduct;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityRule;
import com.xmniao.xmn.core.marketingmanagement.entity.TBargainPrice;
import com.xmniao.xmn.core.user_terminal.entity.TShareRange;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BargainProductDao
 * 
 * 类描述：查询爆品
 * 
 * 创建人： cao'yingde
 * 
 * 创建时间：2015年06月12日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BargainProductDao extends BaseDao<BargainProduct>{
	@Transactional(propagation = Propagation.REQUIRED)
	public void addTBargainPrice(TBargainPrice temp);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAllByBpid(Long Bpid);
	
	@DataSource("slave")
	public List<TBargainPrice> getBargainProductList(@Param("bid")Long bid);

	/**
	 * 根据商家ID获取该商家已经被添加为爆品商家的次数
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	public Long getSellerCount(TSeller seller);
	
	/**
	 * 更新爆品信息中的商家坐标数据
	 */
	public int updateSellerLandmark(TSellerLandmark tSellerLandmark);
}
