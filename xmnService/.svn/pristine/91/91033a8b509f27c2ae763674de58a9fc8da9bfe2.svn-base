package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.LiveFansRank;
import com.xmniao.xmn.core.live.entity.LiveFocusInfo;
import com.xmniao.xmn.core.live.entity.LiveGivedGiftInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiveViewRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.entity.UrsEarningsRelationInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveUserDao   
* 类描述：   直播用户通用Dao
* 创建人：yezhiyong   
* 创建时间：2016年8月9日 上午10:56:11   
* @version    
*
 */
@Repository
public interface LiveUserDao {

	
	/**
	 * 
	* @Title: queryBursEarningsRelationList
	* @Description:  根据uid查询用户各种客的 关系链
	* @return List<UrsEarningsRelationInfo>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	List<UrsEarningsRelationInfo> queryBursEarningsRelationList(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryLiveViewerInfoByUid
	* @Description:  根据uid查询用户各种客的身份
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryBursEarningsRank(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryBursEarningsRankInfoById
	* @Description:  根据id查询用户各种客的身份info
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	Map<Object, Object> queryBursEarningsRankInfoById(Integer id);
	
	/**
	 * 
	* @Title: queryLiveViewerInfoByUid
	* @Description: 根据uid查询直播观众的信息
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryLiverInfoByUid(Integer uid);
	
	/**
	 * 
	* @Title: queryLiveViewerInfoByUid
	* @Description: 根据id查询直播观众的信息
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryLiverInfoById(Integer id);

	/**
	 * 
	* @Title: queryVuidByUid
	* @Description: 根据uid查询V客 
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryVuidByUid(Integer id);

	/**
	 * 查询寻觅客关系链
	 * @param id
	 * @return
	 */
	@DataSource("burs")
	Map<Object, Object> queryRelationByUid(@Param("uid") Integer id);

	
	/**
	 * 
	* @Title: insertViewer
	* @Description: 插入直播观众信息
	* @return Integer    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	Integer insertLiver(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryAttentionAnchorByViewerId
	* @Description: 根据ViewerId 查询关注的主播id列表
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	List<LiveFocusInfo> queryAttentionAnchorByViewerId(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryAttentionAnchorByAnchorIds
	* @Description: 根据被关注人ID  查询关注被关注人列表
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	List<LiverInfo> queryAttentionAnchorByAnchorIds(@Param("list")List<Long> list);
	
	/**
	 * 
	* @Title: queryAttentionAnchorByAnchorIds
	* @Description: 根据AnchorIds list 查询关注的主播列表
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	int editLiveViewerMsgStatus(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryAnchorManagerByUid
	* @Description: 根据主播的会员ID 获取主播的管理员列表
	* @return List<LiverInfo>   返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	List<LiverInfo> queryAnchorManagerByUid(Map<Object, Object> map);

	/**
	 * 
	* @Title: modifyLiverByUid
	* @Description: 修改主播用户信息
	* @return Integer    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	Integer modifyLiverByUid(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryLiverInfo
	* @Description: 查询土豪的头像,昵称,性别,等级
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryLiversInfo(@Param("uids")List<Integer> uids);

	/**
	 * 
	* @Title: queryAnchorByLiveRecordId
	* @Description: 根据直播记录id查询主播的直播信息
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryAnchorByLiveRecordId(Integer liveRecordId);
	
	/**
	* 描述: 根据AnchorIds list 查询关注的主播列表
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	int editLiveViewerInfo(List<Map<Object, Object>> list);

	/**
	 * 
	* @Title: insertPrivateMessage
	* @Description: 更新私信记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer updateSecretMsg(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryNewMsg
	* @Description: 查询最新的私信记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer querySecretNewMsg(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: insertPublicMsg
	* @Description: 记录聊天室群组信息
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer insertPublicMsg(Map<Object, Object> paramMap);
	
	/**
	 * 描述：根据用户当前经验值 获取相应的等级
	 * @param integer 
	 * @return Map<Object,Object>    返回类型
	 * */
	@DataSource("burs")
	Map<Object, Object> queryMemberRankByExp(Integer current_expe);

	/**
	 * 描述：根据当前主播ID 获取主播基本信息
	 * @param integer 
	 * @return Map<Object,Object>    返回类型
	 * */
	@DataSource("burs")
	LiverInfo queryLiverInfoByAnchorId(Integer anchorId);
	
