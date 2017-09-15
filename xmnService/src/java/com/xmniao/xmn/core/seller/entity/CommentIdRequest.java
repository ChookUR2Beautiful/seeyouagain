package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class CommentIdRequest extends BaseRequest{

	private static final long serialVersionUID = 5068383952360843311L;
	
	@NotNull(message="点评id不能为空")
	private Integer commentId;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@Override
	public String toString() {
		return "CommentIdRequest [commentId=" + commentId + ",Base:" + super.toString() + "]";
	}
	
	

}
