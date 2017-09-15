package com.xmniao.xmn.core.integral.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.integral.entity.ProductInfoEntity;
import com.xmniao.xmn.core.seller.entity.Trade;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：xmnService   
* 类名称：CategorysDao   
* 类描述：积分商城首页DAO   
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 上午11:26:43   
* @version    
*
 */
@Repository
public interface IntegralMallDao {

	/**
	 * 
	* @Title: queryBannerByStyle
	* @Description: 查询banner图
	* @return BannerEntity    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<BannerEntity> queryBannerList(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryProductInfo
	* @Description: 根据区域查询积分商品列表
	* @return ProductInfoEntity    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<ProductInfoEntity> queryProductInfoList(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryTradeList
	* @Description:查询分类
	* @return List<TradeEntity>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<Trade> queryTradeList();
	
	/**
	 * 
	* @Title: queryWalletInfo
	* @Description:根据用户id查询用户钱包信息
	* @return Double    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("xmnpay")
	public Map<Object,Object> queryWalletInfo(Integer uid);
	
	/**
	 * 
	* @Title: queryProductInfoByCodeId
	* @Description: 根据积分商品id获取积分商品介绍 
	* @return ProductInfoEntity    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public ProductInfoEntity queryProductInfo(String codeid);
	
	/**
	 * 
	* @Title: queryProductImgByCodeId
	* @Description: 根据积分商品编号查询积分商品图片
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public Map<Object,Object> queryProductImgByCodeId(String codeid);
	
	/**
	 * 
	* @Title: getWalletRecodeList
	* @Description:根据用户钱包id获取用户钱包使用记录
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("xmnpay")
	public List<Map<Object,Object>> getWalletRecodeList(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: getIntegralDynamicList
	* @Description: 查询最新积分消费动态
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("xmnpay")
	public List<Map<Object,Object>> getIntegralDynamicList();
	
	/**
	 * 
	* @Title: getUidList
	* @Description: 查询用户uid
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("xmnpay")
	public List<Map<Object,Object>> getUidList(List<Map<Object,Object>> list); 
	
	/**
	 * 
	* @Title: getXmerPhoneByUid
	* @Description: 查询积分消费用户的手机号码
	* @return List<String>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("burs")
	public List<Map<Object,Object>> getXmerPhoneByUid(List<Map<Object,Object>> list);
	
	/**
	 * 
	* @Title: getProductInfoCounts
	* @Description: 统计热门商品数量
	* @return Integer    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Integer getProductInfoCounts();
	
	
	/**
	 * 查询运费模版
	* @Title: getPostage
	* @Description: 
	* @return Map<String,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<String, Object> getPostage(Integer tid);

	/**
	 * 
	* @Title: queryLiveRoomBanner
	* @Description: 查询直播间banner广告:查询美食首页或者直播首页的重点推荐banner
	* @return List<BannerEntity>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<BannerEntity> queryLiveRoomBanner();
}
