/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.ActivityProductDao;
import com.xmniao.xmn.core.fresh.dao.FreshImageDao;
import com.xmniao.xmn.core.fresh.dao.HotBrandDao;
import com.xmniao.xmn.core.fresh.dao.ModuleDao;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.fresh.entity.Module;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：ModuleService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年1月5日 下午3:52:54 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class ModuleService extends BaseService<Module> {

	@Autowired
	private ModuleDao moduleDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return moduleDao;
	}
	
	@Autowired
	private ActivityProductDao activityProductDao;
	
	@Autowired
	private  FreshImageDao freshImageDao;
	
	@Autowired
	private HotBrandDao hotBrandDao;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 方法描述：保存专场模块
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月5日下午9:05:13 <br/>
	 * @param module
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDediModule(Module module) {
		try {
			module.setUpdateTime(new Date());
			module.setModuleType(3);
			if(module.getId()!=null){
				//修改
				moduleDao.update(module);
				activityProductDao.deleteByModuleUpdate(module.getId());
			}else{
				module.setCreateTime(new Date());
				moduleDao.add(module);
			}
			String productJsonString = module.getProductJsonString();
			if(StringUtils.isNotBlank(productJsonString)){
				JSONObject fromObject = JSONObject.fromObject(productJsonString);
				JSONArray jsonArray = fromObject.getJSONArray("json");
				for (Object object : jsonArray) {
					ActivityProduct activityProduct = MAPPER.readValue(object.toString(),ActivityProduct.class);
					activityProduct.setModuleId(module.getId());
					activityProduct.setType(1);
					activityProduct.setCreateTime(new Date());
					activityProduct.setUpdateTime(new Date());
					activityProductDao.add(activityProduct);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存专场商品时出现异常");
		}
	}

	/**
	 * 方法描述：删除banner模块
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日下午4:39:39 <br/>
	 * @param typeId
	 * @param type 
	 */
	public void deleteBanner(Integer typeId, Integer type) {
		freshImageDao.deleteBanner(typeId,type);
	}

	/**
	 * 方法描述：删除热门品牌模块
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日下午5:00:31 <br/>
	 * @param typeId
	 */
	public void deleteBrand(Integer typeId) {
		hotBrandDao.deleteBrand(typeId);
	}

	/**
	 * 方法描述：删除模块
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日下午5:11:26 <br/>
	 * @param id
	 */
	public void deleteModule(Long id) {
		moduleDao.deleteModule(id);
	}

}
