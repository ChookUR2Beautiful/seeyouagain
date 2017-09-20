/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialAttrDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttr;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialAttrService
 * 
 * 类描述： 商品物料规格Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午4:21:38 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialAttrService extends BaseService<DMaterialAttr> {
	
	/**
	 * 注入商品物料规格
	 */
	@Autowired
	private DMaterialAttrDao materialAttrDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return materialAttrDao;
	}
	
	
	/**
	 * 
	 * 方法描述：根据物料Id删除所有规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:07:01 <br/>
	 * @param materialId
	 * @return
	 */
    public int deleteByMaterialId(Long materialId){
    	return materialAttrDao.deleteByMaterialId(materialId);
    }

	/**
	 * 
	 * 方法描述：批量插入物料商品规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:08:35 <br/>
	 * @param list
	 * @return
	 */
    public Integer addBatch(List<DMaterialAttr> list){
    	return materialAttrDao.addBatch(list);
    }
    
    /**
	 * 
	 * 方法描述：插入物料商品规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:08:35 <br/>
	 * @param materialAttr
	 * @return
	 */
    public void add(DMaterialAttr materialAttr){
    	materialAttrDao.add(materialAttr);
    }
    
    /**
	 * 
	 * 方法描述：获取物料商品规格列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:08:35 <br/>
	 * @param materialAttr
	 * @return
	 */
    public List<DMaterialAttr> getList(DMaterialAttr materialAttr){
    	return materialAttrDao.getList(materialAttr);
    }

}
