package com.xmniao.xmn.core.businessman.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.CatehomeMustBuy;

public interface CatehomeMustBuyDao extends BaseDao<CatehomeMustBuy>{
    int deleteByPrimaryKey(Long id);

    int insert(CatehomeMustBuy record);

    int insertSelective(CatehomeMustBuy record);

    CatehomeMustBuy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CatehomeMustBuy record);

    int updateByPrimaryKey(CatehomeMustBuy record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日上午10:29:28 <br/>
	 * @param id
	 * @param sort
	 * @return
	 */
	int updateMustbuySort(@Param("id")Integer id,@Param("sort") Integer sort);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日上午10:32:47 <br/>
	 * @param id
	 * @return
	 */
	int deleteMustbuy(Integer id);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日上午10:37:43 <br/>
	 * @param type
	 * @return
	 */
	Long countByType(Integer type);
}