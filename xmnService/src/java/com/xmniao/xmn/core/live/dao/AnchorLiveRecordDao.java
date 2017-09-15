package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.live.entity.FansCouponAnchorRef;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiveViewRecordInfo;
import com.xmniao.xmn.core.live.entity.SellerCouponDetailInfo;
import com.xmniao.xmn.core.seller.entity.LiveSellerLedgerInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;



/**
 * 预告/直播 记录获取dao类
 * @author yhl
 * 2016年8月11日10:45:25
 * */
@Repository
public interface AnchorLiveRecordDao {

	/**
	 * 获取预告记录
	 * @author yhl
	 * @param map {type , page}
	 * @return List<LiveRecordInfo>
	 * */
	@DataSource("joint")
	public List<LiveRecordInfo> getTrailerLiveRecordInfo(Map<Object, Object> map);
	
	/**
	 * 获取直播及回放信息记录
	 * @author yhl
	 * @param map {type , page}
	 * @return List<LiveRecordInfo>
	 * */
	@DataSource("joint")
	public List<LiveRecordInfo> getCurrentLiveRecordInfo(Map<Object, Object> map);
	
	/**
	 * 
	* @方法名称: queryOneLiveRecordInfo
	* @描述: 获取单个主播的个人直播及回放记录信息
	* @返回类型 List<LiveRecordInfo>
	* @创建时间 2016年10月19日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryOneLiveRecordInfo(Map<Object, Object> map);
	
	/**
	 * 获取用户关注的主播的正在直播列表
	 * @author yhl
	 * @param map {type , page}
	 * @return List<LiveRecordInfo>
	 * */
	@DataSource("joint")
	public List<LiveRecordInfo> getLiveRecordsAttentionList(Map<Object, Object> map);
	
	
	/**
	 * 获取用户关注的主播的正在直播列表
	 * @author yhl
	 * @param map {type , page}
	 * @return List<LiveRecordInfo>
	 * */
	@DataSource("joint")
	public List<LiveRecordInfo> getLiveViewerByUid(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryViewerByUid
	* @Description: 查询观众跟主播的关注情况
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryViewerByUid(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryViewerLiveRecord
	* @Description: 统计想看预告的观众信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryViewerLiveRecord(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryViewerCount
	* @Description: 统计想看预告的总人数
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryViewerCount();

	/**
	 * 
	* @Title: queryAnchorAnnunciateList
	* @Description: 查询主播的通告列表/历史列表
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryAnchorAnnunciateList(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: queryAnchorAnnunciateList
	* @Description: 修改直播记录状态
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int editAnchorLiveRecordStatus(Map<Object, Object> paramMap);
	
	/**
	 * 
	* 描述: 根据记录id,查询直播记录信息
	* @return  LiveRecordInfo   返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public LiveRecordInfo queryAnchorLiveRecordById(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: queryLiveViewRecordByliverId
	* @Description: 根据记录ID 观众liver_id 查询是否观看过直播记录
	* @return LiveRecordInfo    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryLiveViewRecordByliverId(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: editLiveViewRecordStatus
	* @Description: 根据记录ID 观众liver_id 修改观众状态
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int editLiveViewRecordStatus(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: addLiveViewRecord
	* @Description: 新增用户观看记录
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int addLiveViewRecord(Map<Object, Object> paramMap);
	
	/**
	 * 
	* 描述: 根据记录ID 观众liver_id 修改观众状态
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int editLiveRecordByAnchorId(Map<Object, Object> paramMap);
	
	
	/**
	* 描述: 根据直播记录ID 观众liver_id 查询观看记录
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public LiveViewRecordInfo queryLiverViewRecord(Map<Object, Object> paramMap);

	/**
	 * 描述: Uid 查询当天已经连续看过的直播总时长
	* @return int    返回类型
	* @author
	* @throws
	 * */
	@DataSource("joint")
	public int queryLiverViewRecordViewDuration(Map<Object, Object> paramMap);
	
	/**
	* editLiveViewerRecordByLiveId
	* 批量修改该直播 正在观看人的观看直播记录的
	* @param map
	* @return int
	 */
	@DataSource("joint")
	public int editLiveViewerRecordByLiveId(Map<Object,Object> map);

	/**
	 * 描述： 修改观看记录表 “等待开播”的用户状态为 “观看直播” 
	 * @param Map<Object,Object> map
	 * @return int
	 * */
	public int editLiveViewerRecordStart(Map<Object,Object> map);

	/**
	 * 
	* @Title: queryViewerAvatar
	* @Description: 获取想看预告的观众的头像信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("burs")
	public List<Map<Object, Object>> queryViewerAvatar(@Param("viewerList")List<Map<Object, Object>> viewerList);

	/**
	 * 
	 * @Description: 修改观看结束时间
	 * @author xiaoxiong
	 * @date 2016年8月27日
	 * @version
	 */
	@DataSource("joint")
	public void editAnchorViewRecordStatus(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryRobotSet
	* @Description: 查看机器人配置
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryRobotSet();

	/**
	 * 
	* @Title: queryLiveRecordIdByAnchorId
	* @Description: 查询该主播的直播记录
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public LiveRecordInfo queryLiveRecordIdByAnchorId(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: insertLiveExitRecord
	* @Description: 记录主播正在直播异常退出信息
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void insertLiveExitRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryLiveExitRecord
	* @Description: 查询异常退出记录
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryLiveExitRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: modifyLiveExitRecord
	* @Description: 修改主播异常退出记录信息
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void modifyLiveExitRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryCurrentLiveViewRecord
	* @Description: 查询用户观众观看直播记录信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCurrentLiveViewRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryPlaybackLiveRecord
	* @Description: 查询回放记录
	* @return List<LiveRecordInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryPlaybackLiveRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: modifyPauseLiveRecord
	* @Description: 修改暂停直播的直播记录
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer modifyPauseLiveRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryReadViewerCountByLiveRecordId
	* @Description: 查询观看直播的观看人数
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryReadViewerCountByLiveRecordId(Integer liveRecordId);

	/**
	 * 
	* @Title: queryCurrentViewRecord
	* @Description: //查询观众直播正在观看记录
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCurrentViewRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryCurrentLiveViewRecordByUid
	* @Description: 查询直播用户正在观看记录信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCurrentLiveViewRecordByUid(Integer uid);

	/**
	 * 
	* @Title: modifyCurrentLiveRecord
	* @Description: 修改超过计划时间24小时的未在直播的通告为历史通告
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void modifyCurrentLiveRecord(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: modifyCurrentLiveRecord
	* @Description: 修改超过计划时间24小时的未在直播的通告为历史通告
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void modifyCurrentLiveEndRecord(Map<Object, Object> paramMap);
	
	/**
	 * 描述：获取24小时内直播结束的记录
	 * @return List<LiveRecordInfo>
	 * */
	@DataSource("joint")
	public List<LiveRecordInfo> queryLiveRecordByEnd();
	
	/**
	 * 
	* @方法名称: queryLivePersionSum
	* @描述: 获取当前所有观看直播人数(包括机器人观看)
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年9月20日
	* @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryLivePersionSum();

	/**
	 * 
	* @Title: queryAllLiverNums
	* @Description: 统计某一条直播记录的看过人数(包含机器人)
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryAllLiverNums(Integer liveRecordId);

	/**
	 * 
	* @Title: queryPlaybackSum
	* @Description: 查看看过的回放人数
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryPlaybackSum(Integer liveRecordId);

	/**
	 * 
	* @Title: modifyViewerCount
	* @Description: 同步正在直播的观看人数
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void modifyViewerCount();

	/**
	 * 
	* @Title: insertLiveRecord
	* @Description: 添加自定义直播记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer insertLiveRecord(Map<Object, Object> paramMap);
	
	
	/**
	 * 
	* @Title: queryLiverViewRecordByLiveRecordId
	* @Description: 根据直播记录ID   主播ID   获取观众
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<LiveViewRecordInfo> queryLiverViewRecordByLiveRecordId(Map<Object, Object> map);

	/**
	 * 
	* @Title: modifyCustomLiveRecord
	* @Description: 修改之前自定义直播未关闭的直播记录
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void modifyCustomLiveRecord(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @方法名称: modiflyLiveRecordVedioUrl
	* @描述: 更新直播记录视频流地址
	* @返回类型 int
	* @创建时间 2016年10月18日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int modiflyLiveRecordVedioUrl(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryCurrentLiveRecord
	* @Description: 查看正在直播的直播记录信息(包含内部测试通告)
	* @return List<LiveRecordInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryCurrentLiveRecord();
	
	/**
	 * 
	* @方法名称: queryWantGoLiveRecord
	* @描述: 想去列表
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年10月21日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryWantGoLiveRecord(Map<Object, Object> map);
	/**
	 * 
	* 描述: 查询主播的直播记录关联的粉丝券  
	* @return List<LiveRecordInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryLiveRecordFansCoupon(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 查询主播的直播记录关联的粉丝券   所有
	* @return List<LiveRecordInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<SellerCouponDetailInfo> queryLiveRecordFansCouponAll(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据下单的粉丝券金额 获取能够选择使用的粉丝券列表
	* @return List<CouponInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<CouponInfo> queryFansCouponListByAmount(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据下单的粉丝券金额 获取能够选择使用的粉丝券列表
	* @return List<CouponInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<CouponInfo> queryFansCouponListByUid(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据粉丝券ID获取粉丝券基本信息
	* @return List<CouponInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public CouponInfo queryFansCouponInfoByCouponId(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据粉丝券ID获取粉丝券基本信息
	* @return List<CouponInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<CouponInfo> queryFansCouponListByCouponId(Map<Object, Object> map);

	/**
	 * 
	* 描述: 根据粉丝券ID获取粉丝券基本信息  批量获取
	* @return List<CouponInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<CouponInfo> queryFansCouponInfoTwoByCouponId(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: querySellerLogos
	* @Description: 批量查询商家的logo图
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySellerLogos(@Param("sellerIds")List<Integer> sellerIds);

	/**
	 * 
	* @Title: queryFansCoupons
	* @Description: 批量查询粉丝卷
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryFansCoupons(@Param("liveRecordIds")List<Integer> liveRecordIds);
	
	/**
	 * 
	* 描述: 根据粉丝券ID 获取关联的抵用券
	* @param map
	* @return Map<Object, Object>  返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryUseCouponListByCid(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据粉丝券ID 获取关联的抵用券
	* @param map
	* @return Map<Object, Object>  返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryUseCouponListByUid(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据粉丝抵用券ID 获取关联的抵用券信息     map包含List
	* @param map<List<String>>
	* @return Map<Object, Object>  返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCouponByCidList(List<String> list);
	
	/**
	 * 
	* 描述: 根据 主播Id(anchorId)  查询该主播的预售  按照开播时间倒排序
	* @param Map<Object, Object>
	* @return List<Map<Object, Object>>  返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveRecordFansCouponList(Map<Object, Object> map);
	
	
	/**
	 * 
	* 描述: 根据 主播Id(anchorId)  查询该主播的预售  按照开播时间倒排序
	* @param Map<Object, Object>
	* @return List<Map<Object, Object>>  返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveRecordListByAnchorId(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryLiveBroadcastById
	* @Description: 根据广播id查询广播内容
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryLiveBroadcastById(Integer radioId);

	/**
	 * 
	* @Title: queryGroupIds
	* @Description: 批量查询直播间号(群组号)
	* @return List<String>    返回类型
	* @throws
	 */
	@DataSource("burs")
	public List<String> queryGroupIds(@Param("liveRecordList")List<LiveRecordInfo> liveRecordList);

	/**
	 * 
	* @Title: queryLiveRadioBySendTime
	* @Description: 查询指定时间的广播消息
	* @return List<Map<Object, Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveRadioBySendTime(String sendTime);
	
	/**
	 * 
	* @Title: queryCouponFansOrderByNo
	* @Description: 根据粉丝券订单标号  返回订单信息
	* @return List<Map<Object, Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCouponFansOrderByNo(Map<Object, Object> map);

	/**
	 * 
	* 描述 ：根据粉丝券订单标号  返回获取抵用券信息
	* @return List<Map<Object, Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryFansOrderUseCouponByNo(String orderNo);
	
	/**
	 * 
	* @Title: queryLiveRecordById
	* @Description: 查询直播记录详细信息
	* @return LiveRecordInfo    返回类型
	* @throws
	 */
	@DataSource("joint")
	public LiveRecordInfo queryLiveRecordById(Integer liveRecordId);

	/**
	 * 
	* @Title: queryCurrentLiveRecordByAnchorId
	* @Description: 根据主播的id查询正在直播的直播记录或者是最近结束的直播记录 
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCurrentLiveRecordByAnchorId(Integer anchorId);

	/**
	 * 
	* @Title: insertRebotLiveFocusShow
	* @Description: 批量插入机器人想看记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer insertRebotLiveFocusShow(@Param("rebotList")List<Map<Object, Object>> rebotList);
	
	/**
	 * 通过城市查询直播商铺
	 * @param city
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllByCity(Map<Object,Object> map);
	
	
	@DataSource("joint")
	LiveRecordInfo findAllBySellerId(Map<Object,Object> map);
	
	/**
	 * 通过sellerid查询正在发送粉丝卷的直播纪录
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	Map<Object,Object> findLiveRecordByFansSendStatus(Map<Object,Object> map);
	
	/**
	 * 
	 * @Description: 查询商家回放信息
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> vodListBySellerid(Map<String, Object> params);

	/**
	 * 
	* @Title: queryLiveRecordDynamic
	* @Description: 查询主播的直播动态
	* @returnList<Map<Object, Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveRecordDynamic(Map<Object, Object> paramMap);

	/**
	 * 
	 * @Description: 查询正在直播的记录
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> liveListBySellerid(Integer sellerid);
	
	/**
	 * 
	 * @Description: 查询正在直播的数量
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	@DataSource("joint")
	public int nowLiveCount(@Param("sellerid")int sellerid,@Param("type")Integer type);

	/**
	 * 查询商家直播记录
	 * @author xiaoxiong
	 * @date 2016年11月21日
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryLiveRecordInfoBySellerid(Map<String, Object> params);
	
	/**
	 * 根据直播记录查询粉丝卷ID
	 * @author xiaoxiong
	 * @date 2016年11月21日
	 */
	@DataSource("joint")
	public List<FansCouponAnchorRef> queryFansCouponAnchorRef(List<LiveRecordInfo> list);
	
	/**
	 * 
	 * @Description: 查询商家的分账信息
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	@DataSource("joint")
	public LiveSellerLedgerInfo queryLiveSellerLedgerBySellerId(Integer sellerId);
	
	
	/**
	 * 批量查询优惠卷信息
	 * @author xiaoxiong
	 * @date 2016年11月21日
	 */
	@DataSource("joint")
	public List<CouponInfo> queryCouponByCids(
			List<FansCouponAnchorRef> fCrelList);
	
	/**
	 * 
	* @Title: querySellerCover
	* @Description: 批量查询商家的封面图
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySellerCover(@Param("sellerIds")List<Integer> sellerIds);
	
	/**
	 * 查询直播记录
	 * @author xiaoxiong
	 * @date 2016年11月24日
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryLiveRecordInfoBySellerIdAndType(
			Map<String, Object> params);
	
	/**
	 * 
	* @Title: queryCheckLiveViewRecord
	* @Description: 批量查询这些用户是否观看过指定主播的直播
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCheckLiveViewRecord(Map<Object, Object> paramMap);
	/**
	 * 查询回放列表
	 * @author xiaoxiong
	 * @date 2016年12月1日
	 */
	@DataSource("joint")
	public List<Map<String, Object>> vodList(Map<String, Object> params);

	/**
	 * 
	* @Title: queryLiveRecordList
	* @Description: 查询精彩预告/直播列表(8条,规则:有预售的预告优先,优已关注的主播)
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveRecordList(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryPlayBackRecord
	* @Description: 查询热门回放列表(4条)
	* @return List<Map<Object, Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryPlayBackRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryTopicAnchor
	* @Description: 查询热门主播
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryTopicAnchor(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryTopicAnchorLiveRecord
	* @Description: 批量查询热门主播的直播记录信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryTopicAnchorLiveRecord(Map<Object, Object> paramMap);
	/**
	 * 根据商铺id和直播状态查询商铺直播列表
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryLivesBySellerIdAndType(Map<Object,Object> map);

	
	/**
	 * 
	* @Title: queryTopicAnchorLiveRecord
	* @Description: 批量查询热门主播的直播记录信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryAnchorLiveCount(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryTopicAnchorLiveRecord
	* @Description: 批量查询热门主播的直播记录信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveRecordPlayList(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryAttendLiveRecordList
	* @Description: 查询参加过的直播活动列表(购买过粉丝卷,直播中置顶，直播预售优先排序，回放记录按时间排序)
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryAttendLiveRecordList(Map<Object, Object> paramMap);
	
	@DataSource("joint")
	Map<Object,Object> findLiveRecordBySellerId(Map<Object,Object> map);

	/**
	 * 
	* @Title: queryPreLiveRecordCountBySellerId
	* @Description: 查询商家的直播预售数量(预告和直播的记录总数)
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryPreLiveRecordCountBySellerId(int sellerId);

	/**
	 * 
	* @Title: queryCurrentLiveRecordAllSum
	* @Description: 查询所有正在直播的记录总数
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryCurrentLiveRecordAllSum(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryOnlineCurrentLiveRecord
	* @Description: 查看正在直播的直播记录信息(不包含内部测试通告)
	* @return List<LiveRecordInfo>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<LiveRecordInfo> queryOnlineCurrentLiveRecord();

	/**
	 * 
	* @Title: queryCustomLiveRecordByAnchorId
	* @Description: 查询15分钟内的最新一条自定义直播记录信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCustomLiveRecordByAnchorId(int anchorId);
	
	/**
	 * 根据直播状态查询直播记录列表
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> findAllByType(@Param("type")Integer type,@Param("ids")List<Integer> ids);
	
	/**
	 * 根据店铺ID和直播状态查询是否存在 
	 * @param type
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	int findOneBySellerId(@Param("type")Integer type,@Param("sellerid")Integer sellerid);
	
	/**
	 * 通过店铺ID 分页查询直播记录
	 * @param sellerid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@DataSource("joint")
	List<LiveRecordInfo> selectLiveRecordListBySellerId(@Param("sellerid")Integer sellerid,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 根据店铺ID 和 直播状态查询一条直播记录
	 * @param sellerid
	 * @param type
	 * @return
	 */
	@DataSource("joint")
	LiveRecordInfo selectOneBySellerId(@Param("sellerid")Integer sellerid,@Param("type")Integer type);
	
	/**
	 * 统计正在直播的店铺的数量
	 * @return
	 */
	@DataSource("joint")
	int sumSellerCountsIsLive(@Param("ids")List<String> ids);

	/**
	 * 根据直播ID查询直播间是否设置展示其他商家的推荐套餐0 默认展示 1 不展示
	 * @param liveRecordId
	 * @return
	 */
	@DataSource("joint")
	int queryShowOtherSellerByLiveRecordId(@Param("liveRecordId")Integer liveRecordId);
	
	/**
	 * 记录主播退出房间累计鸟蛋 失败的记录
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	int inserLiveExitRoomFailedRecord(Map<String, String> map);

	/**
	 * 
	 * @Title:queryLiveRecordInfoById
	 * @Description: 查询直播记录信息
	 * @param recordId	直播通告id
	 * @return Map<Object,Object> 直播记录信息
	 * 2017年5月16日上午10:11:58
	 */
	@DataSource("joint")
	public Map<Object, Object> queryLiveRecordInfoById(Integer recordId);

	/**根据商家查询直播状态
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	public Map<Object, Object> findSellerLive(String sellerid);

	@DataSource("joint")
	public Integer queryRobotViewNum(Integer recordId);


	/**
	 * 查询所有正在直播的信息(金山云)
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryAllLiveRecordInfo();

	/**
	 * 修改超时直播信息(金山云)
	 */
	@DataSource("joint")
	void updateLiveById(Map<Object, Object> map);
	
	/**修改观看人数
	 * @param paramMap
	 * @return 
	 */
	@DataSource("joint")
	public int modifyLiveViewCount(Map<Object, Object> paramMap);

}
