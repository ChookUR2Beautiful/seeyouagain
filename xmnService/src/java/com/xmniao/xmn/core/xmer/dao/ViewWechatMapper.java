package com.xmniao.xmn.core.xmer.dao;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.xmer.entity.ViewWechat;

@Repository
public interface ViewWechatMapper {
    
	@DataSource("burs")
    int insertSelective(ViewWechat record);
    
}