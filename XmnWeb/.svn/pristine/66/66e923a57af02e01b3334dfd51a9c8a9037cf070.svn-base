package com.xmniao.xmn.core.businessman.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.ClassifyTag;

public interface ClassifyTagDao extends BaseDao<ClassifyTag>{
    int deleteByPrimaryKey(Integer id);

    int insert(ClassifyTag record);

    int insertSelective(ClassifyTag record);

    ClassifyTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassifyTag record);

    int updateByPrimaryKey(ClassifyTag record);

	/**
	 * 方法描述：根据ids获得名字和Id
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月17日下午5:33:12 <br/>
	 * @param array
	 * @return
	 */
	List<Map<String, Object>> selectTagNameByIds(Object[] array);
}