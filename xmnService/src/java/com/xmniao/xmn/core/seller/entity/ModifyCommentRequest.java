package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class ModifyCommentRequest extends BaseRequest{

	private static final long serialVersionUID = 6600067052171221520L;

	@NotNull(message="点评id不能为空")
	private Integer commentId;	//点评id
	@NotNull(message="点评内容不能为空")
	private String content;		//点评内容
	
	private String picUrls;		//图片url，多张图片用  ‘，’隔开
	
	private String videoUrl;	//视频url

	private String sellerName;
	private Integer sellerId;
	private Integer sellerType;
	
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Override
	public String toString() {
		return "ModifyCommentRequest [commentId=" + commentId + ", content=" + content + ", picUrls=" + picUrls
				+ ", videoUrl=" + videoUrl + ",Base:" + super.toString() + "]";
	}
	
	

}
