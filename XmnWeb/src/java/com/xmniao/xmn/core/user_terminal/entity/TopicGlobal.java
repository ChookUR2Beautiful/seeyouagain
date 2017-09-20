package com.xmniao.xmn.core.user_terminal.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：XmnWeb
 * 
 * 类名：TopicGlobal.java
 * 
 * 类描述：添加话题的全局实体，包含实体话题、话题图片、话题回复
 * 
 * 创建者:yang'xu
 * 
 * 创建时间：2014-12-25 09：44：21
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 * 
 */
public class TopicGlobal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6328305104807701230L;
	private TTopic topic;							//添加topic实体，将关联的数据保存到表t_xmn_topic
	private List<TTopicComment> topicCommentList;	//添加topicComment实体，将关联数据保存到t_xmn_topic_Comm
	private List<TTopicImg> topicImgList;			//添加topicImg实体对象，将关联数据保存到t_xmn_topic_img
	private TTopicComment topicComment;
	
	public TTopic getTopic() {
		return topic;
	}
	public void setTopic(TTopic topic) {
		this.topic = topic;
	}
	public List<TTopicComment> getTopicCommentList() {
		return topicCommentList;
	}
	public void setTopicCommentList(List<TTopicComment> topicCommentList) {
		this.topicCommentList = topicCommentList;
	}
	public List<TTopicImg> getTopicImgList() {
		return topicImgList;
	}
	public void setTopicImgList(List<TTopicImg> topicImgList) {
		this.topicImgList = topicImgList;
	}
	public TTopicComment getTopicComment() {
		return topicComment;
	}
	public void setTopicComment(TTopicComment topicComment) {
		this.topicComment = topicComment;
	}
	@Override
	public String toString() {
		return "TopicGlobal [topic=" + topic + ", topicCommentList="
				+ topicCommentList + ", topicImgList=" + topicImgList
				+ ", topicComment=" + topicComment + "]";
	}
	
	
	
}
