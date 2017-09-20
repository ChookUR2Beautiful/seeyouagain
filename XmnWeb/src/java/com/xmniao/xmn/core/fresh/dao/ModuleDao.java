package com.xmniao.xmn.core.fresh.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.Module;

public interface ModuleDao extends BaseDao<Module>{
    int deleteByPrimaryKey(Long id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

	/**
	 * 方法描述：删除模块
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日下午5:20:20 <br/>
	 * @param id
	 */
	void deleteModule(Long id);

}