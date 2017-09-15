/**
 * 2016年8月15日 下午3:26:03
 */
package com.xmniao.xmn.core.live.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：UserFeedBackDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 下午3:26:03
 * @version
 */
@Repository
public interface UserFeedBackDao {
	
	/**
	 * 
	* @Title: addUserFeedBack
	* @Description: 新增用户反馈 
	* @return Integer 返回类型
	 */
	@DataSource("joint")
	public Integer addUserFeedBack(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryisFeedBack
	* @Description: 查看此客户是否被自己举报过
	* @return Integer
	 */
	@DataSource("joint")
	Integer queryisFeedBack(Map<Object,Object> map);
}
