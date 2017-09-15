package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface MakeHaoDao {
	
	
	/**
	 * 壕赚排名
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	@DataSource("joint")
	List<Map<String, Object>> getMakeHaoListOrder(Map<String, Object> params);
	
	/**
	 * 壕赚排名
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	@DataSource("joint")
	List<Map<String, Object>> makeHaoRank();
	
	/**
	 * 获取用户充值金额
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	@DataSource("joint")
	Double getPayAmount(int uid);
	
	/**
	 * 获取主播打赏总额
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	@DataSource("joint")
	Double getRewardAmount(int anchorId);

	/**
	 * 查询我的壕友数（下级下下级）
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	@DataSource("burs")
	int getMakeHaoFriendCount(int uid);
	
	
	@DataSource("joint")
	List<Map<String, Object>> getLedgerMakeHaoFriend(Map<String,Object> params);
	
	/**
	 * 查询我的壕友需要排除 有贡献的壕友
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	@DataSource("burs")
	List<Map<String,Object>> queryLiverMakeHaoFriend(Map<String, Object> params);
	
	/**
	 * 获取预计收入
	 * @author xiaoxiong
	 * @date 2016年12月27日
	 */
	@DataSource("joint")
	Map<String, Object> getGaveAmount(int uid);

	/**
	 * 查询用户的直接好友
	 *等级关系:用户的下级即是直接好友
	 *比如:自然人A(即一个没有成为别人下级也没有成为别人上级的人),发展一个下级B那么A的直接好友就是B
	 *关系链:比如 A=12345 B=12346,他们在关系链中只是这样的关系 如 ''',0000000012345,000000001246
	 *比如 自然人A作为登录用户,那么000000001245,后面匹配的字符串均为自然人A的下级,即直接好友
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	List<String> fiadMyDirectFriends(@Param("uid")String uid,@Param("page")Integer page,@Param("pageSize")Integer pageSize);

	/**
	 * 统计我的壕友数量
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	int sumMyFriendCounts(@Param("uid")String uid);
	
	/**
	 * 根据充值等级查询比例最大的贡献比例
	 * @param rankNo
	 * @return
	 */
	@DataSource("burs")
	Map<Object,Object> selectContributionRatioByRankNo(@Param("rankNo")String rankNo);
}
