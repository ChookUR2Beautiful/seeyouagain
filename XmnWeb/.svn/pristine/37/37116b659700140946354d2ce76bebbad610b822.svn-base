/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryAttrValDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrVal;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttrValService
 * 
 * 类描述： 物料规格细项服务Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-30 下午3:07:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialCategoryAttrValService {
	
	@Autowired
	private DMaterialCategoryAttrValDao categoryAttrValDao;
	
	/**
	 * 
	 * 方法描述：删除物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:04:44 <br/>
	 * @param id
	 * @return
	 */
    public int deleteById(Long id){
    	return categoryAttrValDao.deleteById(id);
    }


	/**
	 * 
	 * 方法描述：添加物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
    public int add(DMaterialCategoryAttrVal record){
    	return categoryAttrValDao.add(record);
    }

	/**
	 * 
	 * 方法描述：根据ID查询物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
    public DMaterialCategoryAttrVal selectById(Long id){
    	return categoryAttrValDao.selectById(id);
    }
    
    /**
	 * 
	 * 方法描述：查询物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
    public List<DMaterialCategoryAttrVal> getList(DMaterialCategoryAttrVal categoryAttrVal){
    	return categoryAttrValDao.getList(categoryAttrVal);
    }

	/**
	 * 
	 * 方法描述：更新物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
   public int update(DMaterialCategoryAttrVal record){
	   return categoryAttrValDao.update(record);
   }

}
