package com.xmn.designer.dao.postage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.designer.entity.postage.PostageConditions;

public interface PostageConditionsDao {
	int deleteByPrimaryKey(Long id);

	int insert(PostageConditions record);

	int insertSelective(PostageConditions record);

	PostageConditions selectByPrimaryKey(Long id);
	/**
	 * 查询邮递价格
	 * @param id
	 * @param deliveryCityNo
	 * @return
	 */
	List<PostageConditions> selectShippingPrice(@Param("supplierId")Long supplierId,@Param("deliveryCityNo")String deliveryCityNo);
	
	int updateByPrimaryKeySelective(PostageConditions record);

	int updateByPrimaryKey(PostageConditions record);

	/**
	 * 
	 * 方法描述：获取指定商户指定区域运费信息 创建人：jianming 创建时间：2016年11月21日 下午3:36:35
	 * 
	 * @param materialId
	 * @param areaId
	 * @return
	 */
	List<PostageConditions> selectCondition(@Param("materialId") Long materialId, @Param("areaId") Integer areaId);

}