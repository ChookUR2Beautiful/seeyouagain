package com.xmniao.xmn.core.businessman.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TexpertComment;

/**
 *@ClassName:SalesmanManagementDao
 *@Description:合作商业务员管理Dao层
 *@author hls
 *@date:2016年3月15日上午9:26:50
 */
public interface ExpertManagementDao extends BaseDao<TexpertComment>{
	/**
     * @Title:getTexpertComment
     * @Description:查询达人评论列表
     * @param expertComment
     * @return List<TexpertComment>
     * @throw
     */
    public List<TexpertComment> getTexpertComment(TexpertComment expertComment);
    /**
     * @Title:getTexpertCommentCount
     * @Description:查询达人评论记录条数
     * @param expertComment
     * @return Long
     * @throw
     */
    public Long getTexpertCommentCount(TexpertComment expertComment);
    /**
     * 
     * @Title:getTexpertCommentById
     * @Description:根据达人Id查询达人评论信息用于初始化达人评论修改页面
     * @param id
     * @return TexpertComment
     * @throw
     */
    public TexpertComment getTexpertCommentById(Long id);
    /**
     * @Title:getSellerLabelCount
     * @Description:查询商家标签个数
     * @param expertComment
     * @return Long
     * @throw
     */
    public Integer getSellerLabelCount(TexpertComment expertComment);
    

}
