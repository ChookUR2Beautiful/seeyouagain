package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarousel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCarouselDao
 * 
 * 类描述： 物料模板Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午9:17:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialCarouselDao extends BaseDao<DMaterialCarousel>{
	
	/**
	 * 
	 * 方法描述：根据ID删除物料模板 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午7:40:40 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    int deleteById(Long id);

	/**
	 * 
	 * 方法描述：添加物料模板 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午7:40:40 <br/>
	 * @param record
	 * @return
	 */
    @DataSource(value="designer")
    void add(DMaterialCarousel record);

    /**
     * 
     * 方法描述：根据ID查询物料模板 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-5下午7:41:36 <br/>
     * @param id
     * @return
     */
    @DataSource(value="designer")
    DMaterialCarousel selectById(Long id);
    
    /**
     * 
     * 方法描述：获取物料模板列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-5下午7:41:36 <br/>
     * @param record
     * @return
     */
    @DataSource(value="designer")
    List<DMaterialCarousel> getList(DMaterialCarousel record);

    /**
     * 
     * 方法描述：更新物料模板 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-5下午7:41:36 <br/>
     * @param record
     * @return
     */
    @DataSource(value="designer")
    Integer update(DMaterialCarousel record);

}