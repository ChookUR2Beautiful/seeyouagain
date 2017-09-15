package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.SaasSignRequest;
import com.xmniao.xmn.core.common.request.ShopPicRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface SellerInfoDao {

	/**
	 * 
	* @Title: querySellerInfoBySellerid
	* @Description:查询商家信息 
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> querySellerInfoBySellerid(Integer sellerid);
	/**
	 * 
	* @Title: querySellerDetailedBySellerid
	* @Description: 查询商家详细信息
	* @return Map<Object,Object>    商户性情信息
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> querySellerDetailedBySellerid(Integer sellerid);
	
	/**
	 * @param integer 
	 * 
	* @Title: queryFoodBySellerid
	* @Description: 查询菜品
	* @return List<Map<Object,Object>>    菜品信息
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryFoodBySellerid(Integer integer);
	/**
	 * 
	* @Title: querySellerPic
	* @Description: 查询商家图片
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> querySellerPic(Integer sellerid);
	
	
	
	@DataSource("joint")
	public void insertSeller(SaasSignRequest saasSignRequest);
	
	@DataSource("joint")
	public Integer getAreaIdByName(Map<Object,Object> map);
	
	//查询特惠商品
	@DataSource("joint")
	List<Map<Object, Object>> queryBargainBySellerid(Integer sellerid);
	
	//添加商户图片
	@DataSource("joint")
	void addSerllerpic(Map<Object, Object> paramMap);

	// 更新商户图片
	@DataSource("joint")
	void updateSellerPic(Map<Object, Object> paramMap);

	// 批量更新商户图片
	@DataSource("joint")
	void updateSellerPicList(Map<Object, Object> paramMap);



	//更新商家身份证，营业执照、卫生许可证
	@DataSource("joint")
	void updateSellerInfoBySellerid(ShopPicRequest shopPicRequest);
	
	/**
	 * 
	* @Title: insertSellerInfoToDetail
	* @Description: 添加店铺的人均消费
	* @return void    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	void insertSellerInfoToDetail(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: querySellerPicApply
	* @Description: 查询已上线商家的环境图
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> querySellerPicApply(Integer sellerid);
	
	/**
	 * 
	* @Title: insertSellerLandmark
	* @Description:添加店铺经度和纬度
	* @return void    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public void insertSellerLandmark(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: insertSellerAgio
	* @Description: 添加商铺折扣信息
	* @return void    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	Integer insertSellerAgio(Map<Object,Object> map);
	
	
	/**
	 * 
	* @Title: insertSellerAgioRecord
	* @Description: 
	* @return void
	 */
	@DataSource("joint")
	public void insertSellerAgioRecord(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: querySellerLandMark
	* @Description:查询商铺经，纬度
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> querySellerLandMark(Integer sellerid);
	
	/***
	 * 查询耳机类别名称
	 */
	@DataSource("joint")
	public String queryTradeName(Map<Object, Object> tradeMap);
	
	/**
	 * 
	* @Title: querySellerMateriel
	* @Description: 查询商铺物料发送详情
	* @return Integer    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public Integer querySellerMateriel(Integer sellerid);
	
	/**
	 * 
	* @Title: modifySeller
	* @Description: 修改店铺信息状态
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Integer modifySeller(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: queryMaterielOrderBySellerId
	* @Description: 根据店铺id去查询物料订单表，获取订单信息
	* @return map   返回状态status, 和备注 remark,order_sn
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Map<Object,Object> queryMaterialOrderBySellerId(Map<Object, Object> map);
	
	/**
	 * 把寻蜜客签约的商家uid修改为空
	* @Title: updateSellerUidByUid
	* @Description: 
	* @return void    返回类型
	* @throws
	 */
	void updateSellerUidByUid(String uid);
	
	/**
	 * 
	* @Title: querySellersLandMark
	* @Description: 查询一批店铺的经纬度
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> querySellersLandMark(@Param("sellerIds")List<Integer> sellerIds);
	
	/**
	 * 
	 * @Description: 查询是否有设置折扣
	 * @author xiaoxiong
	 * @date 2016年10月11日
	 */
	@DataSource("joint")
	int queryAgioCount(Integer sellerid);
	
	/**
	 * 
	* @Title: updateSellerLandmark
	* @Description: 更改商家经纬度
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	void updateSellerLandmark(Map<Object, Object> paramMap);
	
	/**
	 * 根据城市、定位坐标查询该城市的商铺信息
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllByMap(Map<Object,Object> map);
	
	/**
	 * 查询有直播的商铺信息
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> querySellersByLives(Map<Object,Object> map);
	
	/**
	 * 通过用户id和商圈 查询用户在该商圈消费过的商铺信息
	 * @param list
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllZoneIdBySellerId(List<Integer> list);
	
	/**
	 * 查询用户消费商铺
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryConsumSellerByUid(Map<Object,Object> map);
	
	/**
	 * 通过商铺id查询通分类的商铺信息
	 * @param list
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryTradeSellersBySellerId(List<Integer> list);
	
	/**
	 * 通过商铺id查询商铺信息和距离我的距离
	 * @return
	 */
	@DataSource("joint")
	Map<Object,Object> querySellerAndRangesBySellerId(Map<Object,Object> map);
	
	/**
	 * 通过商圈id 获取商圈内商铺的数量
	 * @return
	 */
	@DataSource("joint")
	int sumSellerCountsByBid(Map<Object,Object> map);
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAll();
	
	@DataSource("joint")
	Map<Object,Object> findAllVsLive(String sellerid);
	
	@DataSource("joint")
	List<Integer> findAllCitysDistinst();
	
	/**
	 * 单纯的批量查询商铺信息
	 * @param sellers
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findSellerAndConsumsBySellerId(@Param("sellers")List<Integer> sellers);
	
	/**根据用户城市，选择的商圈，分类查询所有有效的店家专享卡
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryDebitCardSellerRanges(Map<Object, Object> map);

	@DataSource("joint")
	Integer insertSellerByMap(Map<Object, Object> map);

	@DataSource("joint")
	void updateSellerBySellerid(Map<Object, Object> map);

	@DataSource("joint")
	void updateSellerInfoToDetail(Map<Object, Object> map);
	/**查询寻密客签约商家
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> findMaterialSellerRequest(Map<Object, Object> map);
	
	/**
	 * 
	 * @Title:querySellerCommission
	 * @Description:查询商家订单的 提成信息
	 * @param sellerid 商家id
	 * @return List<String>
	 * 2017年5月4日下午3:33:15
	 */
	@DataSource("joint")
	public List<String> querySellerCommission(Integer sellerid);
	
	/**
	 * 
	 * @Title:listSellerByZoneid
	 * @Description:根据商圈id查询商圈范围内的店铺
	 * @param paraMap 商圈id和 关键字
	 * @return List<Map<Object,Object>> 店铺列表
	 * 2017年5月17日下午2:39:28
	 */
	@DataSource("joint")
	List<Map<Object, Object>> listSellerByKeyword(Map<Object,Object> paraMap);
	
	/**
	 * 查询点评店铺的信息
	 * @Title:queryCommentSellerInfoById
	 * @Description 签约店铺点评是查询信息
	 * @param sellerId
	 * @return Map<String,String>
	 * 2017年5月24日下午5:21:36
	 */
	@DataSource("joint")
	Map<String, String> queryCommentSellerInfoById(Integer sellerId);

//	删除店铺信息
	@DataSource("joint")
	void deleteSeller(@Param("sellerid") Integer sellerId);
//删除人均消费信息
	@DataSource("joint")
	Map<String, String> deleteSellerInfoToDetail(@Param("sellerid")Integer sellerId);
//	删除店铺经纬度
	@DataSource("joint")
	Map<String, String> deleteLandmark(@Param("sellerid")Integer sellerId);
//	删除商铺折扣
	@DataSource("joint")
	Map<String, String> deleteAgioRecord(@Param("sellerid")Integer sellerId);

	/**
	 * 查询连锁记录
	 * @return
	 */
	@DataSource("joint")
	Map<Object, Object> selectLsSellerByName(Map<Object, Object> map);

}
