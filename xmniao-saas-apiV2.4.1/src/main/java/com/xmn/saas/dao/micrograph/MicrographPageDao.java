package com.xmn.saas.dao.micrograph;

import java.util.List;

import com.xmn.saas.entity.micrograph.MicrographPage;
import com.xmn.saas.entity.micrograph.MicrographTemplate;

public interface MicrographPageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicrographPage record);

    int insertSelective(MicrographPage record);

    MicrographPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicrographPage record);

    int updateByPrimaryKey(MicrographPage record);
    
    /**
     * 
     * 方法描述：查询列表
     * 创建人：jianming  
     * 创建时间：2016年12月2日 上午11:15:43   
     * @param id
     * @return
     */
	List<MicrographPage> selectListByPrimaryKey(Integer id);
	
}