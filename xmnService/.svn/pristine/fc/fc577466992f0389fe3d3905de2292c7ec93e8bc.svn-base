package com.xmniao.xmn.core.verification.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 多数据源测试
 * @author Administrator
 *
 */
@Repository
public interface DataDao {
    @DataSource("joint")  
    public List<Map<String,Object>> list(); 
}
