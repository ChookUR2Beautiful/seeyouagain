/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TLiveGiftDetailDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGiftDetail;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveGiftDetailService
 * 
 * 类描述： 礼物详情Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-29 上午11:58:11 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveGiftDetailService extends BaseService<TLiveGiftDetail> {

	/**
	 * 注入礼物详情信息DAO
	 */
	@Autowired
	private TLiveGiftDetailDao liveGiftDetailDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveGiftDetailDao;
	}
	
	/**
	 * 
	 * 方法描述：通过礼物ID删除礼物详细信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-31下午3:24:39 <br/>
	 * @param gid
	 * @return
	 */
	int deleteByGid(Long gid){
		return liveGiftDetailDao.deleteByGid(gid);
	}
	
	
	/**
	 * 
	 * 方法描述：通过礼物ID获取礼物详细信息列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-31下午3:19:01 <br/>
	 * @param gid
	 * @return
	 */
	public List<TLiveGiftDetail> getListByGid(Long gid){
		return liveGiftDetailDao.getListByGid(gid);
	}
	

	/**
	 * 
	 * 方法描述：根据礼物ID获取礼物详细(商户分类)信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-31下午5:06:14 <br/>
	 * @param gid
	 * @return
	 */
	public TLiveGiftDetail selectByGid(Long gid){
		return liveGiftDetailDao.selectByGid(gid);
	}
	
	
}
