package com.xmniao.xmn.core.util.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 数据源获取
 * @author Administrator
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSouce();
	}  
}
