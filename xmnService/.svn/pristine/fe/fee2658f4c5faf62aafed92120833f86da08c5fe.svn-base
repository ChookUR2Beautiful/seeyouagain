package com.xmniao.xmn.core.seller.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


/**
 * 
*      
* 类名称：SellerRecommendDao   
* 类描述：商家推荐   
* 创建人：xiaoxiong   
* 创建时间：2016年11月10日 上午10:58:40     
*
 */
@Repository
public interface SellerRecommendDao {
	
	@DataSource("joint")
	List<Map<Object, Object>> selectSeller(Map<Object, Object> params);
	
	/**
	 * 
	 * @Description: 查询用户消费数量
	 * @author xiaoxiong
	 * @date 2016年11月10日
	 */
	@DataSource("joint")
	int queryConsuCont(int uid);
	
}
