package com.xmniao.xmn.core.recruit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：xmnService   
* 类名称：UserDao   
* 类描述：编辑简历信息接口  
* 创建人：liuzhihao   
* 创建时间：2016年5月20日 下午2:05:23   
* @version    
*
 */
@Repository
public interface UserDao {

	//插入简历信息接口
	@DataSource("burs")
	public int InseetUserCVInfo(Map<Object,Object> param);
	
	//编辑简历信息
	@DataSource("burs")
	public int editUserCVInfo(Map<Object,Object> param);
	
	//插入简历信息标签
	@DataSource("burs")
	public int insertUserTagInfo(List<Map<Object,Object>> users);
	
	//删除所有的标签
	@DataSource("burs")
	public void deleteUserTagInfo(Integer id);
	
	//查看简历（用户自己）
	@DataSource("burs")
	public Map<Object,Object> queryUserInfo(Integer uid);
	
	//查看商户名称
	@DataSource("joint")
	public String querySellerName(Integer sellerid);
	
	//获取用户标签列表
	@DataSource("burs")
	public List<Map<Object,Object>> queryUserTagInfo(Map<Object,Object> param);
	
	//查看寻蜜客信息
	@DataSource("burs")
	public Map<Object,Object> selectUsrName(Integer uid);
	
}
