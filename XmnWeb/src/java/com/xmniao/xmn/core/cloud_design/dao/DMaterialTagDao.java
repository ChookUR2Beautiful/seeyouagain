package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialTag;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialTagDao
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-9-27 下午4:05:08
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialTagDao extends BaseDao<DMaterialTag>{

	/**
	 * 
	 * 方法描述：根据主键删除物料标签 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-27下午4:48:22 <br/>
	 * 
	 * @param id
	 * @return
	 */
	@DataSource(value = "designer")
	int deleteById(Long id);

	/**
	 * 
	 * 方法描述：添加物料标签 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-27下午4:46:13 <br/>
	 * 
	 * @param record
	 * @return
	 */
	@DataSource(value = "designer")
	int addReturnId(DMaterialTag record);

	/**
	 * 
	 * 方法描述：根据主键查询物料标签 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-27下午4:48:34 <br/>
	 * 
	 * @param id
	 * @return
	 */
	@DataSource(value = "designer")
	DMaterialTag selectById(Long id);
	
	/**
	 * 
	 * 方法描述：获取物料标签列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-28上午9:49:41 <br/>
	 * @param materialTag
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialTag> getList(DMaterialTag materialTag);
	
	/**
	 * 
	 * 方法描述：统计符合条件的标签总数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-28下午2:53:23 <br/>
	 * @param materialTag
	 * @return
	 */
	@DataSource(value="designer")
	Long count(DMaterialTag materialTag);

	/**
	 * 
	 * 方法描述：更新物料标签 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-27下午4:57:45 <br/>
	 * 
	 * @param record
	 * @return
	 */
	@DataSource(value = "designer")
	Integer update(DMaterialTag record);
}