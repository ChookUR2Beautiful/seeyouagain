/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TLiveRecordDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LivePageHomeService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-16 上午11:46:53 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class LivePageHomeService extends BaseService<TLiveRecord> {
	
	/**
	 * 注入直播通告Dao
	 */
	@Autowired
	private TLiveRecordDao liveRecordDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveRecordDao;
	}
	
	/**
	 * 
	 * 方法描述：更新首页推荐等信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-16下午2:12:06 <br/>
	 * @param liveRecord
	 * @return
	 */
	public int updateRecommendedInfo(TLiveRecord liveRecord){
		return liveRecordDao.updateRecommendedInfo(liveRecord);
	}

	
	public List<TLiveRecord> getFreshmanRecordList(TLiveRecord liveRecord){
		return liveRecordDao.getFreshmanRecordInfoList(liveRecord);
	}
	
}
