/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TVstarRewardConfDao;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardConf;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarRewardConfService
 * 
 * 类描述： 推荐奖励配置Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-7-17 下午6:24:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TVstarRewardConfService extends BaseService<TVstarRewardConf> {
	
	@Autowired
	private TVstarRewardConfDao rewardConfDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return rewardConfDao;
	}

	/**
	 * 
	 * 方法描述：获取大赛奖励配置 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-20下午3:31:47 <br/>
	 * @return
	 */
	public TVstarRewardConf getFirstObject(){
		return rewardConfDao.getFirstObject();
	}
}
