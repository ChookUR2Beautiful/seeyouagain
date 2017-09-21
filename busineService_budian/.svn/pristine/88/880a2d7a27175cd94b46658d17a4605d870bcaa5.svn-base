package com.xmniao.urs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;



public interface XmerDao {

	/**
     * @Title:getXmerIssues
     * @Description:获取当前寻蜜客关系
     * @param uid
     * @return Map<String,Object>
     * @throw
     */
	public Map<String,Object> getXmerIssues(int uid);
	
	/**
	 * 获取寻蜜客用户名
	 * @Title: getXmerName 
	 * @Description:
	 */
	public List<Map<String,Object>> getXmerName(String[] ids);
	
	/**
	 * 
	* @Title: queryXmer
	* @Description: 热点人物统计
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	List<Map<Object, Object>> queryXmer();

	/**
	 * 查询所有寻蜜客的电子钱包
	* @Title: queryXmerWallet
	* @Description: 
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> queryXmerWallet();


	/**
	 * 根据uid查询寻蜜客
	 * @param uid
	 * @return
	 */
	Map<String,Object> getXmerByUid(String uid);
	
	/**
	 * 创建寻蜜客
	 * @param map
	 * @return
	 */
	int insertXmer(Map<String,Object> map);

	
	
	/**
	 * 更新伙伴数量
	 * @param uid
	 * @return
	 */
	int updatePartnerNum(Map<String,String> map);
	
	/**
	 * 获取寻蜜客的初始头衔
	 */
	String getInitAchievement();
	
	/**
	 * 获取寻蜜客信息
	 */
	Map<String,Object> getXmerInfo(Integer uid);
	
	/**
	 * 寻蜜客会员收益关系链信息
	 * @param map
	 * @return
	 */
	int insertUrsEarningsRelation(Map<String, Object> map);
	
	/**
	 * 
	 * 方法描述：根据uid获取寻蜜客当前等级
	 * 创建人：jianming  
	 * 创建时间：2017年5月10日 下午2:29:31   
	 * @param uid
	 * @return
	 */
	public Map<String, Object> getLevelByUid(Integer uid);
	
	/**
	 * 
	 * 方法描述：修改寻蜜客等级信息
	 * 创建人：jianming  
	 * 创建时间：2017年5月10日 下午2:32:49   
	 * @param level
	 * @return
	 */
	public int updateXmerLevel(Map<String, Object> level);

	 /**
	 * 方法描述：修改寻蜜客售出数量
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月12日下午4:24:10 <br/>
	 * @param intValue
	 * @param i 
	 * @return
	 */
	public int updateXmerSoldNums(@Param("uid")int uid,@Param("soldNums") int soldNums);
	
	 /**
	 * 方法描述：查询关系链信息 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月24日上午10:40:33 <br/>
	 * @param uid
	 * @param soldNums
	 * @return
	 */
	public Map<String, Object> getXmerUrsEarningsRelationByUid(@Param("uid") int uid, @Param("objectOriented") int rtype);

}