	/**
	 * 查询是否已关注
	 * @Description: TODO
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	@DataSource("joint")
	int queryFocusCount(Map<String, Object> param);

	@DataSource("joint")
	int queryFocusCountByUid(Map<String, Object> param);

	/**
	 * 
	 * @Description: TODO
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	@DataSource("burs")
	void clearViewDurationDay(Map<String,Object> map);
	
	/**
	 * 查主播直播未完成的记录
	 * @Description: TODO
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	@DataSource("joint")
	List<LiveRecordInfo> queryLiveRecord();
	
	
	/**
	 * 查询用户直播时长
	 * @Description: TODO
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	@DataSource("burs")
	Map<String, Object> queryLiveDurationDay(String id);

	/**
	 * 
	* @Title: insertFocusAnchor
	* @Description: 插入关注主播信息
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer insertFocusAnchor(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: insertFocusShow
	* @Description: 插入想看预告信息
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer insertFocusShow(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryFocusAnchor
	* @Description: 查询是否关注过此主播
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer queryFocusAnchor(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryFocusShow
	* @Description: 查询是否有想看预告记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer queryFocusShow(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: queryFocusShow
	* @Description: 查询是否有想看预告记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryFocusShowList(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: queryFocusShowListCount
	* @Description: 查询单个直播记录有多少人想去
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryFocusShowListCount(Map<Object, Object> paramMap);
	
	
	/**
	 * 
	 * @Description: 查询未结束的观看记录
	 * @author xiaoxiong
	 * @date 2016年8月26日
	 * @version
	 */
	@DataSource("joint")
	List<LiveViewRecordInfo> queryViewRecord();

	/**
	 * 
	 * @Description: 根据等级No查询等级所需经验
	 * @author xiaoxiong
	 * @date 2016年8月29日
	 * @version
	 */
	@DataSource("burs")
	Map<String, Object> queryLevelByRankNo(int ranNo);

	
	/**
	 * 
	 * @Description: 根据用户ID查询主播ID
	 * @author xiaoxiong
	 * @date 2016年8月29日
	 * @version
	 */
	@DataSource("joint")
	List<Integer> queryUidListByaid(int aid);

	
	/**
	 * 
	 * @Description: 根据主播ID查询uid
	 * @author xiaoxiong
	 * @date 2016年8月29日
	 * @version
	 */
	@DataSource("burs")
	List<Integer> queryLiverUidByaid(List<Integer> idList);
	
	/**
	 * 
	* @Title: queryLiverUids
	* @Description: 查询直播用户的会员uid
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryLiverUids(@Param("anchorIds")List<Integer> anchorIds);

	/**
	 * 
	* @Title: queryLiveViewRecordById
	* @Description: 查询某一个观众观看某一场直播的观看信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryLiveViewRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: modifyLiveViewRecord
	* @Description: 修改某一场观众观看直播记录信息
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer modifyLiveViewRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: deleteFocusAnchor
	* @Description: 删除关注主播信息(取消关注)
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer deleteFocusAnchor(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryAnchorByPhone
	* @Description: 根据手机号码查询主播信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryAnchorByPhone(String phone);

	/**
	 * 
	* @Title: queryAnchorByGroupId
	* @Description: 根据群组号查询主播信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryAnchorByGroupId(String groupId);

	/**
	 * 
	* @Title: queryLiveByPhone
	* @Description: 根据手机号码(登录账号)直播用户信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryLiveByPhone(String phone);

	/**
	 * 
	* @Title: queryPrivilege
	* @Description: 查询直播房间权限
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryPrivilege(Integer type);
	
	
	@DataSource("joint")
	List<Integer> queryLiveFocusShowByRid(Integer rid);
	
	/**
	 * 
	* 描述: 查询土豪榜  查询出内部需要过滤的账号 排除土豪榜
	* @return List<LiverInfo> 返回类型
	* @throws
	 */
	@DataSource("burs")
	List<LiverInfo> queryLiverInfosByIsinside();

