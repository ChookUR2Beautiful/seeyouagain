package com.xmniao.xmn.core.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* 项目名称：saasService   
* 类名称：AppUpdateDao   
* 类描述：APP版本更新接口 Dao  
* 创建人：liuzhihao   
* 创建时间：2016年4月15日 下午3:20:40   
* @version    
*
 */
@Repository
public interface AppUpdateDao {
		/**
		 * 
		* @Title: queryAppUpdate
		* @Description: (查询最新版本信息)
		* @return Map<Object,Object>    返回类型
		* @throws
		 */
		@DataSource("joint")
		public Map<Object,Object> queryAppUpdate(Map<Object,Object>map);
}
