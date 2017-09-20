package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContentAttachment;

public interface VstarLiverContentAttachmentDao extends BaseDao<VstarLiverContentAttachment>{
    int deleteByPrimaryKey(Long id);

    int insert(VstarLiverContentAttachment record);

    int insertSelective(VstarLiverContentAttachment record);

    VstarLiverContentAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VstarLiverContentAttachment record);

    int updateByPrimaryKey(VstarLiverContentAttachment record);
    
    void activateContextId(@Param("id")Long id,@Param("list") List<String> asList);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月19日下午2:51:23 <br/>
	 * @param id
	 * @return
	 */
	List<VstarLiverContentAttachment> getListByContentId(Long id);
}