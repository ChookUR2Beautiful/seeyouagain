package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.ExtensionSet;

public interface ExtensionSetDao extends BaseDao<ExtensionSet> {
	public <T> List<T> getListByType(T t);
}
