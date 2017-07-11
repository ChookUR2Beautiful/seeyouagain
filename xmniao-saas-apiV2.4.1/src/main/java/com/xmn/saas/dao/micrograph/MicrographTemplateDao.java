package com.xmn.saas.dao.micrograph;

import java.util.List;
import java.util.Map;
import com.xmn.saas.entity.micrograph.MicrographTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MicrographTemplateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicrographTemplate record);

    int insertSelective(MicrographTemplate record);

    MicrographTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicrographTemplate record);

    int updateByPrimaryKey(MicrographTemplate record);
    
    /**
     * 
     * 方法描述：从低到高排序查询
     * 创建人：jianming  
     * 创建时间：2016年11月30日 下午5:00:54   
     * @param pageSize
     * @param pageIndex
     * @param tag 
     * @param searchName 
     * @return
     */
	List<MicrographTemplate> selecDownSerial(@Param("pageSize") Integer pageSize, @Param("pageIndex")Integer pageIndex, @Param("tag")Integer tag,@Param("searchName") String searchName);
	
	/**
	 * 
	 * 方法描述：从高到低排序查询
	 * 创建人：jianming  
	 * 创建时间：2016年11月30日 下午5:07:43   
	 * @param pageSize
	 * @param pageIndex
	 * @param tag 
	 * @param searchName 
	 * @return
	 */
	List<MicrographTemplate> selectUpSerial(@Param("pageSize")Integer pageSize,@Param("pageIndex") Integer pageIndex, @Param("tag")Integer tag,@Param("searchName") String searchName);
	
	/**
	 * 
	 * 方法描述：综合排序
	 * 创建人：jianming  
	 * 创建时间：2016年11月30日 下午5:11:36   
	 * @param pageSize
	 * @param pageIndex
	 * @param tag 
	 * @return
	 */
	List<MicrographTemplate> selectBySerial(@Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex, @Param("tag")Integer tag,@Param("searchName") String searchName);
	
	/**
	 * 
	 * 方法描述：关键字模糊搜索
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午2:20:07   
	 * @param name
	 * @return
	 */
	List<Map<String, String>> searchLike(@Param("searchName")String searchName);
	
	/**
	 * 
	 * 方法描述：根据模块id查询模板
	 * 创建人：jianming  
	 * 创建时间：2016年12月3日 下午5:42:30   
	 * @param pageId
	 * @return
	 */
	MicrographTemplate selectByModelId(Integer pageId);
	
	/**
	 * 
	 * 方法描述：制造人数加一
	 * 创建人：jianming  
	 * 创建时间：2016年12月9日 上午10:06:32   
	 * @param id
	 */
	void updateSoldTimes(Integer id);
	
	/**
	 * 
	 * 方法描述：根据页面擦查询模板
	 * 创建人：jianming  
	 * 创建时间：2016年12月14日 下午9:10:56   
	 * @param pageId
	 * @return
	 */
	MicrographTemplate selectByPageId(Integer pageId);
}