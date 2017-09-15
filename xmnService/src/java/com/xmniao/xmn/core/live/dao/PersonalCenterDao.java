/**
 * 2016年8月10日 下午3:41:00
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PersonalCenterDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月10日 下午3:41:00
 * @version
 */
@Repository
public interface PersonalCenterDao {
	
	
	
	/**
	 * 
	* @Title: queryLiverPersonByUid
	* @Description: 根据uid查询直播观众及主播的个人信息
	* @return Map<Object,Object>
	 */
	@DataSource("burs")
	public Map<Object, Object>  queryLiverPersonByUid(int uid);
	
	
	
	/**
	 * 
	* @Title: queryLiverPersonById
	* @Description: 根据id查询直播观众及主播的个人信息 
	* @return Map<Object,Object>
	 */
	@DataSource("burs")
	public Map<Object, Object>  queryLiverPersonById(int id);
		
	
	/**
	 * 
	* @Title: queryLiverPersonByListId
	* @Description: 根据集合ID查询用户信息
	* @return List<Map<Object,Object>>
	 */
	@DataSource("burs")
	public List<Map<Object, Object>> queryLiverPersonByListId(List<String> list);
	
	/**
	 * 
	* @Title: queryLiverPersonByListId
	* @Description: 根据集合ID查询用户信息
	* @return List<Map<Object,Object>>
	 */
	@DataSource("burs")
	public List<Map<Object, Object>> queryLiverPersonByListUid(List<String> list);
	/**
	 * 
	* @Title: queryLiveWalletInfo
	* @Description: 根据uid查询直播观众的财产信息
	* @return Map<Object,Object>
	 */
	@DataSource("xmnpay")
	public Map<Object, Object> queryLiveWalletInfo(int uid);
	
	/**
	 * 
	* @Title: queryLiveRecordNum
	* @Description: 根据用户id查看直播记录数跟通告条数 
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryLiveRecordNum(int anchorId);
	
	/**
	 * 
	* @Title: queryliveIntegral
	* @Description: 根据用户uid查询积分对象
	* @return Map<Object,Object>
	 */
	@DataSource("xmnpay")
	public Map<Object,Object> queryliveIntegral(int uid);
	
	/**
	 * 
	* @Title: queryCouponNum
	* @Description:  根据用户uid查询优惠券总数
	* @return Integer
	 */
	@DataSource("joint")
	public Integer queryCouponNum(int uid);
	
	/**
	 * 
	* @Title: queryAttentionAnchor
	* @Description: 根据uid查询关注人员
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryAttentionAnchor(Map<Object,Object> map);
	/**
	 * 
	* @Title: queryBlackPerson
	* @Description: 查询黑名单人员 集合
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryBlackPerson(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryBlackInfoBasic
	* @Description: 查询黑名单人员的基本信息
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object,Object> queryBlackInfoBasic(Map<Object,Object> map);
	
	
	/**
	 * 
	* @Title: deleteBlackInfo
	* @Description:  删除取消黑名单
	* @return Integer
	 */
	@DataSource("joint")
	public Integer deleteBlackInfo(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: addBlackInfo
	* @Description: 新增黑名单
	* @return Object
	 */
	@DataSource("joint")
	public Integer addBlackInfo(Map<Object,Object> map);
	
	/**
	 * 
	* @方法名称: queryLivePersonByListName
	* @描述: 搜索主播
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年10月17日
	* @param map
	* @return
	 */
	@DataSource("burs")
	public List<Map<Object,Object>> queryLivePersonByListName(Map<Object,Object> map);

	/**
	 * 
	* @Title: queryLiveBlack
	* @Description: 查询是否存在直播拉黑记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryLiveBlack(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryFansList
	* @Description: 查询主播与查看用户的粉丝列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryFansList(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryFocusList
	* @Description: 查询主播与查看用户的关注列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryFocusList(Map<Object, Object> paramMap);

	/**
	 * 描述：查询个人优惠券基本列表 包含：平台券，粉丝券，商家券
	 * @param Map<object,object>
	 * @return List<CouponInfo>
	 * */
	@DataSource("joint")
	public List<CouponInfo> queryUserCouponList(Map<Object,Object> map);
	
	/**
	 * 描述：查询个人粉丝券列表  
	 * @param Map<object,object>
	 * @return List<CouponInfo>
	 * */
	@DataSource("joint")
	public List<CouponInfo> queryUserFansCouponList(Map<Object,Object> map);
	
	/**
	 * 描述：根据商家优惠券发送ID 获取实体  
	 * @param Integer cuid
	 * @return CouponInfo
	 * */
	@DataSource("joint")
	public CouponInfo queryUserCoponByCuid(Integer cuid);
	
	/**
	 * 描述：根据商家优惠券发送ID 获取实体  
	 * @param Integer cuid
	 * @return CouponInfo
	 * */
	@DataSource("joint")
	public CouponInfo queryFansCoponByCdid(Integer cdid);

	/**
	 * 
	* @Title: queryUnusedFansCouponByUid
	* @Description: 查询用户购买的未使用的粉丝卷
	* @return List<Integer>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryUnusedFansCouponByUid(int uid);

	/**
	 * 
	* @Title: queryLiveAndCustomerSeller
	* @Description: 查询主播直播过跟消费过的店铺信息(按照消费时间排序)
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveAndCustomerSeller(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryCustomerSellerList
	* @Description: 查询用户的消费店铺信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCustomerSellerList(Map<Object, Object> paramMap);
	
}
