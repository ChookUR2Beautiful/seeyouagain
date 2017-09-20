package com.xmniao.xmn.core.vstar.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardConf;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarRewardConfMapper
 * 
 * 类描述： 新时尚大赛推荐奖励配置DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-7-17 下午4:11:23 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarRewardConfDao extends BaseDao<TVstarRewardConf>{
	
	/**
	 * 
	 * 方法描述：获取大赛推荐奖励配置信息(唯一记录)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-20下午3:29:20 <br/>
	 * @return
	 */
	TVstarRewardConf getFirstObject();
}