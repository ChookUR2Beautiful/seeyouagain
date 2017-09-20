package com.xmniao.xmn.core.fresh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.fresh.dao.SupplierDao;
import com.xmniao.xmn.core.fresh.entity.TSupplier;

/**
 * 项目名称： XmnWeb
 * 类名称： SupplierService.java
 * 类描述：供应商管理service
 * 创建人： lifeng
 * 创建时间： 2016年6月16日下午6:05:39
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Service
public class SupplierService extends BaseService<TSupplier> {
	
	@Autowired
	private SupplierDao supplierDao;

	@Override
	protected BaseDao<TSupplier> getBaseDao() {
		return supplierDao;
	}

	/**
	 * @Description: 查询列表
	 * @Param:tsupplier
	 * @return:List<TSupplier>
	 * @author:lifeng
	 * @time:2016年6月16日下午6:09:34
	 */
	public List<TSupplier> getTSupplierList(TSupplier tSupplier) {
		return supplierDao.getTSupplierList(tSupplier);
	}

	/**
	 * @Description: 查询总记录数
	 * @Param:tsupplier
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月16日下午6:09:56
	 */
	public Long tSupplierCount(TSupplier tSupplier) {
		return supplierDao.tSupplierCount(tSupplier);
	}

	/**
	 * @Description: 添加供应商
	 * @Param:tSupplier
	 * @return:void
	 * @author:lifeng
	 * @time:2016年6月16日下午8:41:35
	 */
	public Integer addSupplier(TSupplier tSupplier) {
		tSupplier.setSdate(new Date());
		return supplierDao.addTSupplier(tSupplier);
	}
	
	/**
	 * @Description: 下拉框查询所有的供应商
	 * @Param:tSupplier
	 * @return:Pageable<TSeller>
	 * @author:lifeng
	 * @time:2016年6月17日上午9:13:54
	 */
	public Pageable<TSupplier> getPageableSupplier(TSupplier tSupplier) {
		Pageable<TSupplier> pageSupplier = new Pageable<TSupplier>(tSupplier);
		//查询有效状态的供应商
		tSupplier.setStatus(0);
		// 供应商列表内容
		pageSupplier.setContent(supplierDao.getTSupplierList(tSupplier));
		return pageSupplier;
	}

	/**
	 * @Description: 删除供应商
	 * @Param:
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月17日下午3:28:16
	 */
	public Integer deleteById(String supplierId) {
		TSupplier tSupplier = new TSupplier();
		tSupplier.setSupplierId(Integer.parseInt(supplierId));
		tSupplier.setStatus(1);//设置状态为失效
		return supplierDao.updateSupplierById(tSupplier);
	}

	/**
	 * @Description: 根据id获取供应商信息
	 * @Param:supplierId
	 * @return:TSupplier
	 * @author:lifeng
	 * @time:2016年6月18日上午10:16:55
	 */
	public TSupplier getTSupplierById(String supplierId) {
		TSupplier tSupplier = new TSupplier();
		if(supplierId != null && !supplierId.equals("") && !supplierId.equals("-请选择")){
			tSupplier.setSupplierId(Integer.parseInt(supplierId));
			tSupplier = supplierDao.getSupplier(tSupplier);
		}
		return tSupplier;
	}

	/**
	 * @Description: 根据id编辑供应商信息
	 * @Param:
	 * @return:void
	 * @author:lifeng
	 * @time:2016年6月22日下午3:21:49
	 */
	public Integer editSupplier(TSupplier tSupplier) {
		tSupplier.setSdate(new Date());
		return supplierDao.updateSupplierById(tSupplier);
	}

	/**
	 * @Description:根据供应商名称查询一条供应商的信息
	 * @Param:supplierName
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月23日下午3:31:33
	 */
	public Object getSupplierBySupplierName(String supplierName) {
		TSupplier tSupplier = new TSupplier();
		List<TSupplier> list = new ArrayList<TSupplier>();
		if(supplierName != null && supplierName.equals("")){
			tSupplier.setSupplierName(supplierName);
			list = supplierDao.getTSupplierList(tSupplier);
		}
		return list;
	}
	
	/**
	 * @Description: 根据供应商的电话查询一条记录
	 * @Param:phone
	 * @return:TSupplier
	 * @author:lifeng
	 * @time:2016年6月30日下午5:30:39
	 */
	public Long getSupplierByPhone(String phone){
		TSupplier tSupplier = new TSupplier();
		tSupplier.setPhone(phone);
		tSupplier = supplierDao.getSupplier(tSupplier);
		if(tSupplier != null){
			return 1l;
		}else{
			return 0l;
		}
	}

}
