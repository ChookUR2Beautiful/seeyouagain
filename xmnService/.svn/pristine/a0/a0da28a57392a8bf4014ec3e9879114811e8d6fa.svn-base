package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.common.request.NearbyStageRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：xmnService   
* 类名称：StageDao   
* 类描述：驿站相关接口DAO      
* 创建人：liuzhihao   
* 创建时间：2016年6月23日 上午11:05:55   
* @version    
*
 */

@Service
public interface StageDao {

	/**
	 * 
	* @Title: queryXmerSignOrder
	* @Description:查询寻蜜客签约商铺集合
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<String> querySignSellerAreaId(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryStageInfo
	* @Description: 查询驿站详情列表
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryStageInfoList(List<String> list);
	
	/**
	 * 
	* @Title: queryActivtyInfo
	* @Description: 查询驿站活动详情
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryActivtyInfo(List<Map<Object,Object>> list);

	/**
	 * 
	* @Title: queryStageInfo
	* @Description:根据驿站id查询驿站详情
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryStageInfo(String jointid);

	/**
	 * 
	* @Title: nearbyStageList
	* @Description: 查询附近驿站
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> nearbyStageList(NearbyStageRequest request);

	/**
	 * 
	* @Title: queryStageAchievement
	* @Description: 查询驿站头衔
	* @return String    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public String queryStageAchievement(Integer jointid);

}
