package com.xmniao.urs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.RankRedPacketDetail;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiveFansRankDao
 * 
 * 类描述： 粉丝分账等级DAO，涉及b_live_fans_rank,b_live_fans_rank_detail,b_rank_red_packet_detail三表操作
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年2月25日 下午2:09:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface LiveFansRankDao {

	/**
	 * 
	 * 方法描述：获取等级的基本信息
	 * 创建人： ChenBo
	 * 创建时间：2017年2月25日
	 * @param id
	 * @return LiveFansRank
	 */
    LiveFansRank getLiveFansRankBase(LiveFansRank liveFansRank);

	/**
	 * 
	 * 方法描述：获取等级基本信息列表
	 * 创建人： ChenBo
	 * 创建时间：2017年2月25日
	 * @param paramMap
	 * @return List<LiveFansRank>
	 */
    List<LiveFansRank> getLiveFansRankBaseList(Map<String,Object> paramMap);

    /**
     * 
     * 方法描述：获取指定粉丝等级分账信息
     * 创建人： ChenBo
     * 创建时间：2017年2月25日
     * @param paramMap
     * @return LiveFansRankDetail
     */
    LiveFansRankDetail getLiveFansRankDetail(LiveFansRankDetail detail);
    
    /**
     * 
     * 方法描述：获取指定粉丝等级分账信息列表
     * 创建人： ChenBo
     * 创建时间：2017年2月25日
     * @param paramMap
     * @return List<LiveFansRankDetail>
     */
    List<LiveFansRankDetail> getLiveFansRankDetailList(Map<String,Object> paramMap);
    
    /**
     * 
     * 方法描述：获取指定等级和角色可享受的红包配置
     * 创建人： ChenBo
     * 创建时间：2017年2月25日
     * @param rankRedPacketDetail
     * @return RankRedPacketDetail
     */
    RankRedPacketDetail getRankRedPakcetDetail(RankRedPacketDetail rankRedPacketDetail);
    
    /**
     * 
     * 方法描述：获取指定等级下的红包配置列表
     * 创建人： ChenBo
     * 创建时间：2017年2月25日
     * @param rankRedPacketDetail
     * @return List<RankRedPacketDetail>
     */
    List<RankRedPacketDetail> getRankRedPakcetDetailList(RankRedPacketDetail rankRedPacketDetail);
    
    /**
     * 
     * 方法描述：获取V客的等级信息 <br/>
     * 创建人： Administrator <br/>
     * 创建时间：2017年8月11日上午10:03:56 <br/>
     * @param uid
     * @param rankType
     * @return
     */
    LiveFansRank getLiveFansRankByVeruid(@Param("uid")Integer uid);
}