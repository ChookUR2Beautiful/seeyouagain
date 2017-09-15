package com.xmniao.xmn.core.catehome.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.catehome.entity.Advertising;
import com.xmniao.xmn.core.catehome.entity.StartImg;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: AdvertisingDao    
* @Description:广告图片表dao
* @author: liuzhihao   
* @date: 2016年11月9日 下午2:56:42
 */

@Repository
public interface AdvertisingDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(Advertising record);

	@DataSource("joint")
    int insertSelective(Advertising record);

	@DataSource("joint")
    Advertising selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(Advertising record);

	@DataSource("joint")
    int updateByPrimaryKeyWithBLOBs(Advertising record);

	@DataSource("joint")
    int updateByPrimaryKey(Advertising record);
    
    /** 通过城市编号查询广告图片 */
	@DataSource("joint")
    List<Advertising> selectByCity(Integer city);
    
	@DataSource("joint")
    List<Advertising> findAll();
	
	
	
    /** 通过城市编号查询广告图片 */
	@DataSource("joint")
    List<StartImg> selectStartImgByCity(Map<Object, Object> map);
    
	//查询全国的
	@DataSource("joint")
    List<StartImg> findAllStartImg();

	// 查询直播间广告
    @DataSource("joint")
    List<Map<Object, Object>> selectLiveHomeAdList(@Param("cityId")Integer cityId);

	
}