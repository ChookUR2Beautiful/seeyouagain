package com.xmniao.xmn.core.seller.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.catehome.response.HomeImageResponse;
import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface BannerDao {

	/**
	 * 根据城市查询城市banner图
	 * @param city
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllByCity(Map<Object,Object> map);
	
	/**
	 * 查询全国通用的banner图
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAll(String position);
	
	/**
	 * 查询非重点推荐的bannner
	 * @param city
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findBannersByIsNotEmphasis(@Param("city")Integer city);
	
	/**
	 * 竞拍banner图 
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findBidBanner();

	@DataSource("joint")
    List<Map<Object,Object>> findBidBannerByType(@Param("type") Integer type);

	/**首页活动模块（本地城市）
	 * @param cityid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> findHomeActivity(Integer cityid);

	/**全国的专题模块
	 * @return
	 */
	List<Map<Object, Object>> findHomeActivityNation();
}
