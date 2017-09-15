package com.xmniao.xmn.core.seller.service;

import com.xmniao.xmn.core.seller.entity.CommentIdRequest;
import com.xmniao.xmn.core.seller.entity.CommentRequest;
import com.xmniao.xmn.core.seller.entity.CommentSellerRequest;
import com.xmniao.xmn.core.seller.entity.ModifyCommentRequest;
import com.xmniao.xmn.core.seller.entity.SellerCommentRequest;

/**
 * 店铺点评Service
 * @ClassName:ExperienceCommentService
 * @Description:TODO
 * @Author:xw
 * @Date:2017年5月15日下午5:14:22
 */
public interface ExperienceCommentService {

	/**
	 * 新增点评
	 * @Title:addComment
	 * @Description:新增店铺点评
	 * @param commentRequest
	 * @return Object
	 * 2017年5月15日下午5:18:48
	 */
	Object addComment(CommentRequest commentRequest);

	/**
	 * 
	 * @Title:listSellerComment
	 * @Description 根据店铺id查询店铺网红点评列表
	 * @param request 店铺id sellerType 请求类
	 * @return Object 店铺点评列表
	 * 2017年5月19日下午1:10:38
	 */
	Object sellerCommentList(SellerCommentRequest request);

	/**
	 * 
	 * @Title:initCommentInfo
	 * @Description:修改点评时 查询初始化点评数据
	 * @param commentIdRequest 点评id请求类
	 * @return Object 初始化点评数据信息
	 * 2017年5月20日下午12:28:23
	 */
	Object initCommentInfo(CommentIdRequest commentIdRequest);

	/**
	 * 
	 * @Title:updateComment
	 * @Description:修改点评
	 * @param ModifyCommentRequest 修改的点评数据请求类
	 * @return Object
	 * 2017年5月20日下午2:02:55
	 */
	Object updateComment(ModifyCommentRequest request);

	
	/**
	 * 查询点评商家信息，(美食体验官新增点评、主播修改点评时查询点评店铺信息)
	 * @Title:commentSellerInfo
	 * @Description:点评商家信息
	 * @param request
	 * @return Object
	 * 2017年5月24日下午6:07:38
	 */
	Object commentSellerInfo(CommentSellerRequest request);

	
}
