package com.xmniao.xmn.core.vstar.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.vstar.entity.TVstarEnroll;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarEnrollDao
 * 
 * 类描述： 新时尚大赛报名DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-1 下午1:52:27 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarEnrollDao extends BaseDao<TVstarEnroll>{
	
	/**
	 * 
	 * 方法描述：更新新时尚大赛报名选手直播用户信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-6下午7:49:22 <br/>
	 * @param paramMap
	 * @return
	 */
	@DataSource(value="burs")
	int updateLiverVstarInfo(Map<String,Object> paramMap);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月14日下午4:44:43 <br/>
	 * @return
	 */
	List<Map<String, Object>> getCityRank();

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月14日下午6:25:21 <br/>
	 * @param date 
	 * @return
	 */
	List<Map<String, Object>> getCityRankByDate(String date);
	
}