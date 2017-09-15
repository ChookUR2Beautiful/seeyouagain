package com.xmniao.xmn.core.sellerPackage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.sellerPackage.entity.SellerPackage;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerPackageDao    
* @Description:套餐信息dao   
* @author: liuzhihao   
* @date: 2017年2月20日 上午9:50:33
 */
@Repository
public interface SellerPackageDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    SellerPackage selectByPrimaryKey(Integer id);
	
	/**
	 * 获取当前商家套餐,需有库存,一上线,正在销售的
	 * */
	@DataSource("joint")
	SellerPackage querySellerPackageInfoById(Integer id);
	
	/**
	 * 按距离查询套餐列表
	 * @param lat
	 * @param lon
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllByRanges(@Param("lat") Double lat,@Param("lon") Double lon,@Param("cityId")Integer cityId,@Param("tradeId")Integer tradeId);

	/**
	 * 查询所有推荐的套餐
	 * 1.按后台推荐降序排序
  	 *2.按是否有直播通告降序排序 
  	 *3.按时当天有直播通告降序排序
  	 *4.按是否在当天有套餐降序排序
  	 *5.按距离用户距离远近升序排序
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findRecommendCombo(@Param("lat") Double lat,@Param("lon") Double lon,@Param("cityId") Integer cityId,@Param("startDate")String startDate,@Param("endDate")String endDate);

	//根据店铺ID统计店铺存在多少个套餐
	@DataSource("joint")
	Map<Object,Object> findOneBySellerId(@Param("sellerid")Integer sellerid);
	
	/**
	 * 根据店铺ID查询套餐信息
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> selectCombosBySellerId(@Param("sellerid")Integer sellerid);
	
	/**
	 * 描述:修改当前套餐的库存量
	 * @param Map<Object,Object>
	 * @return int
	 * */
	@DataSource("joint")
	int modifySellerPackageInfo(Map<Object,Object> map);
	
	/**
	 * 根据套餐信息查询套餐列表
	 * @param (Map<Object, Object> map
	 * @return List<Map<Object,Object>>
	 * */
	@DataSource("joint")
	List<Map<Object,Object>> querySellerPackageList(Map<Object, Object> map);
	
	/**
	 * 根据 直播间商家获取当前商家推荐套餐 和 其他店家的推荐套餐
	 * @param (Map<Object, Object> map
	 * @return List<Map<Object,Object>>
	 * */
	@DataSource("joint")
	List<SellerPackage> queryPackageRecommendList(Map<Object,Object> map);
	
	/**
	 * 查询没有直播状态的推荐套餐
	 * @param lat
	 * @param lon
	 * @param cityId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findHomeRecommendCombo(@Param("lat") Double lat,@Param("lon") Double lon,@Param("cityId") Integer cityId,@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	/**
	 * 查询我附近的套餐列表
	 * @param 用户lat
	 * @param 用户lon
	 * @param cityId
	 * @return
	 */
	List<Map<Object,Object>> queryMynearbySellerPackage(Map<Object, Object> map);
	
	
}