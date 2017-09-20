package com.xmniao.xmn.core.businessman.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.UnsignedSeller;

public interface UnsignedSellerDao extends BaseDao<UnsignedSeller>{
    int deleteByPrimaryKey(Integer unsignedSellerid);

    int insert(UnsignedSeller record);

    int insertSelective(UnsignedSeller record);

    UnsignedSeller selectByPrimaryKey(Integer unsignedSellerid);

    int updateByPrimaryKeySelective(UnsignedSeller record);

    int updateByPrimaryKey(UnsignedSeller record);

	/**
	 * 方法描述：更新商铺状态
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月15日下午4:41:13 <br/>
	 * @param unsignedSellerid
	 * @param type
	 * @return
	 */
	int updateState(@Param("unsignedSellerid")Integer unsignedSellerid,@Param("type") Integer type);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日下午5:26:56 <br/>
	 * @param sellerid
	 * @return
	 */
	Map<String, Object> getCommentParam(Integer sellerid);

	/**
	 * 方法描述：检查此商户id是否在此城市
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月25日上午11:01:13 <br/>
	 * @param sellerid
	 * @param provinceId
	 * @param cityId
	 * @return
	 */
	long checkIdAtArea(@Param("id")Integer sellerid,@Param("province") Integer provinceId,@Param("city") Integer cityId);
}