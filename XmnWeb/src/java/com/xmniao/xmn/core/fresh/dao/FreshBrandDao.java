package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.FreshBrand;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface FreshBrandDao extends BaseDao<FreshBrand>{
    int deleteByPrimaryKey(Integer id);

    int insert(FreshBrand record);

    int insertSelective(FreshBrand record);

    FreshBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshBrand record);

    int updateByPrimaryKey(FreshBrand record);

	/**
	 * 方法描述：分页条件查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月22日下午4:24:18 <br/>
	 * @param brand
	 * @return
	 */
    @DataSource("slave")
	List<FreshBrand> selectByPage(FreshBrand brand);

	/**
	 * 方法描述：统计条数
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月22日下午4:34:38 <br/>
	 * @param brand
	 * @return
	 */
    @DataSource("slave")
	Long countTotal(FreshBrand brand);

	/**
	 * 方法描述：删除
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月23日上午10:35:33 <br/>
	 * @param ids
	 */
	void deleteMine(@Param("ids")String ids);

	/**
	 * 方法描述：查询根据导出条件
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月24日下午2:32:33 <br/>
	 * @param brand
	 * @return
	 */
	List<FreshBrand> selectExport(FreshBrand brand);

	/**
	 * 方法描述：批量添加
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月25日下午5:56:02 <br/>
	 * @param brandInfo
	 */
	void addBatchMine(List<FreshBrand> brandInfo);

	/**
	 * 方法描述：获取所有品牌
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月26日下午6:01:53 <br/>
	 * @return
	 */
	List<FreshBrand> findAll();

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月11日下午6:20:53 <br/>
	 * @param ids
	 * @return
	 */
	List<FreshBrand> hasProduct(@Param("ids")String ids);
}