/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialAttrGroupDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttrGroup;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialAttrGroupService
 * 
 * 类描述： 商品物料分组信息Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午7:57:02 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialAttrGroupService extends BaseService<DMaterialAttrGroup> {
	
	@Autowired
	private DMaterialAttrGroupDao materialAttrGroupDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return materialAttrGroupDao;
	}
	
	/**
	 * 
	 * 方法描述：根据物料ID删除商品物料规格分组信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午7:55:37 <br/>
	 * @param materialId
	 * @return
	 */
    public int deleteByMaterialId(Long materialId){
    	return materialAttrGroupDao.deleteByMaterialId(materialId);
    }


    /**
     * 
     * 方法描述：在此处添加方法描述 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-24下午7:51:58 <br/>
     * @param record
     * @return
     */
    public Integer addBatch(List<DMaterialAttrGroup> list){
    	return materialAttrGroupDao.addBatch(list);
    }

    /**
     * 
     * 方法描述：获取商品物料规格分组信息列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-24下午7:51:58 <br/>
     * @param record
     * @return
     */
    public List<DMaterialAttrGroup> getList(DMaterialAttrGroup attrGroup){
    	return materialAttrGroupDao.getList(attrGroup);
    }

}
