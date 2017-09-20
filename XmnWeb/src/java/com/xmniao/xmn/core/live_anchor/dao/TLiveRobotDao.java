package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRobot;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveRobotDao
 *
 * 类描述：直播机器人Dao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-24下午7:47:23
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveRobotDao extends BaseDao<TLiveRobot>{
	
	/**
	 * 
	 * 方法描述：通过ID删除机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-24下午7:55:53
	 * @param id
	 * @return
	 */
	@DataSource(value = "slave")
    int deleteById(Integer id);

	/**
	 * 
	 * 方法描述：通过ID查询机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-24下午7:56:17
	 * @param id
	 * @return
	 */
	@DataSource(value = "slave")
    TLiveRobot selectById(Integer id);
	
	
	/**
	 * 批量新增机器人信息
	 */
	@DataSource(value="master")
	public Integer addBatch(List<TLiveRobot> list);

	/**
	 * 获取房间机器人数量
	 * 创建人： id
	 * 创建时间：2016年9月1日下午5:55:47
	 * @param parseInt
	 * @return
	 */
	@DataSource(value = "slave")
	public Integer getLiveRobotSum(int id);

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： Administrator
	 * 创建时间：2016年9月2日下午5:05:14
	 * @return
	 */
	@DataSource(value = "slave")
	public List<Map<String, String>> getLiveRobotSum2();

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-13下午2:03:28 <br/>
	 * @return
	 */
	@DataSource(value="master")
	int deleteAll();

}