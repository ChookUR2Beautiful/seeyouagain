package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.GroupLevel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import org.apache.ibatis.annotations.Param;

public interface GroupLevelDao extends BaseDao<GroupLevel>{
    int deleteByPrimaryKey(Long id);

    int insert(GroupLevel record);

    int insertSelective(GroupLevel record);

    GroupLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupLevel record);

    int updateByPrimaryKey(GroupLevel record);

	/**
	 * 方法描述：获取没有绑定的等级
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月25日下午5:44:21 <br/>
	 * @param groupLevel 
	 * @return
	 */
	List<GroupLevel> getLastLevels(GroupLevel groupLevel);

	/**
	 * 方法描述：根据上一级查找
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月25日下午5:57:05 <br/>
	 * @param lastLevelId
	 * @return
	 */
	List<GroupLevel> getLastLevel(Long lastLevelId);

	/**
	 * 方法描述：等级高于level的自加一
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月26日下午2:01:02 <br/>
	 * @param level
	 */
	void upLevel(Integer level);

	/**
	 * 方法描述：等级+1
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月26日下午2:20:49 <br/>
	 * @param level
	 */
	void downLevel(Integer level);

	@DataSource("joint")
    String queryLevelNameByProformance(@Param("performance") double performance);
}