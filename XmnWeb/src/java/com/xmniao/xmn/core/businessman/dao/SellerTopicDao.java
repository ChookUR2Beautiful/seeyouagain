package com.xmniao.xmn.core.businessman.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.SellerTopic;

public interface SellerTopicDao extends BaseDao<SellerTopic>{
    int deleteByPrimaryKey(Integer id);

    int insert(SellerTopic record);

    int insertSelective(SellerTopic record);

    SellerTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerTopic record);

    int updateByPrimaryKey(SellerTopic record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月17日下午6:28:58 <br/>
	 * @param id
	 * @param sort
	 * @return
	 */
	int updateTopicSort(@Param("id")Integer id,@Param("sort") Integer sort);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月17日下午6:33:28 <br/>
	 * @param id
	 * @return
	 */
	int deleteTopic(Integer id);
}