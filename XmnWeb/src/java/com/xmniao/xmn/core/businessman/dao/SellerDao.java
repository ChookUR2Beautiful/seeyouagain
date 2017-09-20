package com.xmniao.xmn.core.businessman.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.AllianceRelation;
import com.xmniao.xmn.core.businessman.entity.AllianceShop;
import com.xmniao.xmn.core.businessman.entity.MSeller;
import com.xmniao.xmn.core.businessman.entity.TLiveSellerLedger;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerTraderRef;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerDao
 * 
 * @类描述： 商家(商户)
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时22分21秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerDao extends BaseDao<TSeller> {

	/**
	 * 获取客户信息列表
	 */
	@DataSource("slave")
	public List<TSeller> getActivitySentList(TSeller seller);

	/**
	 * 获取客户信息列表sellerCount
	 */
	@DataSource("slave")
	public Long getActivitySentCount(TSeller seller);

	/**
	 * 获取客户信息列表
	 */
	@DataSource("slave")
	public List<TSeller> getSellerList(TSeller seller);

	/**
	 * 获取客户信息列表sellerCount
	 */
	@DataSource("slave")
	public Long sellerCount(TSeller seller);
	
	/**
	 * 获取客户信息列表
	 */
	@DataSource("slave")
	public List<TSeller> getSellerListByAlliance(AllianceShop allianceShop);

	/**
	 * 获取客户信息列表sellerCount
	 */
	@DataSource("slave")
	public Long getSellerListByAllianceCount(AllianceShop allianceShop);

	/**
	 * 批量跟新商家状态
	 */
	public Integer updateSellerStatus(TSeller seller);

	/**
	 * 获取商户评论列表
	 * 
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	public List<TSeller> getCommnetSellerList(TSeller seller);

	/**
	 * 获取商户评论列表条数
	 * 
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	public Long getCommnetSellerCount(TSeller seller);

	/**
	 * 获取分店列表
	 */
	@DataSource("slave")
	public List<TSeller> getsplitShopList(TSeller seller);

	/**
	 * 获取分店cout
	 */
	@DataSource("slave")
	public Long splitShopCount(TSeller seller);

	/**
	 * 下拉框查寻所有总店
	 */
	@DataSource("slave")
	public List<TSeller> getFatherSeller(TSeller seller);

	/**
	 * 下拉框查寻商家编号和名称
	 */
	@DataSource("slave")
	public List<TSeller> getSellerIdAndSellerName(TSeller seller);
	
	/**
	 * 查询已上线的签约商家
	 */
	@DataSource("slave")
	public List<TSeller>  getSellerIdAndName(TSeller seller);
	/**
	 * 根据商家id查询服务员
	 */
	public List<TSeller> getWaiterList(int sellerid);
	/**
	 * 批量上线
	 */
	public Integer beachOnLine(TSeller seller);

	/**
	 * 批量关联子店
	 */
	public Integer beachRelationShop(TSeller seller);

	/**
	 * 根据fatheri清空对应的fatherid值
	 */
	public Integer deleteRelationShop(Long fatherid);

	/**
	 * 根据fatherid查询对应的商家信息
	 */
	@DataSource("slave")
	public List<TSeller> findSellerByFatherid(TSeller seller);

	/**
	 * 根据联盟商店查询查询对应的商家信息
	 */
	@DataSource("slave")
	public List<TSeller> findSellerByAllianceShopId(@Param("id") String id);

	/**
	 * 根据联盟商店查询查询对应的商家信息
	 */
	@DataSource("slave")
	public List<TSeller> getAllianceSeller(Map<String, Object> map);

	/**
	 * 根据联盟商店查询查询对应的商家信息数量
	 */
	@DataSource("slave")
	public Long getAllianceSellerCount(Map<String, Object> map);

	/**
	 * 添加联盟店和商家关联表信息
	 * 
	 * @param allianceRelation
	 */
	public void addAllianceRelation(AllianceRelation allianceRelation);

	/**
	 * 查询联盟店和商家关联表信息
	 * 
	 * @param allianceShop
	 * @return
	 */
	@DataSource("slave")
	public TSeller getAllianceRelation(Long sellerid);

	/**
	 * 根据商家id更新商家对应的联盟店信息
	 * 
	 * @param allianceShop
	 * @return
	 */
	public Integer updateAllianceRelation(TSeller seller);

	/**
	 * 根据区域查商家
	 * 
	 * @param areas
	 *            区域数组
	 * @return
	 */
	@DataSource("slave")
	public List<TSeller> getSellerByAreas(String[] areas);

	/**
	 * 导出商家信息
	 */
	@DataSource("slave")
	public List<TSeller> exportSellerList(TSeller seller);

	/**
	 * 查询站内消息商家
	 * 
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	public List<TSeller> getSellerMsg(TSeller seller);

	/**
	 * 查询站内消息商家数量
	 */
	@DataSource("slave")
	public Long getSellerMsgCount(TSeller seller);

	/**
	 * 根据sellerId关联查询商家表及商户账号表
	 */
	public List<TSeller> gettSellerAndSellerAccount(TSeller seller);

	/**
	 * 根据手机号码查询指定商检编号
	 * 
	 * @param PhoneId
	 * @return
	 */
	@DataSource("slave")
	public String getPhoneIdBySellerId(@Param("phoneid") String PhoneId);

	/**
	 * 根据sellerId关联查询商家表及商户折扣设置表
	 * 
	 * @param sellert
	 * @return
	 */
	@DataSource("slave")
	public List<TSeller> getSellerAndSellerAgio(TSeller seller);

	/**
	 * 根据sellerId查询商家等级
	 * 
	 * @param 数组
	 *            {商家比那好}
	 * @return 符合条件的商家编号
	 */
	@DataSource("slave")
	List<Integer> getSellerGradeBySellerid(Object[] objs);

	@DataSource("slave")
	public List<TSeller> getSellerAndSellerDetailed(TSeller seller);

	/**
	 * 取得优惠券的商家
	 * 
	 * @param seller
	 * @return
	 */
	public List<TSeller> getSellerByCidAndSeller(TSeller seller);

	public Long countOfGetSellerByCidAndSeller(TSeller seller);

	/**
	 * 获取存入mongodb中的商家对象属性
	 * 
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	MSeller getMSellerInfo(Integer id);

	/**
	 * @author dong'jietao
	 * @date 2015年6月13日 下午1:58:05
	 * @TODO 下拉框
	 * @param seller
	 * @return
	 */
	public  List<TSeller>  getSelect(TSeller seller);

	/**
	 * 获取品牌店的子店
	 * 
	 * @param seller
	 * @return
	 */
	public List<TSeller> getBrandSubSeller(TSeller seller);

	/**
	 * @param seller
	 * @return
	 */
	public Long countOfBrandSubSeller(TSeller seller);

	/**
	 * 添加品牌店
	 * 
	 * @param seller
	 */
	public void addBrandSeller(TSeller seller);

	/**
	 * 删除品牌店
	 * 
	 * @param seller
	 */
	public void deleteBrandSeller(TSeller seller);

	/**
	 * @param sellerid
	 * @return
	 */
	public List<TSellerTraderRef> getTraderRefsBySellerId(Integer sellerid);

	/**
	 * @param tSellerTraderRef
	 */
	public void addTSellerTraderRef(TSellerTraderRef tSellerTraderRef);

	/**
	 * @param sellerid
	 */
	public void deleteTSellerTraderRefBySellerid(Integer sellerid);

	
	public List<TSeller> getChildShopList(TSeller tSeller);
	
	public Long getChildShopCount(TSeller tSeller);
	
	public List<Map<String,Object>> getSellerName(@Param("idList") List<String> idList);
	
	/**
	 * 通过商家账号查询对应商家
	 */
	public TSeller getSellerBySellerAccount(String sellerAccount);
	
	/**
	 * 批量更新商家最新更新日期
	 */
	public int updateSellerDate(TSeller tSeller);
	/**
	 * @Title: getSellerBySellerIdAndSellerAddress
	 * @Description: 条件查询一个商家信息
	 * @return:TSeller
	 * @author:lifeng
	 * @time:2016年5月28日下午2:27:05
	 */
	public TSeller getSellerByWhere(TSeller seller);
	
	/**
	 * 获取商家区域
	 */
	public Map<String,String> getSellerArea(String areaId);

	/**
	 * 方法描述：获取商家信息包括经纬度 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-3上午11:14:46 <br/>
	 * @param sellerid
	 * @return
	 */
	public TSeller getSellerLandmarkInfoById(Long sellerid);

	
	/**
	 * 获取商家物料发送状态
	 * @param sellerid
	 * @param uid
	 * @return
	 */
//	public Integer getMaterialStatus(@Param("sellerid") Integer sellerid ,@Param("uid") Integer uid);
	
	/**
	 * 修改物料发送状态
	 * @param sellerid
	 * @param uid
	 * @return
	 */
//	public Integer changeMaterielStatus(@Param("sellerid") String sellerid,@Param("uid") String uid);
	
	/**
	 * 
	 * 方法描述：添加商家直播分账设置 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2016年11月21日下午2:35:18 <br/>
	 * @param liveLedger
	 * @return
	 */
	public int addSellerLiveLedger(TLiveSellerLedger liveLedger);
	
	/**
	 * 
	 * 方法描述：修改商家直播分账设置 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2016年11月21日下午2:35:18 <br/>
	 * @param liveLedger
	 * @return
	 */
	public int updateSellerLiveLedger(TLiveSellerLedger liveLedger);
	
	/**
	 * 
	 * 方法描述：获取商家直播分账设置 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2016年11月21日下午2:35:18 <br/>
	 * @param liveLedger
	 * @return
	 */
	public TLiveSellerLedger getSellerLiveLeger(Integer sellerid);
	
	/**
	 * 
	 * 方法描述：删除商家的直播分账设置<br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2016年12月9日下午5:14:33 <br/>
	 * @param sellerid
	 * @return
	 */
	public int deleteSellerLiveLedger(Integer sellerid);
	
	/**
	 * 
	 * 方法描述：获取已上线商家 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2016年12月9日下午5:42:50 <br/>
	 * @return
	 */
	public List<TSeller> getOnlineSellerList(TSeller seller);
	
	/**
	 * 
	 * 方法描述：更新商家鸟币支付状态
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月20日上午10:35:37 <br/>
	 * @param seller
	 * @return
	 */
	Integer updateCoinStatus(TSeller seller);
	
	/**
	 * 
	 * 方法描述：批量修改商家   公开商户，参与分红，付费商家  状态
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月29日上午11:27:48 <br/>
	 * @param seller
	 * @return
	 */
	Integer statusOption(TSeller seller);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日下午5:13:55 <br/>
	 * @param sellerid
	 * @return
	 */
	public Map<String, Object> getCommentParam(Integer sellerid);

	/**
	 * 方法描述：检查此商户id是否在此城市
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月25日上午10:58:17 <br/>
	 * @param sellerid
	 * @param provinceId
	 * @param cityId
	 * @return
	 */
	public long checkIdAtArea(@Param("id")Integer sellerid,@Param("province") Integer provinceId,@Param("city") Integer cityId);

	/**
	 * 
	 * 方法描述：获取指定商家信息 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月6日上午11:30:23 <br/>
	 * @param sellerids
	 * @return
	 */
	public List<TSeller> getSellerBaseList(@Param("sellerids")String sellerids);
	
	/**
	 * 
	 * 方法描述：寻蜜客放弃商家关联 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年8月8日下午6:05:56 <br/>
	 * @param seller
	 * @return
	 */
	public int xmerAbandonSeller(TSeller seller);
}
