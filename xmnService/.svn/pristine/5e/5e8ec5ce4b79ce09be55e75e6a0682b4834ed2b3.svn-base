package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.HandleCKRequest;
import com.xmniao.xmn.core.common.request.PhoneRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.xmer.entity.TopRankXmer;
import com.xmniao.xmn.core.xmer.entity.Xmer;


/**
 * 
 * 项目名称：xmnService
 * 
 * 类名称：XmerDao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-20下午4:43:56
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Repository
public interface XmerDao {
	/**
	 * 插入Xmer记录
	 * @param record
	 * @return
	 */
	@DataSource("burs")
    int insertSelective(Xmer record);
	
	/**
	 * 通过用户ID查询寻蜜客记录
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	Xmer selectByUid(Integer uid);
	
	/**
	 * 更新记录
	 * @param record
	 * @return
	 */
	@DataSource("burs")
	int updateByUid(Xmer record);
	
	/**
	 * 通过用户ID查询寻企业蜜客记录
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	Xmer selectEnXmerByUid(Integer uid);

	/**
	* @Title: queryXmerByUid
	* @Description: 查询我的小伙伴信息
	* @param map
	* @return Xmer 返回类型
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("burs")
	Xmer queryXmerByUid(Map<String,Object> map);
	
	/**
	* @Title: queryViewWechat
	* @Description: 通过用户ID查询是否支付过
	* @param map
	* @return Xmer 返回类型
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("burs")
	int queryViewWechat(Map<String,Object> map);
	
	/**
	* @Title: queryXmerByParentid
	* @Description: 查询下级与下下级寻蜜客信息
	* @param map
	* @return List<Xmer> 返回类型
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("burs")
	List<Xmer> queryXmerByParentid(Map<Object,Object> map);
	
	/**
	* @Title: selectEnXmerByUids
	* @Description: 根据uid列表查询寻蜜客的id，rtype,levels
	* @param uids
	* @return List<Xmer> 返回类型
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("burs")
	List<Xmer> selectEnXmerByUids(List<Integer> uids);
	
	/**
	* @Title: queryUidListByParentid
	* @Description: 查询下级寻蜜客uid
	* @param uid
	* @return List<Integer> 返回类型
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("burs")
	List<Integer> queryUidListByParentid(Integer uid);
	
	/**
	 * 
	* @Title: queryXmerByphone
	* @Description:根据手机号码查询寻觅可 
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("burs")
	Map<Object, Object> queryXmerByphone(PhoneRequest phoneRequest);
	
	/**
	 * 
	* @Title: addFriendApply
	* @Description: 添加
	* @return void    返回类型
	* @throws
	 */
	@DataSource("burs")
	void addFriendApply(Map<Object, Object> xmerMap);
	/**
	 * 
	* @Title: queryFriendApply
	* @Description: 查询是否发送过申请 并且正在审核的
	* @return int    返回类型
	* @throws
	 */
	@DataSource("burs")
	int queryFriendApply(Map<Object, Object> xmerMap);
	/**
	 * 
	* @Title: queryFriendship
	* @Description: 查询是否是好友
	* @return int    返回类型
	* @throws
	 */
	@DataSource("burs")
	int queryFriendship(Map<Object, Object> xmerMap);
	//查询寻蜜客头像
		@DataSource("burs")
		public String queryXmerAvatar(Integer uid);
		
		//获取寻蜜客用户资料信息
		@DataSource("burs")
		public Map<Object,Object> queryXmerInfo(Integer uid);
		
		//获取用户信息
		@DataSource("burs")
		public Map<Object,Object> queryUserInfo(Integer uid);
		
		//查询前500名订单
		@DataSource("burs")
		List<Map<Object, Object>> queryXmerPageList();
		
		@DataSource("burs")
		void modifyXmerPartnernums(HandleCKRequest hand);
		@DataSource("burs")
		int queryISQ(int uid);
		
		/**
		 * 
		* @Title: updateXmerStatus
		* @Description:寻蜜客解除dao
		* @return void    返回类型
		* @author liuzhihao
		* @throws
		 */
		@DataSource("burs")
		public Integer updateXmerStatus(Integer uid);

		/**
		 * 
		* @Title: queryBankAccountByUid
		* @Description: 查询用户绑定的银行卡号
		* @return String    返回类型R
		* @throws
		 */
		@DataSource("xmnpay")
		public String queryBankAccountByUid(Integer uid);
		
		/**
		 * 
		* @Title: queryByUidAndStatus
		* @Description: 根据用户id查询已经解除的寻蜜客信息
		* @return Xmer    返回类型
		* @throws
		 */
		@DataSource("burs")
		public Xmer queryByUidAndStatus(Integer uid);
		
		
		/**
		 * 
		* @Title: addpartner
		* @Description: 新增伙伴数量
		* @return Integer
		 */
		@DataSource("burs")
		public Integer addpartner(Integer uid);
		
		/**
		 * 
		* @Title: addSignNum
		* @Description: 新增店铺签约数量
		* @return Integer
		 */
		@DataSource("burs")
		public Integer addSignNum(Integer uid);

		/**
		 * 查询用户和xmer信息
		* @Title: selecXmerAndUrstByUid
		* @Description: 
		* @return Map<Object,Object>    返回类型
		* @throws
		 */
		@DataSource("burs")
		Map<Object, Object> selecXmerAndUrstByUid(Integer valueOf);
		
		//查询寻蜜客钱包
		@DataSource("xmnpay")
		Map<Object, Object> queryXmerWalletByUid(int uid);
		
		/**
		 * 
		* @Title: addFeedBack
		* @Description:插入一条意见反馈信息
		* @return void    返回类型
		* @author liuzhihao
		* @throws
		 */
		@DataSource("joint")
		public void addFeedBack(Map<Object,Object> map);
		
		@DataSource("joint")
		List<Map<Object, Object>> queryBannerList();
		
		/**
		 * 
		* @Title: queryXmkInfoListByPaternerNum
		* @Description: 查询寻蜜客信息已伙伴数量降序排列并且给结果集生成序列号
		* @return Map<Object,Object>    返回类型
		* @author liuzhihao
		* @throws
		 */
		@DataSource("burs")
		public List<Map<Object,Object>> queryXmkInfoListByPaternerNum(Map<Object,Object> map);
		
		/**
		 * 
		* @Title: checkPhone
		* @Description: 根据电话查询用户是否存在
		* @return Integer    返回类型
		* @author liuzhihao
		* @throws
		 */
		@DataSource("burs")
		public int checkPhone(String phone);

		/**
		 * 根据认购套数排序查询uid
		* @Title: querySaasOrderByOrderbyNums
		* @Description: 
		* @return List<Map<Object,Object>>    返回类型
		* @throws
		 */
		@DataSource("joint")
		List<Map<Object, Object>> querySaasOrderByOrderbyNums(
				Map<Object, Object> map);
		

		/**
		 * 
		* @Title: countNums
		* @Description: (这里用一句话描述这个方法的作用)
		* @return Object    返回类型
		* @author
		* @throws
		 */
		@DataSource("burs")
		Integer countPartnerNums(Integer uid);
		
		/**
		 * 
		* @Title: queryAnchorByuid
		* @Description: 查询主播信息
		* @return Map<Object,Object>    返回类型
		* @throws
		 */
		@DataSource("joint")
		Map<Object, Object> queryAnchorByuid(Integer uid);
		
		/**
		 * 修改下级寻蜜客的父ID为空
		* @Title: updateParentidByUid
		* @Description: 
		* @return void    返回类型
		* @throws
		 */
		@DataSource("burs")
		void updateParentidByUid(String uid);
		
		/**
		 * 
		* @Title: queryXmerListInfo
		* @Description: 支付系统查询寻蜜客列表信息
		* @return List<Map<Object,Object>>    返回类型
		* @throws
		 */
		@DataSource("burs")
		List<Map<Object, Object>> queryXmerListInfo(Map<Object, Object> paramMap);
		
		/**
		 * 根据伙伴uid查询伙伴的信息
		 * @Title:listFriendsInfoByIds
		 * @Description:下级和下下级朋友ids
		 * @param param 包含分页信息，好友id的 ids集合
		 * @return List<Xmer> 寻蜜客信息实体集合
		 * 2017年5月25日上午10:42:27
		 */
		@DataSource("burs")
		List<Xmer> listFriendsInfoByIds(Map<Object,Object> param);
		
		/**
		 * 根据好友id查询 好友信息 
		 * @Title:queryFriendInfoById
		 * @Description: 根据id查询寻蜜客信息
		 * @param uid 好友uid
		 * @return List<Xmer>
		 * 2017年5月25日下午2:56:43
		 */
		@DataSource("burs")
		Xmer queryFriendInfoById(Integer uid);
		
		/**
		 * 查询寻蜜客排行榜信息
		 * @Title:listXmerRank
		 * @Description: 查询所有寻蜜客的基本信息
		 * @return List<TopRankXmer>
		 * 2017年5月31日上午11:00:43
		 */
		@DataSource("burs")
		List<TopRankXmer> listXmerRank();
}