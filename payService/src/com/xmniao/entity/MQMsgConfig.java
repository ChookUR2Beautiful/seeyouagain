package com.xmniao.entity;

import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
/**
 * 消息队列基本配置
 * @author zenghm
 *
 */
public class MQMsgConfig {
	
	
	/** 消息服务地址 */
	protected String namesrvAddr;
	/** 消息模式：广播、集群 */
	protected MessageModel messageModel;
	/** 消息读取方式 */
	protected ConsumeFromWhere consumeFromWhere;
	/** 系统是否集群     0 开启 1 关闭  */
	protected String cluster ;
	/** 分账标识 */
	protected String identityLedger;
	/** 业务标识 */
	protected String identityBusine;
	/** 支付标识 */
	protected String identityPay;
	/** 支付生产组*/
	protected String producerGroup;
	/** 支付生产者*/
	protected String producerInstance;

	
	public String getProducerGroup() {
		return producerGroup;
	}

	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}

	public String getProducerInstance() {
		return producerInstance;
	}

	public void setProducerInstance(String producerInstance) {
		this.producerInstance = producerInstance;
	}

	public String getNamesrvAddr() {
		return namesrvAddr;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

	public MessageModel getMessageModel() {
		return messageModel;
	}

	public void setMessageModel(MessageModel messageModel) {
		this.messageModel = messageModel;
	}

	public ConsumeFromWhere getConsumeFromWhere() {
		return consumeFromWhere;
	}

	public void setConsumeFromWhere(ConsumeFromWhere consumeFromWhere) {
		this.consumeFromWhere = consumeFromWhere;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getIdentityLedger() {
		return identityLedger;
	}

	public void setIdentityLedger(String identityLedger) {
		this.identityLedger = identityLedger;
	}

	public String getIdentityBusine() {
		return identityBusine;
	}

	public void setIdentityBusine(String identityBusine) {
		this.identityBusine = identityBusine;
	}

	public String getIdentityPay() {
		return identityPay;
	}

	public void setIdentityPay(String identityPay) {
		this.identityPay = identityPay;
	}
}
