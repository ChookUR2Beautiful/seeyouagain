package com.xmniao.dao.vstar;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.vstar.TVstarPlayerInfo;


/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarPlayerInfoMapper
 * 
 * 类描述： 星食尚大赛选手信息表DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-2 上午11:56:39 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarPlayerInfoDao {
	
	/**
	 * 
	 * 方法描述：根据主键ID获取选手信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午5:47:54 <br/>
	 * @param id
	 * @return
	 */
	TVstarPlayerInfo getObject(int id);
	
	/**
	 * 
	 * 方法描述：获取选手信息列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午5:47:54 <br/>
	 * @param id
	 * @return
	 */
	List<TVstarPlayerInfo> getList(TVstarPlayerInfo playerInfo);
	
	/**
	 * 
	 * 方法描述：当前节点需处理的记录总数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午6:36:26 <br/>
	 * @param playerInfo
	 * @return
	 */
	long count(TVstarPlayerInfo playerInfo);
	
	/**
	 * 
	 * 方法描述：更新选手信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10上午11:57:01 <br/>
	 * @param playerInfo
	 * @return
	 */
	int update(TVstarPlayerInfo playerInfo);
	
	
	/**
	 * 
	 * 方法描述：更新选手统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午2:11:36 <br/>
	 * @param playerInfo
	 * @return
	 */
	int updateCountInfo(TVstarPlayerInfo playerInfo);
	
	/**
	 * 
	 * 方法描述：获取新时尚大赛选手被关注关注、点赞信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午2:55:03 <br/>
	 * @param paraMap
	 * @return resultMap ——> type:关注类型 1 关注 2 点赞
	 */
	List<Map<String,Object>> getVstarLikeList(Map<String,Object> paraMap);
	
	
	/**
	 * 
	 * 方法描述：获取新时尚大赛选手被评论信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午4:12:13 <br/>
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getVstarCommentList(Map<String,Object> paraMap);
	
	
	/**
	 * 
	 * 方法描述：获取赛区列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-13下午4:15:08 <br/>
	 * @return
	 */
	List<Map<String,Object>> getDivisionList();
	
	
	/**
	 * 
	 * 方法描述：更新总决赛选手类型 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-14下午3:29:55 <br/>
	 * @param paraMap
	 * @return
	 */
	long updatePlayerType(Map<String,Object> paraMap);
	
	/**
	 * 
	 * 方法描述：大赛选手鸟蛋降序排名 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-16下午4:18:15 <br/>
	 * @return
	 */
	long updateEggRankNum();
	
	/**
	 * 
	 * 方法描述：大赛选手鸟蛋降序排名 (周榜)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-16下午4:18:15 <br/>
	 * @return
	 */
	long updateEggRankNumWeek();
	
	/**
	 * 
	 * 方法描述：大赛选手鸟蛋降序排名 (月榜)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-16下午4:18:15 <br/>
	 * @return
	 */
	long updateEggRankNumMonth();
	
	
	/**
	 * 
	 * 方法描述：大赛选手粉丝降序排名 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-16下午4:18:15 <br/>
	 * @return
	 */
	long updateFansRankNum();
	
	/**
	 * 
	 * 方法描述：大赛选手粉丝降序排名(周榜) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-16下午4:18:15 <br/>
	 * @return
	 */
	long updateFansRankNumWeek();
	
	/**
	 * 
	 * 方法描述：大赛选手粉丝降序排名(月榜) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-16下午4:18:15 <br/>
	 * @return
	 */
	long updateFansRankNumMonth();
	
	/**
	 * 
	 * 方法描述：获取APP端用户被关注数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-7下午5:32:40 <br/>
	 * @return
	 */
	List<Map<String,Object>> getFansCountInitList(Map<String,Object> paramMap);
	
}