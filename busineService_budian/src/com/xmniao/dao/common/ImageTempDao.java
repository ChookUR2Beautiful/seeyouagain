/**
 * 
 */
package com.xmniao.dao.common;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.common.ImageTempEntity;

/**
 * 
 * 项目名称：busineService_manor
 * 
 * 类名称：ImageTempDao
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年6月9日 下午2:02:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public interface ImageTempDao {
	
	List<ImageTempEntity> getImageList();
	
	int removeImage(List<Long> list);
}
