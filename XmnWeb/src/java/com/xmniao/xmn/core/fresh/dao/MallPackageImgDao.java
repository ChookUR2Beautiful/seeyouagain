package com.xmniao.xmn.core.fresh.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.MallPackageImg;

public interface MallPackageImgDao extends BaseDao<MallPackageImg>{
    int deleteByPrimaryKey(Long id);

    int insert(MallPackageImg record);

    int insertSelective(MallPackageImg record);

    MallPackageImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallPackageImg record);

    int updateByPrimaryKey(MallPackageImg record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月10日上午10:42:06 <br/>
	 * @param id
	 */
	void deletePackageImgs(Long id);
}