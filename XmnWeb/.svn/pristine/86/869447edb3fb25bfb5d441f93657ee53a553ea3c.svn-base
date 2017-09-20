/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.util.logging.resources.logging;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.cloud_design.dao.SupplierManageDao;
import com.xmniao.xmn.core.cloud_design.dao.TransportFeeDao;
import com.xmniao.xmn.core.cloud_design.entity.PostTemplate;
import com.xmniao.xmn.core.cloud_design.entity.Supplier;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SupplierService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月16日 上午11:50:06 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class SupplierManageService{
	
	@Autowired
	private SupplierManageDao sManageDao;
	
	@Autowired
	private TransportFeeDao tFeeDao;
	
	private Logger log = Logger.getLogger(SupplierManageService.class);
	/**
	 * 
	 * 方法描述：获取供应商列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月16日下午3:10:54
	 * @param supplier
	 * @return
	 */
	public List<Supplier> getList(Supplier supplier){
		return sManageDao.getList(supplier);
	}
	
	/**
	 * 
	 * 方法描述：统计记录条数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月16日下午3:10:33
	 * @param supplier
	 * @return
	 */
	public long count(Supplier supplier){
		return sManageDao.count(supplier);
	}
	
	/**
	 * 
	 * 方法描述：新增供应商
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日上午10:15:47 <br/>
	 * @param supplier
	 */
	
	public void add(Supplier supplier){
		sManageDao.add(supplier);
		if(supplier.getType()==1){//供应商
			PostTemplate postTemplate = new PostTemplate();
			postTemplate.setId(Long.valueOf(supplier.getPostTemplateId()));
			postTemplate.setSupplierId(Long.valueOf(supplier.getSupplierId()));
			postTemplate.setSupplierName(supplier.getName());
			Integer result = tFeeDao.updateSupplier(postTemplate);
			if (result != 1) {
				log.error("更新运费模板供应商id失败");
				throw new RuntimeException();
			}
		}
	}
	
	/**
	 * 
	 * 方法描述：删除
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午3:38:34 <br/>
	 * @param supplier
	 */
	public void delete(Supplier supplier){
		Integer result = sManageDao.delete(supplier);
		if (result != 1) {
			throw new RuntimeException("删除失败");
		}
	}
	
	/**
	 * 
	 * 方法描述：根据id获取供应商信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月17日下午4:35:32 <br/>
	 * @param supplier
	 * @return
	 */
	public Supplier getSupplier(Supplier supplier){
		return sManageDao.getSupplier(supplier);
	}
	
	public Integer update(Supplier supplier){
		
		if(supplier.getType()==1){//供应商
			PostTemplate postTemplate = new PostTemplate();
			postTemplate.setId(Long.valueOf(supplier.getPostTemplateId()));
			postTemplate.setSupplierId(Long.valueOf(supplier.getSupplierId()));
			postTemplate.setSupplierName(supplier.getName());
			Integer result = tFeeDao.updateSupplier(postTemplate);
			if (result != 1) {
				log.error("更新运费模板供应商id失败");
				throw new RuntimeException();
			}
			//删除旧模板
			tFeeDao.deleteOldTemplate(postTemplate);
		}
		return sManageDao.update(supplier);
	}
	
	/**
	 * 
	 * 方法描述：验证数据
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月16日下午4:37:46 <br/>
	 * @param supplier
	 * @return
	 */
	public boolean checkData(Supplier supplier){
		if(supplier.getType() ==1){
			Long result = sManageDao.checksData(supplier);
			if(result >=1){
				return false;
			}else {
				return true;
			}
		}else if(supplier.getType() ==2){
			Long result = sManageDao.checkdData(supplier);
			if(result >=1){
				return false;
			}else {
				return true;
			}
		}else if(supplier.getType() ==3){
			return true;
		}else {
			return false;
		}
	}
}
