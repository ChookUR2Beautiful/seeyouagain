package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.Kill;

public interface KillDao extends BaseDao<Kill>{
    int deleteByPrimaryKey(Long id);

    int insert(Kill record);

    int insertSelective(Kill record);

    Kill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Kill record);

    int updateByPrimaryKey(Kill record);

	/**
	 * 方法描述：终止活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日上午9:34:59 <br/>
	 * @param id
	 */
	void end(@Param("id")Long id,@Param("state")int state);

	/**
	 * 方法描述：加载下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月1日上午10:03:45 <br/>
	 * @param kill
	 * @return
	 */
	List<Kill> getKillChoose(Kill kill);
}