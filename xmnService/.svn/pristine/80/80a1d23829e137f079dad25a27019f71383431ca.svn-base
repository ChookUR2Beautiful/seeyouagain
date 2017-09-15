package com.xmniao.xmn.core.seller.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.seller.entity.ExperienceComment;
import com.xmniao.xmn.core.seller.entity.ExperienceCommentMedia;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
 * @ClassName:ExperienceCommentDao
 * @Description:美食探店点评 dao
 * @Author:xw
 * @Date:2017年5月13日下午2:37:20
 */
@Repository
public interface ExperienceCommentDao {

	/**
	 * 
	 * @Title:listExperienceCommentBySellerid
	 * @Description:查询店铺网红点评列表
	 * @param sellerid	店铺的sellerid,sellerType
	 * @return List<ExperienceComment> 网红店铺list集合
	 * 2017年5月13日下午2:50:02
	 */
	@DataSource("joint")
	List<ExperienceComment> listExperienceCommentBySellerid(@Param(value = "sellerid") Integer sellerid, @Param(value = "sellerType") Integer sellerType);

	/**
	 * 查询商家图片
	 * @Title:listSellerPhotos
	 * @Description:商家相册
	 * @param sellerid 店铺列表
	 * @param page 页码
	 * @param pageSize 分页size
	 * @return List<ExperienceCommentMedia> 点评图片集合
	 * 2017年5月15日下午2:31:47
	 */
	@DataSource("joint")
	List<ExperienceCommentMedia> listSellerPhotos(@Param(value = "sellerid") Integer sellerid, @Param(value = "page") Integer page, @Param(value = "pageSize") Integer pageSize);

	/**
	 * 
	 * @Title:addComment
	 * @Description:新增评论
	 * @param comment 点评实体类
	 * 2017年5月16日下午2:03:04
	 */
	@DataSource("joint")
	void addComment(ExperienceComment comment);

	/**
	 * 
	 * @Title:addCommentMediaList
	 * @Description: 批量插入点评的图片和视频
	 * @param mediaList 图片视频实体集合
	 * 2017年5月16日下午2:43:24
	 */
	@DataSource("joint")
	void addCommentMediaList(List<ExperienceCommentMedia> mediaList);

	/**
	 * 根据id查询评论信息
	 * @Title:queryCommentInfoById
	 * @Description:根据id查询点评信息
	 * @param commentId 点评id
	 * @return ExperienceComment 点评信息
	 * 2017年5月20日下午12:14:43
	 */
	@DataSource("joint")
	ExperienceComment queryCommentInfoById(Integer commentId);

	
	/**
	 * 
	 * @Title:updateComment
	 * @Description:修改评论
	 * @param comment void
	 * 2017年5月20日下午2:47:56
	 */
	@DataSource("joint")
	void updateComment(ExperienceComment comment);

	/**
	 * 
	 * @Title:deleteCommentMedia
	 * @Description:级联删除点评图片和视频
	 * @param id 点评表的评论id
	 * 2017年5月20日下午3:03:34
	 */
	@DataSource("joint")
	void deleteCommentMedia(Integer id);
}
