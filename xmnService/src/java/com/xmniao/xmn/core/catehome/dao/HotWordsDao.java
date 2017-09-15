package com.xmniao.xmn.core.catehome.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.catehome.entity.HotWords;
import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface HotWordsDao {
	
	@DataSource("joint")
	List<HotWords> queryHotWordsOrder(Map<String,Object> params);

	
	/**
	 * 查询热搜关键字数量
	 * @author xiaoxiong
	 * @date 2016年11月23日
	 */
	@DataSource("joint")
	HotWords queryKeyWordByKeyWord(Map<String, Object> params);

	/**
	 * 
	 * @author xiaoxiong
	 * @date 2016年11月23日
	 */
	@DataSource("joint")
	void insertSelective(HotWords hotWord);

	/**
	 * 修改
	 * @author xiaoxiong
	 * @date 2016年11月23日
	 */
	@DataSource("joint")
	void updateByPrimaryKeySelective(HotWords hotWord);

}
