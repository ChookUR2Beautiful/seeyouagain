/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.dao.TLiveLevelDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveLevel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


@Service
public class TLiveLevelService {
	
	@Autowired
	private TLiveLevelDao liveLevelDao;
	
	/**
	 * 方法描述：查询显示数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:50 <br/>
	 * @param liveLevel
	 * @return
	 */
	
	public Pageable<TLiveLevel> getLiveLevelInfoList(TLiveLevel liveLevel) {
		Pageable<TLiveLevel> liveLevelInfoList = new Pageable<TLiveLevel>(liveLevel);
		List<TLiveLevel> liveLevelList = liveLevelDao.getLiveLevelList(liveLevel);
	
		liveLevelInfoList.setContent(liveLevelList);
		liveLevelInfoList.setTotal(liveLevelDao.countLiveLevel(liveLevel));
	    return liveLevelInfoList;
	}
    
    
	/**
	 * 方法描述：保存前端数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:10 <br/>
	 * @param liveLevel
	 */
	@Transactional(propagation=Propagation.NEVER)
	public void saveActivity(TLiveLevel liveLevel) throws Exception{
		liveLevel.setCreateTime(new Date());
		liveLevelDao.insertSelective(liveLevel);
	}
	
	
	
	/**
	 * 方法描述：删除内容 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:42:01 <br/>
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) throws Exception{
		//删除关联的公式数据
		return liveLevelDao.deleteByPrimaryKey(id);
	}
	
	
	/**
	 * 方法描述：更新前端数据 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月29日上午11:48:31 <br/>
	 * @param liveLevel
	 */
	public int saveUpdateActivity(TLiveLevel liveLevel) throws Exception{
		liveLevel.setUpdateTime(new Date());
		return liveLevelDao.updateByPrimaryKeySelective(liveLevel);
	}
	
	public TLiveLevel getLiveLevelData(TLiveLevel liveLevel) {
		TLiveLevel liveLevelInfo = new TLiveLevel();
		Integer recordId = liveLevel.getId();
		if (recordId != null) {
			liveLevelInfo = liveLevelDao.selectByPrimaryKey(recordId);
		}

		return liveLevelInfo;
	}
	
	/**
	 * 校验连锁店帐号唯一性
	 * @return
	 */
	public Long checkLevelName(String levelName){
		long num = liveLevelDao.checkLevelName(levelName);
		return num;
	}


	/**
	 * 方法描述：查询主播等级列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-6下午8:37:23 <br/>
	 * @param liveLevel
	 * @return
	 */
	public List<TLiveLevel> getLiveLevelList(TLiveLevel liveLevel) {
		return liveLevelDao.getLiveLevelList(liveLevel);
	}
}
