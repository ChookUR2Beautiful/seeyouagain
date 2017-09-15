package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.catehome.entity.Business;
import com.xmniao.xmn.core.util.dataSource.DataSource;


/**
 * 
*      
* 类名称：BusinessDao   
* 类描述：   商圈
* 创建人：xiaoxiong   
* 创建时间：2016年11月1日 下午3:04:02     
*
 */
@Repository
public interface BusinessDao {
	
	/**
	 * 
	 * @Description: 查询商圈信息
	 * @author xiaoxiong
	 * @date 2016年11月1日
	 */
	@DataSource("joint")
	Map<Object, Object> selectBusinessByid(int zoneid);
	@DataSource("joint")
	int deleteByPrimaryKey(Integer bid);
	@DataSource("joint")
    int insert(Business record);
	@DataSource("joint")
    int insertSelective(Business record);
	@DataSource("joint")
    Business selectByPrimaryKey(Integer bid);
	@DataSource("joint")
    int updateByPrimaryKeySelective(Business record);
	@DataSource("joint")
    int updateByPrimaryKey(Business record);
	
    /**
     * 通过hash值获取商品id
     * @param geoHash
     * @return
     */
    @DataSource("joint")
    List<Map<Object,Object>> selectByLonAndLat(Map<Object,Object> map);
    
    @DataSource("joint")
    Business selectByTitle(Map<Object,Object> map);
	

    /**
     * 搜索商圈
     * @param map
     * @return
     */
    @DataSource("joint")
    List<Map<Object,Object>> searchLocationByTitle(Map<Object,Object> map);
    
    /**
     * 根据城市id查询所有商圈信息
     * @param map
     * @return
     */
    @DataSource("joint")
    List<Map<Object,Object>> findAllBusinessByCityId(Map<Object,Object> map);
    
    /**
     * 根据区域ID 获取该区域下的所有商圈信息
     * @param map
     * @return
     */
    @DataSource("joint")
    List<Map<Object,Object>> findAllByAreaId(Map<Object,Object> map);


    /**
     * 根据商圈id列表获取商圈信息
     * @param map
     * @return
     */
    @DataSource("joint")
    List<Map<Object,Object>> findAllByZoneIds(Map<Object,Object> map);

    /**
     * 根据areaId获取地区信息
     * @param map
     * @return
     */
    @DataSource("joint")
    Map<Object,Object> findAreaByAreaId(Map<Object,Object> map);
}
