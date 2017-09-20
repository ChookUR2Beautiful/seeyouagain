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
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveRecommendRecordDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecommendRecord;

/**
 * 
 * 项目名称：XmnWeb_170605_dev
 * 
 * 类名称：TLiveRecommendRecordService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： Administrator
 * 
 * 创建时间：2017年6月2日 下午6:21:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveRecommendRecordService  extends BaseService<TLiveRecommendRecord> {
	
	@Autowired
	private TLiveRecommendRecordDao liveRecommendRecordDao;
	
	
	@Autowired
	private BLiverDao bLiverDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveRecommendRecordDao;
	}
	
	/**
	 * 方法描述：查询显示数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:50 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	
	public Pageable<TLiveRecommendRecord> getLiveRecommendRecordInfoList(TLiveRecommendRecord liveRecommendRecord) {
		Pageable<TLiveRecommendRecord> liveRecommendRecordInfoList = new Pageable<TLiveRecommendRecord>(liveRecommendRecord);
		List<TLiveRecommendRecord> liveRecommendRecordList = liveRecommendRecordDao.getLiveRecommendRecordList(liveRecommendRecord);
	
		liveRecommendRecordInfoList.setContent(liveRecommendRecordList);
		liveRecommendRecordInfoList.setTotal(liveRecommendRecordDao.countLiveRecommendRecord(liveRecommendRecord));
	    return liveRecommendRecordInfoList;
	}

	
	/**
	 * 方法描述：保存好看推荐 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:32:56 <br/>
	 * @param liveRecommendRecord
	 * @return
	 * @throws Exception
	 */
	public int saveAddActivity(TLiveRecommendRecord liveRecommendRecord) throws Exception{
		liveRecommendRecord.setUpdateTime(new Date());
		//判断是否有选择城市, 未选择时为全国
		if (liveRecommendRecord.getCityId() != null ){  
			liveRecommendRecord.setIsNationwide(0);
		}else{
			liveRecommendRecord.setIsNationwide(1);
		}
		return liveRecommendRecordDao.insertSelective(liveRecommendRecord);
	}
	
	/**
	 * 方法描述：更新前端数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:31 <br/>
	 * @param liveRecommendRecord
	 */
	public int saveUpdateActivity(TLiveRecommendRecord liveRecommendRecord) throws Exception {
		int cout = 0;
//		Integer id = liveRecommendRecord.getId();
//		TLiveRecommendRecord bean = liveRecommendRecordDao.selectByPrimaryKey(id);
		if (liveRecommendRecord != null) {
//			bean.setHomeSort(liveRecommendRecord.getHomeSort()); // 更新排序
			liveRecommendRecord.setUpdateTime(new Date());
			cout = liveRecommendRecordDao.updateByPrimaryKeySelective(liveRecommendRecord);
		}
		return cout ;
	}
	
	
	/**
	 * 方法描述：删除内容 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:42:01 <br/>
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) throws Exception{
		//删除关联的好看推荐
		return liveRecommendRecordDao.deleteByPrimaryKey(id);
	}
	
	public Pageable<TLiveRecommendRecord> getFreshmanRecommendRecordInfoList(TLiveRecommendRecord liveRecommendRecord) {
		Pageable<TLiveRecommendRecord> liveRecommendRecordInfoList = new Pageable<TLiveRecommendRecord>(liveRecommendRecord);
		List<TLiveRecommendRecord> liveRecommendRecordList = liveRecommendRecordDao.getFreshmanRecommendRecordList(liveRecommendRecord);
	
		liveRecommendRecordInfoList.setContent(liveRecommendRecordList);
		liveRecommendRecordInfoList.setTotal(liveRecommendRecordDao.countFreshmanRecommendRecord(liveRecommendRecord));
	    return liveRecommendRecordInfoList;
	}
	
	
	/**
	 * 方法描述：获取非签约主播信息<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月2日下午6:21:12 <br/>
	 * @param liver
	 * @return
	 */
	public List<BLiver> getLiverInfoList(BLiver liver) {
		List<BLiver> LiverInfoList = bLiverDao.getList(liver);

		return LiverInfoList;
	}
	
	
	public List<TLiveRecommendRecord> getLiveRecommendRecordList(TLiveRecommendRecord liveRecommendRecord) {
		List<TLiveRecommendRecord> liveRecommendRecordList = liveRecommendRecordDao.getLiveRecommendRecordList(liveRecommendRecord);
	    return liveRecommendRecordList;
	}
	
	
	public Pageable<TLiveRecommendRecord> getFreshmanVideoRecommendInfoList(TLiveRecommendRecord liveRecommendRecord) {
		Pageable<TLiveRecommendRecord> liveRecommendRecordInfoList = new Pageable<TLiveRecommendRecord>(liveRecommendRecord);
		List<TLiveRecommendRecord> liveRecommendRecordList = liveRecommendRecordDao.getFreshmanVideoRecommendList(liveRecommendRecord);
	
		liveRecommendRecordInfoList.setContent(liveRecommendRecordList);
		liveRecommendRecordInfoList.setTotal(liveRecommendRecordDao.countFreshmanVideoRecommend(liveRecommendRecord));
	    return liveRecommendRecordInfoList;
	}

	/**
	 * 方法描述：获取新食尚大赛精彩视频
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月19日下午4:55:37 <br/>
	 * @param video
	 * @return
	 */
	public List<TLiveRecommendRecord> getTVstarRecord(TLiveRecommendRecord video) {
		return liveRecommendRecordDao.getTVstarRecords(video);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月19日下午5:40:39 <br/>
	 * @param video
	 */
	public int addTVstarRecord(TLiveRecommendRecord video) {
		video.setModuleType(4);
		return liveRecommendRecordDao.insert(video);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月19日下午5:56:56 <br/>
	 * @param id
	 * @param sort
	 */
	public int updateVideoSort(Integer id, Integer sort) {
		return liveRecommendRecordDao.updateVideoSort(id,sort);
		
	}
}
