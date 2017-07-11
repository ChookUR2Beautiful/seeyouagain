package com.xmn.saas.dao.micrograph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.micrograph.MicrographSearch;

public interface MicrographSearchDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicrographSearch record);

    int insertSelective(MicrographSearch record);

    MicrographSearch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicrographSearch record);

    int updateByPrimaryKey(MicrographSearch record);
    
    /**
     * 
     * 方法描述：保存搜索记录
     * 创建人：jianming  
     * 创建时间：2016年12月1日 下午4:47:01   
     * @param searchName
     * @param sellerId
     */
	void insert(@Param("searchName")String searchName, @Param("sellerid")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询用户搜索记录
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午5:10:47   
	 * @param sellerId
	 * @return
	 */
	List<MicrographSearch> selectBySellerid(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询搜索字段
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午5:25:32   
	 * @param searchName
	 * @param sellerid
	 * @return
	 */
	MicrographSearch selectTitle(@Param("title")String searchName, @Param("sellerid")Integer sellerid);
	
	/**
	 * 
	 * 方法描述：修改字段日期
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午5:29:26   
	 * @param old
	 */
	void updateCreateTime(MicrographSearch old);
	
	/**
	 * 
	 * 方法描述：删除历史记录
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午8:13:20   
	 * @param sellerId
	 */
	void deleteSearch(Integer sellerId);
	
	
}