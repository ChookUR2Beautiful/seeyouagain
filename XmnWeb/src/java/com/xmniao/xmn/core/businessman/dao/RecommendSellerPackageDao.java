package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.RecommendSellerPackage;
import com.xmniao.xmn.core.businessman.entity.RecommendSellerPackageWithBLOBs;

public interface RecommendSellerPackageDao extends BaseDao<RecommendSellerPackage>{
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendSellerPackageWithBLOBs record);

    int insertSelective(RecommendSellerPackageWithBLOBs record);

    RecommendSellerPackageWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendSellerPackageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RecommendSellerPackageWithBLOBs record);

    int updateByPrimaryKey(RecommendSellerPackage record);

	/**
	 * 方法描述：加载下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午7:43:25 <br/>
	 * @param sellerPackage
	 * @return
	 */
	List<RecommendSellerPackage> getPackageChoose(RecommendSellerPackage sellerPackage);


	/**
	 * 方法描述：修改排序
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午8:09:39 <br/>
	 * @param id
	 * @param homeSort
	 */
	void updateHomeSort(@Param("id")Integer id,@Param("homeSort") Integer homeSort);
}