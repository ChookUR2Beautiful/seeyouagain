package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategory;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface DMaterialCategoryDao  extends BaseDao<DMaterialCategory>{
	
	/**
	 * 
	 * 方法描述：根据主键删除分类 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-16下午6:32:54 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value = "designer")
    int deleteById(Long id);


    /**
     * 
     * 方法描述：添加物料分类 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    @DataSource(value="designer")
    void add(DMaterialCategory record);

    /**
     * 
     * 方法描述：根据主键查询分类 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    @DataSource(value="designer")
    DMaterialCategory selectById(Long id);

    /**
     * 
     * 方法描述：更新物料分类 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    @DataSource(value="designer")
    Integer update(DMaterialCategory record);
    
    
    /**
     * 
     * 方法描述：获取物料分类列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    @DataSource(value="designer")
    List<DMaterialCategory> getList(DMaterialCategory record);
    
    /**
     * 
     * 方法描述：获取物料分类总数 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    @DataSource(value="designer")
    Long count(DMaterialCategory record);

}