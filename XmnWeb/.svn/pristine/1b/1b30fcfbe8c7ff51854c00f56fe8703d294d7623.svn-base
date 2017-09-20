package com.xmniao.xmn.core.businessman.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.MSellerLandmark;
import com.xmniao.xmn.core.businessman.entity.TSellerLandmark;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerLandmarkDao
 * 
 * @类描述： 商家经纬度信息
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时30分16秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerLandmarkDao extends BaseDao<TSellerLandmark>{
	
	//根据商家id查询
	@DataSource("slave")
	public List<TSellerLandmark> getSellerLandmarkByid(Long sellerid);
	
	/**
	 * 根据商家编号查询坐标
	 * @param sellerid
	 * @return
	 */
	@DataSource("slave")
	MSellerLandmark getMSellerLandmarkByid(Long sellerid);
}
