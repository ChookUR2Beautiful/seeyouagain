package com.xmniao.xmn.core.vstar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.FashionTickets;

public interface FashionTicketsDao extends BaseDao<FashionTickets>{
    int deleteByPrimaryKey(Integer id);

    int insert(FashionTickets record);

    int insertSelective(FashionTickets record);

    FashionTickets selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FashionTickets record);

    int updateByPrimaryKeyWithBLOBs(FashionTickets record);

    int updateByPrimaryKey(FashionTickets record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午6:37:30 <br/>
	 * @param asList
	 * @return
	 */
	List<Map<String, Object>> checkSeatHasPrice(List<String> asList);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月3日下午1:44:34 <br/>
	 * @param id
	 * @param status 
	 */
	void end(@Param("id")Integer id,@Param("status") Integer status);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午2:35:00 <br/>
	 * @return
	 */
	List<FashionTickets> getSellIngTickets();
}