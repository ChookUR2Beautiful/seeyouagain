package com.xmniao.dao.manor;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.manor.ManorFlowerBranchChain;

public interface ManorFlowerBranchChainMapper {
    int deleteByPrimaryKey(ManorFlowerBranchChain key);

    int insert(ManorFlowerBranchChain record);

    int insertSelective(ManorFlowerBranchChain record);

	 /**
	 * 方法描述：查询节点上所有父Id
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月28日下午5:07:04 <br/>
	 * @param id
	 * @return
	 */
	List<String> selectByBranchId(String id);

	 /**
	 * 方法描述：根据BranchId和ParentId删除数据
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月28日下午5:17:18 <br/>
	 * @param pId
	 * @param id
	 */
	int deleteByBranchIdAndParentId(@Param("branchId")String branchId,@Param("parentId") String parentId);
	
}