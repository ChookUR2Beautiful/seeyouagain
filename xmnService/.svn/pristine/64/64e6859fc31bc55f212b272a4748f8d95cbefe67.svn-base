package com.xmniao.xmn.core.common.rocketmq.model;

/**
 * 消息队列信息
 * @author Administrator
 *
 */
public class TopicInfo {

	/**
	 * 队列服务地址
	 */
	private String namesrvAddr;
	/**
	 * 消息主题
	 */
	private String topicName;
	/**
	 * 消息分组标签
	 */
	private String topicTag;
	/**
	 * 消息组(集群模式下才有意义)
	 */
	private String topicGroup;
	
	public TopicInfo(String namesrvAddr,String topicName,String topicTag,String topicGroup){
		this.namesrvAddr = namesrvAddr;
		this.topicName = topicName;
		this.topicTag = topicTag;
		this.topicGroup = topicGroup;
	}
	
	public TopicInfo(){}
	
	public String getNamesrvAddr() {
		return namesrvAddr;
	}
	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicTag() {
		return topicTag;
	}
	public void setTopicTag(String topicTag) {
		this.topicTag = topicTag;
	}

	public String getTopicGroup() {
		return topicGroup;
	}

	public void setTopicGroup(String topicGroup) {
		this.topicGroup = topicGroup;
	}	
}
