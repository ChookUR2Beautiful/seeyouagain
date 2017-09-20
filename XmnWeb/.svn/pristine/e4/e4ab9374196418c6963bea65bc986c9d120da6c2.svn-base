/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCarouselSourceDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarouselSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCarouselSourceService
 * 
 * 类描述： 物料元数据Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-26 下午2:36:00 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialCarouselSourceService extends BaseService<DMaterialCarouselSource> {
	
	@Autowired
	private DMaterialCarouselSourceDao carouselSrcDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return carouselSrcDao;
	}
	
	
	/**
	 * 
	 * 方法描述：获取物料元数据列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-26上午11:22:10 <br/>
	 * @param materialId
	 * @return
	 */
	public List<DMaterialCarouselSource> getList(DMaterialCarouselSource carouselSource){
		return carouselSrcDao.getList(carouselSource);
	}
	
	/**
	 * 
	 * 方法描述：删除物料元数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-26上午11:29:44 <br/>
	 * @param carouselSource
	 * @return
	 */
    public  int delete(DMaterialCarouselSource carouselSource){
    	return carouselSrcDao.delete(carouselSource);
    }
	
	/**
	 * 
	 * 方法描述：批量添加物料元数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-26上午11:29:44 <br/>
	 * @param carouselSource
	 * @return
	 */
	public Integer addBatch(List<DMaterialCarouselSource> list){
		return carouselSrcDao.addBatch(list);
	}


	/**
	 * 方法描述：获取图片、文字框架信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6上午10:08:39 <br/>
	 * @param carouselSourceReq
	 * @return
	 */
	public List<DMaterialCarouselSource> getSourceList( DMaterialCarouselSource carouselSourceReq) {
		return carouselSrcDao.getSourceList(carouselSourceReq);
	}

}
