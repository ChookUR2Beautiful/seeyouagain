/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.dao.TLiveEntertainmentDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveEntertainment;

@Service
public class TLiveEntertainmentService  extends BaseService<TLiveEntertainment> {
	
	@Autowired
	private TLiveEntertainmentDao liveEntertainmentDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveEntertainmentDao;
	}
	

	public Pageable<TLiveEntertainment> getLiveEntertainmentInfoList(TLiveEntertainment liveRecommendRecord) {
		Pageable<TLiveEntertainment> liveRecommendRecordInfoList = new Pageable<TLiveEntertainment>(liveRecommendRecord);
		List<TLiveEntertainment> liveRecommendRecordList = liveEntertainmentDao.getLiveEntertainmentList(liveRecommendRecord);
	
		liveRecommendRecordInfoList.setContent(liveRecommendRecordList);
		liveRecommendRecordInfoList.setTotal(liveEntertainmentDao.countLiveEntertainment(liveRecommendRecord));
	    return liveRecommendRecordInfoList;
	}


	public int saveAddActivity(TLiveEntertainment liveRecommendRecord) throws Exception{
		liveRecommendRecord.setUpdateTime(new Date());
		return liveEntertainmentDao.insertSelective(liveRecommendRecord);
	}
	

	public int saveUpdateActivity(TLiveEntertainment liveRecommendRecord) throws Exception {
		int cout = 0;
//		Integer id = liveRecommendRecord.getId();
//		TLiveEntertainment bean = liveEntertainmentDao.selectByPrimaryKey(id);
		if (liveRecommendRecord != null) {
//			bean.setHomeSort(liveRecommendRecord.getHomeSort()); // 更新排序
			liveRecommendRecord.setUpdateTime(new Date());
			cout = liveEntertainmentDao.updateByPrimaryKeySelective(liveRecommendRecord);
		}
		return cout ;
	}
	
	
	public int deleteById(Integer id) throws Exception{
		//删除关联的好看推荐
		return liveEntertainmentDao.deleteByPrimaryKey(id);
	}
	

}
