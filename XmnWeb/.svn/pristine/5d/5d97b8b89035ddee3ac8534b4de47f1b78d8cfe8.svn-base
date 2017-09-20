package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.TFreshLabel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TFreshLabelDao extends BaseDao<TFreshLabel>{
	@DataSource("master")
    int deleteByPrimaryKey(Integer id);

	@DataSource("master")
    int insert(TFreshLabel record);

	@DataSource("master")
    int updateByPrimaryKey(TFreshLabel record);
    
    
    /*   自定义查询区域*/
	@DataSource("master")
	List<TFreshLabel> getListByIds(Object[] ids);
	
	@DataSource("master")
	List<TFreshLabel> getLabelInfoList(TFreshLabel record);

	/**
	 * 方法描述：加载下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月3日上午9:51:23 <br/>
	 * @param freshLabel
	 * @return
	 */
	List<TFreshLabel> getLabelChoose(TFreshLabel freshLabel);
	
	
}