package com.xmniao.urs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.LiverJournalCount;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiverDao
 * 
 * 类描述：直播会员表 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月19日 下午8:32:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface LiverDao {

	LiverBean getLiverByUid(int uid);
	
	LiverBean getLiverInfo(LiverBean liver);

	/**
	 * 
	 * 方法描述：获取指定用户列表信息
	 * 创建人： ChenBo
	 * 创建时间：2017年2月28日
	 * @param list
	 * @return List<LiverBean>
	 */
	List<LiverBean> getLiverListByUids(List<Integer> list);
	
	List<LiverBean> getLiverListByPhone(List<String> list);
	
	    
    /**
     * 
     * 方法描述：插入直播会员流水统计
     * 创建人： ChenBo
     * 创建时间：2016年12月28日
     * @param liverJournalCount
     * @return int
     */
    int insertLiverJournal(LiverJournalCount liverJournalCount);
    
    /**
     * 
     * 方法描述：更新直播会员流水统计
     * 创建人： ChenBo
     * 创建时间：2016年12月28日
     * @param liverJournalCount
     * @return int
     */
    int updateLiverJournal(LiverJournalCount liverJournalCount);
    
    /**
     * 
     * 方法描述：获取直播会员流水统计
     * 创建人： ChenBo
     * 创建时间：2016年12月28日
     * @param liverJournalCount
     * @return int
     */
    LiverJournalCount getLiverJournal(int uid);
    
    
    /**
     * 
     * 方法描述：获取指定类型会员的会员号
     * 创建人： ChenBo
     * 创建时间：2017年1月10日
     * @return List<Integer>
     */
    List<Integer> getLiverUidList(Map<String,Object> map);
    
    /**
     * 
     * 方法描述：获取指定id直播账号及等级
     * 创建人：jianming  
     * 创建时间：2017年3月30日 下午4:30:17   
     * @param ids
     * @return
     */
	List<LiverBean> getLiverLeverByIds(List<Integer> ids);
	
	/**
	 * 
	 * 方法描述：获得所有直播账号
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 上午10:07:43   
	 * @return
	 */
	List<LiverBean> getLiverLever();
	
	/**
	 * 
	 * 方法描述：获得指定直播账号
	 * 创建人：jianming  
	 * 创建时间：2017年4月11日 下午2:15:37   
	 * @param leverId
	 * @return
	 */
	LiverBean getLiverLeverById(@Param("anchorId")Integer leverId);
	
	
	Map<String, Object> queryLiverInfoByAnchorId(Integer integer);
	
	/**
	 * 
	 * 方法描述：获取主播所属信息 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年8月9日下午2:15:04 <br/>
	 * @param uid
	 * @return
	 */
	Map<String,Object> getAnchorBaseInfo(Integer uid);
	
	/**
	 * 
	 * 方法描述：获取V客的基本信息及主播的分成比例 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年8月9日下午2:49:55 <br/>
	 * @param uid
	 * @return
	 */
	Map<String,Object> getVkeAnchorInfo(Integer uid);
}