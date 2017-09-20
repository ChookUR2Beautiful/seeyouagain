package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.live_anchor.entity.TLiveSalaryFail;

public interface TLiveSalaryFailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TLiveSalaryFail record);

    int insertSelective(TLiveSalaryFail record);

    TLiveSalaryFail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TLiveSalaryFail record);

    int updateByPrimaryKey(TLiveSalaryFail record);

	/**
	 * 方法描述：活动失败列表
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月11日下午5:09:22 <br/>
	 * @return
	 */
	List<TLiveSalaryFail> getFailList();
}