package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterial;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface DMaterialDao extends BaseDao<DMaterial>{

	/**
	 * 
	 * 方法描述：添加物料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:07 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
    void add(DMaterial record);

	/**
	 * 
	 * 方法描述：根据ID查询物料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:18 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    DMaterial selectById(Long id);
	
	/**
	 * 
	 * 方法描述：获取物料列表<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:18 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterial> getList(DMaterial material);
	
	/**
	 * 
	 * 方法描述：统计物料总数<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:18 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
	Long count(DMaterial material);

	/**
	 * 
	 * 方法描述：跟新物料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:28 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
    Integer update(DMaterial record);

	/**
	 * 
	 * 方法描述：批量更新商品状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:25:57 <br/>
	 * @param map
	 */
	@DataSource(value="designer")
	void updateBatch(Map<String,Object> map);
}