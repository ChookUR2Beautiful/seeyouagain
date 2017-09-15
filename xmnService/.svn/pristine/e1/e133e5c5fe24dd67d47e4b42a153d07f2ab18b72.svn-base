package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.xmer.entity.Area;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AreaDao   
* 类描述： 地区Dao  
* 创建人：xiaoxiong   
* 创建时间：2016年5月23日 下午2:20:17   
* @version    
*
 */
@Repository
public interface AreaDao {

	@DataSource("joint")
	int deleteByPrimaryKey(Integer areaId);

	@DataSource("joint")
    int insert(Area record);

	@DataSource("joint")
    int insertSelective(Area record);

	@DataSource("joint")
    Area selectByPrimaryKey(Integer areaId);

	@DataSource("joint")
    int updateByPrimaryKeySelective(Area record);

	@DataSource("joint")
    int updateByPrimaryKey(Area record);
	
	/**
	 * 
	* @Title: queryAreqByAreaId
	* @Description: 查询地区名次
	* @return String    返回名称
	* @throws
	 */
	@DataSource("joint")
	String queryAreaByAreaId(int areaId);
	
	/**
	 * 通过区域名称获取区域详情
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	Area selectByTitle(Map<Object,Object> map);
	
	/**
	 * 查询所有区域名称 
	 * @return
	 */
	@DataSource("joint")
	List<Area> findAllCity(@Param("citys") List<Integer> citys);
	
	/**
	 * 根据父ID查询所有子类
	 * @param pid
	 * @return
	 */
	@DataSource("joint")
	List<Area> findAllByPid(Integer pid);
	
	/**
	 * 根据父ID查询所有子类(只要名称和ID)
	 * @param pid
	 * @return
	 */
	@DataSource("joint")
	List<Area> queryAreaByType(int pid);
	
}
