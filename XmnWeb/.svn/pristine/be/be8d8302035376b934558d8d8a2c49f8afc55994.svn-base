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
import com.xmniao.xmn.core.live_anchor.dao.TLiveDeliciousDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveDelicious;

@Service
public class TLiveDeliciousService  extends BaseService<TLiveDelicious> {
	
	@Autowired
	private TLiveDeliciousDao liveDeliciousDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveDeliciousDao;
	}
	

	public Pageable<TLiveDelicious> getLiveDeliciousInfoList(TLiveDelicious liveRecommendRecord) {
		Pageable<TLiveDelicious> liveRecommendRecordInfoList = new Pageable<TLiveDelicious>(liveRecommendRecord);
		List<TLiveDelicious> liveRecommendRecordList = liveDeliciousDao.getLiveDeliciousList(liveRecommendRecord);
	
		liveRecommendRecordInfoList.setContent(liveRecommendRecordList);
		liveRecommendRecordInfoList.setTotal(liveDeliciousDao.countLiveDelicious(liveRecommendRecord));
	    return liveRecommendRecordInfoList;
	}


	public int saveAddActivity(TLiveDelicious liveRecommendRecord) throws Exception{
		liveRecommendRecord.setUpdateTime(new Date());
		return liveDeliciousDao.insertSelective(liveRecommendRecord);
	}
	

	public int saveUpdateActivity(TLiveDelicious liveRecommendRecord) throws Exception {
		int cout = 0;
		if (liveRecommendRecord != null) {
			liveRecommendRecord.setUpdateTime(new Date());
			cout = liveDeliciousDao.updateByPrimaryKeySelective(liveRecommendRecord);
		}
		return cout ;
	}
	

	public int deleteById(Integer id) throws Exception{
		//删除关联的好看推荐
		return liveDeliciousDao.deleteByPrimaryKey(id);
	}
	

}
