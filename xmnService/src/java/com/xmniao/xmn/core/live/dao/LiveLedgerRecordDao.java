package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.LiveLedgerRecord;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 项目描述：XmnService
 * API描述：获取平台分账红包  推荐用户的
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */
@Repository
public interface LiveLedgerRecordDao {
	
	/**
	 *	描述：获取平台分账红包  推荐用户的   根据当前用户ID
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public LiveLedgerRecord queryLiveLedgerRecordByUid(Map<Object, Object> map);
	
	
	/**
	 *	描述：获取平台分账红包  推荐用户的  根据红包ID
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public LiveLedgerRecord queryLiveLedgerRecordByRedpacketId(Integer id);

	/**
	 * 获取最新信息
	 * @author xiaoxiong
	 * @date 2016年12月27日
	 */
	@DataSource("joint")
	public List<LiveLedgerRecord> queryLiveLedgerRecordOrderList();

	/**
	 * 查询用户分账记录
	 * @author xiaoxiong
	 * @date 2017年1月11日
	 */
	@DataSource("joint")
	public List<Map<String, Object>> queryLiveledgerList(Map<String,Object> paramsMap);
	
	/**
	 * 获取我的壕友对我的贡献值
	 * @param fuid 壕友UID
	 * @param uid  登录用户的UID
	 * @return
	 */
	@DataSource("joint")
	public Double getContributionValue(@Param("fuid") String fuid,@Param("uid")String uid);
	
}
