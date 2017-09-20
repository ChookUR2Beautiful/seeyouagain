package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.MallPackage;

public interface MallPackageDao extends BaseDao<MallPackage>{
    int deleteByPrimaryKey(Long id);

    int insert(MallPackage record);

    int insertSelective(MallPackage record);

    MallPackage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallPackage record);

    int updateByPrimaryKeyWithBLOBs(MallPackage record);

    int updateByPrimaryKey(MallPackage record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月10日上午11:39:48 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	void updateStatusBatch(@Param("ids")List<String> ids,@Param("status") String status);
}