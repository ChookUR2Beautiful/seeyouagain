package com.xmniao.xmn.core.recruit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface WorksDao {
	
	//查询岗位基本信息
	@DataSource("burs")
	public List<Map<Object,Object>> queryWorksList(Map<Object,Object> param);
	
	//获取商铺信息
	@DataSource("joint")
	public Map<Object,Object> querySellerLogo(Integer id);
	
	//根据岗位id获取该岗位详细信息
	@DataSource("burs")
	public Map<Object,Object> queryWorksInfo(Integer id);
	
	//查询招聘岗位标签列表
	@DataSource("burs")
	public List<Map<Object,Object>> queryWorksTagInfo(Map<Object,Object> param);
	
	//查询省，市，区名称
	@DataSource("joint")
	public String queryAreaName(Map<Object,Object> map);
}
