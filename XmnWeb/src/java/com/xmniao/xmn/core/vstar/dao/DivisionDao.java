package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.vstar.entity.Division;

public interface DivisionDao extends BaseDao<Division>{
    int deleteByPrimaryKey(Integer id);

    int insert(Division record);

    int insertSelective(Division record);

    Division selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Division record);

    int updateByPrimaryKey(Division record);

    /**
	 * 方法描述：删除部分城市
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月8日下午3:52:32 <br/>
	 * @param division
	 */
	public void deleteCityNotInIds(Division division);
	
	
	/**
	 * 
	 * 方法描述：获取赛区基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-19下午3:17:32 <br/>
	 * @param division
	 * @return
	 */
	@DataSource(value="slave")
	List<Division> getBaseList(Division division);
		
	
}