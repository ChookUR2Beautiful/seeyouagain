package com.xmniao.xmn.core.catehome.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CateHomeDao   
* 类描述：   发现美食首页信息Dao
* 创建人：yezhiyong   
* 创建时间：2016年6月21日 上午9:15:02   
* @version    
*
 */
@Repository
public interface CateHomeDao {

	/**
	 * 
	* @Title: queryCateAdvertisingList
	* @Description: 根据市查询发现美食首页的轮播图列表信息
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCateAdvertisingList(Integer city);

	
	/**
	 * 
	* @Title: queryCateCategory
	* @Description: 查询发现美食首页分类信息(大类)
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCateCategory();


	/**
	 * 
	* @Title: queryBannerList
	* @Description: 查询发现美食首页banner信息
	* @return List<BannerEntity>    返回类型
	* @author
	* @throws
	 */
	public List<BannerEntity> queryBannerList();

	/**
	 * 
	* @Title: insertCateComment
	* @Description: 发表评价
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int insertCateComment(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryCommentByBid
	* @Description: 根据订单号去查询订单评论信息
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCommentByBid(Long bid);

	/**
	 * 
	* @Title: queryCateCommentListByUid
	* @Description: 根据uid去查询用户评价列表
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCateCommentList(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryCommentByCid
	* @Description:	根据评价id去查询评价信息
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCommentByCid(Integer cid);

	/**
	 * 
	* @Title: dedeteCateCommentByCid
	* @Description: 根据评价id删除评价信息
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int dedeteCateCommentByCid(Integer id);
	
	/**
	 * 搜索联想
	 * @author xiaoxiong
	 * @date 2016年11月24日
	 */
	@DataSource("joint")
	public List<String> searchConnectList(Map<String, Object> params);

}
