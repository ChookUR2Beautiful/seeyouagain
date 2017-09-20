package com.xmniao.xmn.core.live_anchor.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveSalary;

public interface TLiveSalaryDao extends BaseDao<TLiveSalary>{
    int deleteByPrimaryKey(Integer id);

    int insert(TLiveSalary record);

    int insertSelective(TLiveSalary record);

    TLiveSalary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TLiveSalary record);

    int updateByPrimaryKey(TLiveSalary record);

	/**
	 * 方法描述：修改工资
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月6日下午2:07:34 <br/>
	 * @param id
	 * @param afterSalary
	 * @return
	 */
	int updateSalary(@Param("id")Integer id, @Param("afterSalary")BigDecimal afterSalary);

	/**
	 * 方法描述：确认工资
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月6日下午3:11:01 <br/>
	 * @param id
	 */
	void confirmSalary(Integer id);
}