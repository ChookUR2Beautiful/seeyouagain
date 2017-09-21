package com.xmniao.dao.manor;

import com.xmniao.domain.manor.ManorFlower;
import com.xmniao.domain.manor.ManorFlowerBranch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManorFlowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManorFlower record);

    int insertSelective(ManorFlower record);

    ManorFlower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManorFlower record);

    int updateByPrimaryKey(ManorFlower record);

    /** 批量插入数据 */
    int insertBatch(@Param("flowers") List<ManorFlower> flowers);

    /** 根据uid 更新花朵庄园 */
    int updateTypeByUid(@Param("uid") Integer uid, @Param("type") Integer type);

    /** 根据uid 和location  分支下已枯萎未种植的*/
    List<ManorFlower> selectPerishedFlowers(@Param("uid") Integer uid, @Param("branch") ManorFlowerBranch branch);

	 /**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月29日上午11:52:00 <br/>
	 * @param selectAllSubBranchByUid
	 * @param id
	 */
	int updateFlowerByMigrate(@Param("manorFlowerBranchs")List<ManorFlowerBranch> manorFlowerBranchs,@Param("parentId") String parentId);

	 /**
	 * 方法描述：迁移花苗
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月29日下午2:18:30 <br/>
	 * @param newParentUid 新父级uid
	 * @param oldParentUid 旧父级uid
	 * @param childUid	子级uid
	 * @param newParentBranchId 新父级BranchId
	 * @return
	 */
	int updateFlowerSeedlingMigrate(@Param("newParentUid")Integer newParentUid,@Param("oldParentUid") Integer oldParentUid,@Param("childUid") Integer childUid,@Param("newParentBranchId") String newParentBranchId);
	

}