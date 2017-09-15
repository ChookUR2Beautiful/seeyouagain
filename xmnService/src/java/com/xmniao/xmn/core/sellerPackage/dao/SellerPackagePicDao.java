package com.xmniao.xmn.core.sellerPackage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.sellerPackage.entity.SellerPackagePic;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerPackagePicDao    
* @Description:套餐图片dao   
* @author: liuzhihao   
* @date: 2017年2月20日 上午9:52:23
 */
@Repository
public interface SellerPackagePicDao {
    
	@DataSource("joint")
	int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    SellerPackagePic selectByPrimaryKey(Integer id);
	
	@DataSource("joint")
	List<String> getCoverImage(@Param("pid")Integer pid,@Param("type")Integer type);

	@DataSource("joint")
	List<Map<Object, Object>> getCoverImageByIds(Map<Object, Object> param);

}