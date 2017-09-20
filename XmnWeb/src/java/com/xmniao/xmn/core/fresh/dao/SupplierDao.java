package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.TSupplier;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称： XmnWeb
 * 类名称： SupplierDao.java
 * 类描述：供应商管理dao
 * 创建人： lifeng
 * 创建时间： 2016年6月16日下午6:07:42
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Repository
public interface SupplierDao extends BaseDao<TSupplier> {

	/**
	 * @Description: 查询列表
	 * @Param:tsupplier
	 * @return:List<TSupplier>
	 * @author:lifeng
	 * @time:2016年6月16日下午6:09:39
	 */
	@DataSource("slave")
	List<TSupplier> getTSupplierList(TSupplier tSupplier);

	/**
	 * @Description: 查询总记录数
	 * @Param:tsupplier
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月16日下午6:09:58
	 */
	@DataSource("slave")
	Long tSupplierCount(TSupplier tSupplier);

	/**
	 * @Description: 保存
	 * @Param:tSupplier
	 * @return:void
	 * @author:lifeng
	 * @time:2016年6月16日下午8:42:29
	 */
	@DataSource("write")
	Integer addTSupplier(TSupplier tSupplier);

	/**
	 * @Description: 根据id更新数据
	 * @Param:tSupplier
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月17日下午3:30:06
	 */
	@DataSource("write")
	Integer updateSupplierById(TSupplier tSupplier);

	/**
	 * @Description: 根据id查询一条记录
	 * @Param:TSupplier
	 * @return:TSupplier
	 * @author:lifeng
	 * @time:2016年6月18日上午10:19:25
	 */
	@DataSource("slave")
	TSupplier getSupplier(TSupplier tSupplier);
	
	/**
	 * 根据供应商名称查询供应商信息
	 * 创建人： huang'tao
	 * 创建时间：2016-7-15上午11:14:52
	 * @param tSupplier
	 * @return
	 */
	@DataSource("slave")
	TSupplier getSupplierBySupplierName(TSupplier tSupplier);

}
