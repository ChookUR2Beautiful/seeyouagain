package com.xmniao.xmn.core.util.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.holder.DynamicDataSourceHolder;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSource = DynamicDataSourceHolder.getDataSource();
		if(StringUtils.hasLength(dataSource)){
			DynamicDataSourceHolder.remove();
		}
		return dataSource;	
	}

}
