package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.HandleCKRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 项目名称： xmnService
 * 类名称： FriendshipDao
 * 类描述：查询朋友关系
 * 创建人： lifeng
 * 创建时间： 2016年5月23日下午7:58:56
 * @version
 */
@Repository
public interface FriendshipDao {

	/**
	 * @Title: 根据uid查询该用户的好友的uid
	 * @Description: 
	 * @param uidFrom
	 * @return 该用户的好友的uid（uidTo）
	 */
	@DataSource("burs")
	List<Integer> queryUidToByUidFrom(Map<Object,Object> paramMap);
	
	/**
	 * 
	* @Title: modifyFriendApplyByid
	* @Description:修改好友申请信息 
	* @return void    返回类型
	* @throws
	 */
	@DataSource("burs")
	void modifyFriendApplyByid(HandleCKRequest paramMap);
	
	@DataSource("burs")
	void addFriendShip(Map<Object,Object> map);

	//查询好友申请
	@DataSource("burs")
	List<Map<Object, Object>> queryFriendApplyByUid(Integer uid);
	
	//批量查询导师(寻蜜客)昵称
	@DataSource("burs")
	public List<Map<Object,Object>> queryTutorAvatarListByUid(List<Integer> list);
	
	//批量查询导师(寻蜜客)总收入
	@DataSource("xmnpay")
	public List<Map<Object,Object>> queryXmkTutorIncomeListByUid(List<Integer> list);
}
