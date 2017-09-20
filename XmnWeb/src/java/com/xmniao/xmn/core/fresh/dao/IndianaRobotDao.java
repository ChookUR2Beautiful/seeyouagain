package com.xmniao.xmn.core.fresh.dao;

import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.IndianaRobot;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface IndianaRobotDao extends BaseDao<IndianaRobot>{
    int deleteByPrimaryKey(Integer id);

    int insert(IndianaRobot record);

    int insertSelective(IndianaRobot record);

    IndianaRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndianaRobot record);

    int updateByPrimaryKey(IndianaRobot record);

	/**
	 * 方法描述：删除全部机器人
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月2日下午4:46:40 <br/>
	 */
	void deleteAll();

	/**
	 * 方法描述：统计用户数量
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月13日上午10:07:48 <br/>
	 * @return
	 */
	@DataSource("burs")
	Long countUrs();

	/**
	 * 方法描述：查询随机用户
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月13日上午10:14:16 <br/>
	 * @param nextLong
	 * @return
	 */
	@DataSource("burs")
	Map<String, Object> selectNextLong(long nextLong);
}