	/**
	 * isInside = 1 && sign_type = 2 的账号排除
	 * @return
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryLiverInfosByIsinsideAndSignType();

	/**
	 * 
	* @Title: insertSecretMsg
	* @Description: 插入私信信息
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer insertSecretMsg(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: updateLiverCurrentExpe
	* @Description: 修改直播用户当前经验值
	* @return void    返回类型
	* @throws
	 */
	@DataSource("burs")
	void updateLiverCurrentExpe(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryliverInfoList
	* @Description: 查询所有的直播用户信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryliverInfoList();
	
	/**
	 * 
	 * @Description: 查询主播基本信息
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	@DataSource("burs")
	LiverInfo queryLiverByUid(Long uid);

	/**
	 * 
	* @Title: querySameFocusCount
	* @Description: 查询关注相同的好友数量
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer querySameFocusCount(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: querySameFocusListCount
	* @Description: 批量查询有共同关注的好友数量
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> querySameFocusListCount(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryBankInfoById
	* @Description: 查询银行卡信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryBankInfoById(Integer bankListId);

	/**
	 * 
	* @Title: queryBankList
	* @Description: 查询银行卡所属银行列表信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryBankList();
	
	/**
	 * 检查两个用户之间是否关注
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int checkTwoUidIsFollow(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryBankList
	* @Description: 查询银行卡所属银行列表信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryLiveInfoByUidList(List<Object> list);
	
	/**
	 * 
	* @Title: queryLiveViewerInfoByUid
	* @Description: 根据uid查询直播观众的信息
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryLiverInfoByUidList(List<Map<Object, Object>> list);

	
	/**
	 * 
	* 描述: 根据主播ID查询主播的收入记录
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	List<LiveGivedGiftInfo> queryLiverIncomeRecord(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据主播ID查询主播的收入记录
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	LiveGivedGiftInfo queryLiverIncomeRecordAmount(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据主播ID查询主播的收入记录  总鸟蛋
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryLiverInfoByIdList(List<Integer> list);
	
	
	/**
	 * 
	* 描述: 根据主播UID 批量查询主播列表
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiveAnchorFocusList(Map<Object, Object> map);
	
	/**
	 * 描述:根据用户ID查询用户关注的用户
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryUserFocusListByUid(Integer uid);

	/**
	 * 
	* @Title: queryLiveAnchorImageByUid
	* @Description: 根据uid查询主播的相册信息
	* @return List<Map<Object, Object>>    返回类型
	* @throws
	 */
	@DataSource("burs")
	List<Map<Object, Object>> queryLiveAnchorImageByUid(Map<Object, Object> paramMap);
	
	/**
	 * 查询我关注的好友消费用一家商铺的好友用户ID
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Integer> findFollowUidBySellerId(Map<Object,Object> map);
	
	/**
	 * 根据UID查询UID关注的UID
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Integer> findFollowUidCustomSellerIdByUid(Integer uid);
	
	@DataSource("burs")
	List<Map<Object, Object>> queryMakeHaoFriend(@Param("uid")String uid);

	/**
	 * 
	* @Title: queryMinLiveFansRank
	* @Description: 查询等级表,最低等级的数据
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryMinLiveFansRank();
	
	/**
	 * 查询用户信息
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	Map<Object,Object> findUserInfoByUid(@Param("uid")Integer uid);

	/**
	 * 查询等级图标
	 * @author xiaoxiong
	 * @date 2017年1月4日
	 */
	@DataSource("burs")
	List<LiveFansRank> queryLiveFansRankList();
	
	/**
	 * 查询用户最大充值金额
	 * @author xiaoxiong
	 * @date 2017年1月10日
	 */
	@DataSource("joint")
	List<Map<String,Object>> queryMaxPamentList(List<Object> list);
	
	/**
	 * 批量查询主播用户的基本信息
	 * @param uids
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllByUid(@Param("uids")List<Integer> uids);
	
	/**
	 * 查询两个uid之间的关系
	 * @param uid 当前用户的UID
	 * @param fuid 被检测是否关注的用户的UID
	 * @return
	 */
	@DataSource("burs")
	int queryTwoUidIsRelation(@Param("uid")String uid,@Param("fuid")String fuid);
	
	/**
	 * 批量查询我的壕友信息
	 * @param uids
	 * @return
	 */
	@DataSource("burs")
	List<Map<Object,Object>> findMyFriendsByUid(@Param("uids")List<String> uids);
	
	/**
	 * 查询用户的最大充值金额
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	Double findMaxPaymentByUid(@Param("uid") String uid);

	/**查询用户当前城市，商圈，分类   充值过的店家专享卡
	 * @param conditionMap
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryChargeDebitCards(Map<Object, Object> conditionMap);

	/**
	 * 批量查询用户基本信息（主播和用户）
	 * @param ids
	 * @return
	 */
	@DataSource("burs")
	List<Map<Object,Object>> findAllByIdList(@Param("ids")List<Object> ids);


	/**
	 * 查询内部测试主播
	 * @param paramMap
	 * @return
	 */
	@DataSource("burs")
	List<Map<Object, Object>> findInsideTestAnchors(Map<Object, Object> paramMap);

	
	/**
	 * 
	 * @Title:queryAnchorInfoByAnchorId
	 * @Description:根据主播id查询直播的详细信息
	 * @param anchorId 主播id
	 * @return LiverInfo 用户的基本信息
	 * 2017年4月6日上午10:58:09
	 */
	@DataSource("burs")
	LiverInfo queryAnchorInfoByAnchorId(Integer anchorId);

	/**
	 * 查询主播的签约类型
	 * @Title:querySignType
	 * @Description:根据uid查询主播签约类型
	 * @param uid 直播uid
	 * @return Integer	signType
	 * 2017年5月16日上午9:07:38
	 */
	@DataSource("burs")
	Integer querySignType(String uid);

	/**
	 * 根据城市Id获取主播Id
	 * @param cityId
	 * @return
	 */
	@DataSource("burs")
	List<Map<Object, Object>> selectAnchorByCityId(@Param("cityId") Integer cityId);
	
	/**
	 * 获取报名主播信息 用于验证是否可用开播
	 * @param cityId
	 * @return
	 */
	@DataSource("joint")
	int queryVstarEnrollInfo(Map<Object, Object> map);

	/**
	 * 根据主播id查询 主播群组号
	 * @Title:queryGroupIdByAnchorId
	 * @Description: 根据主播id查询群组号
	 * @param anchor_id 主播id
	 * @return String groupId
	 * 2017年6月22日上午11:32:27
	 */
	@DataSource("burs")
	String queryGroupIdByAnchorId(String anchor_id);

	/**大赛报名成功后修改用户为大赛选手
	 * @param userMap
	 */
	@DataSource("burs")
	public void updateVstarType(Map<Object, Object> userMap);
	
	
}
