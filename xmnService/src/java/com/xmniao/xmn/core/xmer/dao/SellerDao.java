package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.catehome.BestForYouRequest;
import com.xmniao.xmn.core.seller.entity.ActivityFullreductionInfo;
import com.xmniao.xmn.core.seller.entity.Redpacket;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerCouponDetail;
import com.xmniao.xmn.core.seller.entity.SellerDetailed;
import com.xmniao.xmn.core.seller.entity.SellerLandMark;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.util.dataSource.DataSource;
/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SellerDao   
* 类描述：   店铺信息接口Dao
* 创建人：yezhiyong   
* 创建时间：2016年5月23日 下午2:54:03   
* @version    
*
 */
@Repository
public interface SellerDao {

	/**
	 * 
	* @Title: querySellerList
	* @Description: 查询用户签约的店铺列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySellerList(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryXmerSellerNums
	* @Description: 查询寻
	* @return int    返回类型
	* @throws
	 */
	@DataSource("joint")
	public int queryXmerSellerNums(Integer uid);
	
	/**
	 * 
	 * @Description: 查询商家基本信息
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	@DataSource("joint")
	public Seller querySellerBySellerid(Long sellerid);

	/**
	 * 查询商店基本信息
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	Seller querySellerInfoBySellerid(Long sellerid);
	
	@DataSource("joint")
	public List<SellerPic> querySellerPicBySelleridAndType(
			Map<String, Object> params);
	
	/**
	 * 
	 * @Description: 查询商家详细信息
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	@DataSource("joint")
	public SellerDetailed querySellerDetailBySellerid(Integer sellerid);
	
	/**
	 * 
	 * @Description: 查寻商家经纬度
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	@DataSource("joint")
	public SellerLandMark querySellerLandMarkBySellerid(Integer sellerid);
	/**
	* @Title: querySellerBusiness
	* @Description: 批量查询店铺所在的商圈
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySellerBusiness(@Param("sellerIds")List<Integer> sellerIds);

	/**
	* @Title: querySellerInfo
	* @Description: 批量查询店铺信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySellerInfo(@Param("sellerIds")List<Integer> sellerIds);
	
	/**
	 * @Description: 查询是否有该用户浏览该店铺的记录
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	@DataSource("joint")
	public int querySellerBrowsedCount(@Param("uid")String uid, @Param("sellerid")Integer sellerid);
	
	/**
	 * @Description: 用户浏览店铺次数加1
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	@DataSource("joint")
	public void updateSellerBrowSed(Map<String, Object> params);
	
	/**
	 * 
	 * @Description: 新增用户浏览记录
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	@DataSource("joint")
	public void insertSellerBrowsed(Map<String, Object> params);
	
	/**
	 * 
	 * @Description: 查询用户优惠卷信息
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	@DataSource("joint")
	public List<SellerCouponDetail> querySellerCoupon(Map<String, Object> params);
	
	/**
	 * 查询红包信息
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	@DataSource("joint")
	public List<Redpacket> querySellerRedPacket(Map<String, Object> params);

	/**
	 * 
	* @Title: queryCollectSellerCount
	* @Description: 批量查询有共同收藏的店铺数量
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCollectSellerCount(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: queryViewSellerCount
	* @Description: 批量查询有共同预览的店铺数量
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryViewSellerCount(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryFirstCollectSellerByUid
	* @Description: 查询第一次收藏的商户封面图
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryFirstCollectSellerByUid(Integer uid);

	/**
	 * 查询预售卷信息
	 * @author xiaoxiong
	 * @date 2016年11月21日
	 */
	@DataSource("joint")
	public List<Map<String,Object>> queryCouponBySellerid(Map<String, Object> params);
	/**
	 * 查询商家所有活动
	 * @author xiaoxiong
	 * @date 2016年11月21日
	 */
	@DataSource("joint")
	public List<Map<String, Object>> queryActivityList(Integer sellerid);
	
	/**
	 * 关键字查询
	 * @author xiaoxiong
	 * @date 2016年11月22日
	 */
	@DataSource("joint")
	public List<Map<String,Object>> search(Map<String, Object> params);
	

	/**
	 * 根据商家ID查询商家满减活动表信息
	 * */
	@DataSource("joint")
	public List<ActivityFullreductionInfo> querySellerSaleBySellerId(Map<Object, Object> map);
	
	/**
	 * 根据商家ID查询商家满减活动表信息
	 * */
	@DataSource("joint")
	public Redpacket querySellerRedPacketInfo(Map<Object, Object> map);
	
	/**
	 * 根据商家ID查询商家满减活动表信息
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryUserRedPacketRecord(String orderNo);
	
	/**
	 * 根据商家ID查询商家满减活动表信息
	 * */
	@DataSource("joint")
	public int querySellerRedPacketBySellerId(Integer sellerId);
	
	/**
	 * 查询附近商家
	 * @author xiaoxiong
	 * @date 2016年12月2日
	 */
	@DataSource("joint")
	public List<Map<String, Object>> querySellerNearList(
			Map<String, Object> params);
	
	/**
	 * 查询秒杀活动
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	@DataSource("joint")
	public List<SellerCouponDetail> queryActivityKill(Map<String, Object> params);
	
	/**
	 * 查询商家是否参与储值卡活动 包含总店 或 区域是否包含当前店铺
	 * 如果是连锁店 或在 区域代理 则取连锁店 或者区域代理的ID
	 * @author yhl
	 * @date 2017年2月16日17:48:24
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCardSellerBySellerId(@Param("sellerid") Integer sellerid);
	
	/**
	 * 查询 用户在店有无充值过储值卡 在获取额度
	 * @author yhl
	 * @date 2017年2月16日17:48:24
	 */
	@DataSource("joint")
	public Map<Object, Object> queryUserCardSellerInfo(Map<Object, Object> map);
	
