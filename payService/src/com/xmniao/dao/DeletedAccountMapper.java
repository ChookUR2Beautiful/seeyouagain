package com.xmniao.dao;

import java.util.Map;

/**
 * 解除绑定银行卡表Dao
 * @author Administrator
 *
 */
public interface DeletedAccountMapper {
	
	int addDeletedRecord(Map<String,Object> map);
}
