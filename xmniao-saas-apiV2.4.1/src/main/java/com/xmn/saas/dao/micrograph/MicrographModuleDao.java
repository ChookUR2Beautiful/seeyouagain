package com.xmn.saas.dao.micrograph;

import java.util.List;

import com.xmn.saas.entity.micrograph.MicrographModule;

public interface MicrographModuleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicrographModule record);

    int insertSelective(MicrographModule record);

    MicrographModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicrographModule record);

    int updateByPrimaryKey(MicrographModule record);
    
    /**
     * 
     * 方法描述：查询模块列表
     * 创建人：jianming  
     * 创建时间：2016年12月2日 下午2:41:26   
     * @param id
     * @return
     */
	List<MicrographModule> selectByPageId(Integer id);
}