	/**
	 * 通过关键词模糊匹配与套餐相关的店铺信息
	 * @param uid
	 * @param lat
	 * @param lon
	 * @param cityId
	 * @param keyword
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> searchSellersByKeyword(@Param("uid")String uid,@Param("lat")Double lat,@Param("lon")Double lon,@Param("cityId")Integer cityId,
		@Param("keyword")String keyword,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
	
	/**
	 * 模糊匹配不到数据时,补充的数据
	 * @param nowDate
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> replenSellersIsNotCombo(@Param("nowDate")String nowDate);
	
	/**
	 * 根据店铺ID查询店铺标签
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	List<String> findSellerTagNameBySellerId(@Param("tags")List<String> tags);
	
	
	/**
	 * 通过店铺ID查询与之关联的充值卡店铺
	 * @param sellerid
	 * @param sellerType
	 * @return
	 */
	@DataSource("joint")
	String selectSubSellersBySellerId(@Param("sellerid")String sellerid,@Param("sellerType")Integer sellerType);
	
	
	/**
	 * 批量查询商铺信息
	 * @param sellerids
	 * @param uid
	 * @param lon
	 * @param lat
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> selectSellerInfoBySellerIds(@Param("sellerids")List<String> sellerids,@Param("uid")String uid,@Param("lon")Double lon,@Param("lat")Double lat,@Param("page")Integer page,@Param("pageSize")Integer pageSize);

	/**
	 * 查询店铺图片
	 * @param sellerid
	 * @param type
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> selectSellerPicBySellerId(@Param("sellerid")String sellerid,@Param("type")Integer type);

	/**
	 * 查询商铺详情
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	Map<Object,Object> selectByPrimaryKey(@Param("sellerid")String sellerid);

	/**
	 * 
	 * @Title:queryNewSellerList
	 * @Description:V3.6.0版本查询 签约店铺列表
	 * @param map
	 * @return List<Map<Object,Object>>
	 * 2017年5月2日下午3:44:55
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryNewSellerList(Map<Object, Object> map);

	/**
	 * 
	 * @Title:queryReadyNumByUids
	 * @Description:查询 寻蜜客关系链中 已上线的签约店铺数量 
	 * @param uidList 寻蜜客关系链uid集合  自己，下级和 下下级
	 * @return Integer 已上线店铺数量
	 * 2017年5月3日上午10:33:52
	 */
	@DataSource("joint")
	public Integer queryReadyNumByUids(@Param("uidList") List<Integer> uidList, @Param("uid") Integer uid);

	/**
	 * 
	 * @Title:queryWaitNumByUid
	 * @Description:根据uid查询 待上线店铺数量
	 * @param uid
	 * @return Integer
	 * 2017年5月3日上午10:41:32
	 */
	@DataSource("joint")
	public Integer queryWaitNumByUid(Integer uid);

	/**
	 * 
	 * @Title:queryDraftNumByUid
	 * @Description:根据uid查询 草稿箱中店铺数量
	 * @param uid
	 * @return Integer
	 * 2017年5月3日上午10:44:44
	 */
	@DataSource("joint")
	public Integer queryDraftNumByUid(Integer uid);

	/**
	 * 根据店铺id查询店铺名称
	 * @Title:querySellerName
	 * @Description:查店铺名称
	 * @param sellerId 店铺id
	 * @return String 店铺名称
	 * 2017年5月15日下午9:03:22
	 */
	@DataSource("joint")
	public String querySellerName(Integer sellerId);

	/**查询城市是否开通本地生活业务
	 * @param cityid
	 * @return
	 */
	@DataSource("joint")
	public Integer countLocalSeller(Integer cityid);

	
	/**查询首页当前城市所有有限店铺 有套餐，直播优先显示
	 * @param paraMap
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryHomeSeller(Map<Object, Object> paraMap);

	/**查询当前城市店铺的消费人数 降序
	 * @param paramMap
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryConsumNumberByLocalCity(Map<Object, Object> paramMap);

	
	/**查询网红头条点评商铺
	 * @param paramMap
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> findTopCommentSellers(Map<Object, Object> paramMap);

	/**首页必买清单title
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> findMustBuyTitle();
	/**首页必买清单商品codes
	 * @return
	 */
	@DataSource("joint")
	public List<Integer> findMustBuyCode(Map<Object,Object>paraMap);

	/**根据分类查询为你优选店铺
	 * @param paraMap
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryHomeSellerByClassify(Map<Object, Object> paraMap);

	/**为你优选后台设置分类
	 * @param paraMap
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> findBestForYouTitle(Map<Object, Object> paraMap);


	/**根据分类id查询分类名称（商品标签)
	 * @param tabLabelids
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> findClassifyByIDs(String[] tabLabelids);

	/**网红点评 根据商家获取主播头像至多三个
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	public List<String> findLiverBysellerid(Integer sellerid);

	/**查询商家的
	 * @param signSellerids
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> findHomeCommentBySignSelelrids(List<Integer> signSellerids);

	/**首页必买清单title
	 * @return
	 */@DataSource("joint")
	public List<Integer> findHomeMustBuyTitle();

	/**
	 * 查询所有连锁店铺
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> findAllMultipleSeller();
}
