package com.xmniao.xmn.core.xmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AppLoadUrlDao   
* 类描述：   获取app客户端下载地址
* 创建人：yezhiyong   
* 创建时间：2016年6月16日 下午2:51:39   
* @version    
*
 */
@Repository
public interface AppLoadUrlDao {

	/**
	 * 
	* @Title: queryLoadUrlByVtype
	* @Description: 根据手机类型查询app客户端下载地址
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryLoadUrlByVtype(Integer vtype);

}
