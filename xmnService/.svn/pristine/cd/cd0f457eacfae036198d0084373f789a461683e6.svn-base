package com.xmniao.xmn.core.verification.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.verification.entity.Urs;

/**
 * person DAO
 * @author Administrator
 *
 */
@Repository
public interface UrsDao {
	
	/**
	 * 获取用户列表
	 * @return
//	 */
//    @DataSource("burs")  	//数据库选择
//    public List<Map<String,Object>> list();
    
    
	/**
	* @Description:  根据uid查询burs表用户信息
	* @return Map    返回类型
	 * */
	@DataSource("burs")
	Map<Object, Object> queryBursInfoByUid(Map<Object, Object> map);
	
    /**
     * 通过用户名获取用户信息
     * @param uname
     * @return
     */
    @DataSource("burs")  
    public Urs getUrsByUname(String uname);
    /**
     * 
     * @Description: 添加用户信息
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("burs")
	public int addUrs(Map<Object, Object> paramMap);
    
    /**
     * 
     * @Description: 添加用户信息
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("burs")
	void addUrsByEntity(Urs urs);
    
    /**
     * 
    * @Title: queryUrsByUid
    * @Description: 根据用户id去查询用户信息
    * @return Urs    返回类型
    * @author
    * @throws
     */
    @DataSource("burs")
	public Urs queryUrsByUid(Integer uid);
    
    /**
     * 跟新用户基本资料
    * @Title: updateUrsByUid
    * @Description: 
    * @return void    返回类型
    * @throws
     */
    @DataSource("burs")
	public int updateUrsByUid(Map<Object, Object> paramMap);
    
    /**
     * 更新主播信息
    * @Title: updateAnchorByUid
    * @Description: 
    * @return int    返回类型
    * @throws
     */
    @DataSource("burs")
	public int updateAnchorByUid(Map<Object, Object> param);
     
    /**
     * 
     * @Description: 查询是否收藏店铺
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int ursCollectSellerCount(Map<String, Object> params);
    
    /**
     * 
     * @Description: 收藏店铺
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int ursCollectSellerInsert(Map<String, Object> params);
    
    /**
     * 
     * @Description: 查询是否关注用户
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int ursCollectUrsCount(Map<String, Object> params);
    
    /**
     * 
     * @Description: 用户关注
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int ursColletUrsInsert(Map<String, Object> params);
    
    /**
     * 
     * @Description: 查询两个用户是否在同一个商家消费过
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int isInSellerCons(@Param("uid")String uid, @Param("cuid")String cuid);
    
    /**
     * 
     * @Description: 查询两个用户是否关注同一用户
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int ursColletUrsCount(@Param("uid")String uid, @Param("cuid")String cuid);
    
    /**
     * 
     * @Description: 查询用户是否有同一收藏记录
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int ursCollectSellerCount(@Param("uid")String uid, @Param("cuid")String cuid);
    
    /**
     * @Description: 查询是否有同一个浏览记录
     * @author xiaoxiong
     * @date 2016年11月15日
     */
    @DataSource("joint")
	public int ursBrowsedCount(@Param("uid")String uid, @Param("cuid")String cuid);
    
    /**
     * 
     * @Description: 查询是否收藏店铺
     * @author xiaoxiong
     * @date 2016年11月17日
     */
    @DataSource("joint")
	public int isCollectSeller(Map<String, Object> params);
    
    /**
     * 
     * @Description: 查询浏览记录数量
     * @author xiaoxiong
     * @date 2016年11月17日
     */
    @DataSource("joint")
	public int queryBrowsedCountByUidAndSellerid(@Param("uid")int uid,@Param("sellerid")int sellerid);
    
    /**
     * 
     * @Description: 取消店铺收藏
     * @author xiaoxiong
     * @date 2016年11月17日
     */
    @DataSource("joint")
	public int deleteUrsCollectByUidAndSellerid(@Param("sellerid")Integer sellerid,@Param("uid")int uid);
    
    /**
     * 
     * @Description: 取消用户关注
     * @author xiaoxiong
     * @date 2016年11月17日
     */
    @DataSource("joint")
	public int deleteLiveFocus(@Param("uid")Long uid, @Param("cuid")Long cuid);
    
    /**
     * 查询所有我关注的用户信息
     * @param uid
     * @return
     */
    @DataSource("joint")
    List<Map<Object,Object>> findMyFocusByUid(Integer uid);
    
    /**
     * 
    * @Title: queryCollectCountBySellerIdsAndUid
    * @Description: 批量查询用户是否收藏过这些店铺
    * @return List<Map<Object,Object>>    返回类型
    * @throws
     */
    @DataSource("joint")
	public List<Map<Object, Object>> queryCollectCountBySellerIdsAndUid(Map<Object, Object> paramMap);
    
    /**
     * 
    * @Title: queryBrowsedCountBySellerIdsAndUid
    * @Description: 批量查询是否浏览过这些店铺
    * @return List<Map<Object,Object>>    返回类型
    * @throws
     */
    @DataSource("joint")
	public List<Map<Object, Object>> queryBrowsedCountBySellerIdsAndUid(Map<Object, Object> paramMap);
    
    /**
     * 通过用户ID分页查询用户关注的用户ID
     * @param uid
     * @return
     */
    @DataSource("joint")
    List<Integer> findUserFollowsByUid(@Param("uid")String uid,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
   
    /**
     * 批量分页查询用户信息
     * @param fuids
     * @param page
     * @param pageSize
     * @return
     */
    @DataSource("burs")
    List<Urs> findUrsByPage(@Param("fuids")List<Integer> fuids,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
    
    /**
     * 用户注册赠送大礼包列表
     * @return List<Map<Object, Object>>
     */
    @DataSource("joint")
    List<Map<Object, Object>> queryUserRegisterGift();
    
    /**
     * 删除
     * @return int
     * */
    int deleteUrsByUid(@Param("uid")Integer uid);
    
}
