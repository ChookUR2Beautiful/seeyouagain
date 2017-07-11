package com.xmn.saas.dao.celebrity;

import java.util.List;

import com.xmn.saas.entity.celebrity.Tag;

public interface TagDao {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
    
    /**
     * 
     * 方法描述：获取默认排序标签
     * 创建人：jianming  
     * 创建时间：2016年12月1日 上午9:54:45   
     * @return
     */
	List<Tag> selectTagsBySerial();
}