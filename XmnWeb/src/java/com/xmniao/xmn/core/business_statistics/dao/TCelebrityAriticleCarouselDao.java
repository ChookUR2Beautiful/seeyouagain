package com.xmniao.xmn.core.business_statistics.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_statistics.entity.TCelebrityAriticleCarousel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrityAriticleCarouselMapper
 * 
 * 类描述：SaaS网红文章相册Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午11:11:24 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TCelebrityAriticleCarouselDao extends BaseDao<TCelebrityAriticleCarousel>{
	
	/**
	 * 
	 * 方法描述：批量添加相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-18下午4:43:38
	 * @param record
	 * @return
	 */
    @DataSource(value = "slave")
    public Integer addPhotoBatch(List<TCelebrityAriticleCarousel> imageList);
    
    /**
     * 
     * 方法描述：根据文章ID删除相册 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-13下午3:01:08 <br/>
     * @param celebrityAriticleId
     * @return
     */
    @DataSource(value = "slave")
    public int deleteByAriticleId(Long celebrityAriticleId);
}