package com.xmniao.xmn.core.manor.dao;

import com.xmniao.xmn.core.manor.entity.ManorFlowerBranch;
import com.xmniao.xmn.core.manor.entity.ManorFlowerCount;
import com.xmniao.xmn.core.manor.entity.TManorFlowerRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManorFlowerBranchMapper {
    int deleteByPrimaryKey(String id);

    int insert(ManorFlowerBranch record);

    int insertSelective(ManorFlowerBranch record);

    @DataSource("joint")
    ManorFlowerBranch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ManorFlowerBranch record);

    int updateByPrimaryKey(ManorFlowerBranch record);

    @DataSource("joint")
    List<ManorFlowerBranch> selectAllSubBranch(@Param("uid") Integer uid);

    @DataSource("joint")
    int countByTypeInBranch(@Param("flowerType") int flowerType, @Param("branch") ManorFlowerBranch branch);

    @DataSource("joint")
    int countByTypeAndFloristInBranch(@Param("flowerType") int flowerType, @Param("uid") Integer uid, @Param("branch") ManorFlowerBranch branch);


    /** 按花朵类型统计分支节点下所有的未枯萎花朵 */
    ManorFlowerCount countFlowerByTypeInBranch(@Param("branch") ManorFlowerBranch branch,
                                               @Param("types") int[] types,
                                               @Param("perishType") int perishType,
                                               @Param("shareTypes") int[] shareTypes

    );

    /** 按花朵类型统计分支节点下自己种值未枯萎花朵 */
    ManorFlowerCount countFlowerByTypeAndFloristInBranch(@Param("branch") ManorFlowerBranch branch,
                                                         @Param("types") int[] types,
                                                         @Param("floristUid") Integer floristUid,
                                                         @Param("perishType") int perishType,
                                                         @Param("shareTypes") int[] shareTypes
    );

    /**
	 * 方法描述：查询没有下级的花田
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日上午10:06:11 <br/>
	 * @param ursList 根据用户列表查询,可传null
	 * @return
	 */
	List<ManorFlowerBranch> getNotChildsFlowerRelation(@Param("list")List<Burs> ursList);

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日下午2:31:27 <br/>
	 * @param uid
	 * @return
	 */
	List<ManorFlowerBranch> getByUid(Integer uid);


}