package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.CommentMedia;

public interface CommentMediaDao extends BaseDao<CommentMedia>{
    int deleteByPrimaryKey(Integer id);

    int insert(CommentMedia record);

    int insertSelective(CommentMedia record);

    CommentMedia selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentMedia record);

    int updateByPrimaryKey(CommentMedia record);

	/**
	 * 方法描述：加载评论内容
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日下午2:13:29 <br/>
	 * @param id
	 * @return
	 */
	List<CommentMedia> getListByCommentId(Integer id);
}