package com.xmniao.service.msg;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.OSUtil;
import com.xmniao.entity.MQMsgConfig;

/**
 * 分账消息生产者
 * 
 * @author YangJing
 * 
 */
public class LedgerProducer {

	/** 日志记录 */
	private final Logger log = Logger.getLogger(LedgerProducer.class);
	/** 分账消息生产者 */
	private DefaultMQProducer producer;

	/** 分账消息生产主题 */
	private String ledgertopic;
	/** 分账消息生产标签 */
	private String ledgertags;
	
	private String saastags;
	
	private String offlinetags="offline_incr_ledger_pop";
	private String onlinetags="online_incr_ledger_pop";
	
	/** 分账消息生产实例 */
	private String ledgerInstance;
	
	private final String XMN_ORDER ="divide_pay";	//普通消费订单
	private final String MALL_ORDER="incr_ledger_push";	//积分商品订单
	private final String SAAS_ORDER="saas_ledger_push";		//saas签约订单
	private final String PRE_SAAS_ORDER="saas_ledger_prepush";		//saas签约订单	
	/** 消息 */
	private Message msg;

	@Autowired
	private MQMsgConfig mqMsgConfig;

	/**
	 * 初始化生产者、消息
	 */
	public void init() {

		producer = new DefaultMQProducer();

		/* 消费者组、消费者实例标识 */
		String producer_ident = "";
		/* 主题 标识 */

		/* 判断是否开启集群 */
		if ("0".equals(mqMsgConfig.getCluster())) {
			producer_ident = mqMsgConfig.getIdentityPay();
		} else {
			producer_ident = OSUtil.getLocalIP_();
		}
		ledgertopic += producer_ident;

		/* 生产者组名 */
		producer.setProducerGroup(mqMsgConfig.getProducerGroup()
				+ producer_ident);
		/* 生产者实例名 */
		producer.setInstanceName(ledgerInstance + "_"
				+ producer_ident);
		/* 消息服务器地址 */
		producer.setNamesrvAddr(mqMsgConfig.getNamesrvAddr());
		try {
			producer.start();
		} catch (MQClientException e) {
			log.error("分账生存者初始化异常", e);
		}

		log.info("分账返回消息生产配置：   " + "   group:" + producer.getProducerGroup()
				+ "   instance:" + producer.getInstanceName() + "   topic:"
				+ ledgertopic + "   tag:" + ledgertags);
		msg = new Message();


	}

	/**
	 * 发送分账返回消息
	 * 
	 * @param paramMap
	 *            orderNumber status message platform_code userType
	 */
	public void ledgerSendMsg(String revMsgTag,Map<String, String> paramMap)
			throws UnsupportedEncodingException, MQClientException,
			RemotingException, MQBrokerException, InterruptedException {

		String body = JSON.toJSONString(paramMap); // 消息主体
		
		if(revMsgTag.equals(SAAS_ORDER)){	//saas订单
			msg.setTopic(ledgertopic);
			msg.setTags(saastags);
			msg.setKeys("fz_2_" + paramMap.get("bid"));
			
		}else if(revMsgTag.equals(PRE_SAAS_ORDER)){	//saas订单
			return ;
		}else if(revMsgTag.equals(MALL_ORDER)){	//积分订单
			if(paramMap.get("dealType").equals("1")){	//线上商品订单
				msg.setTopic(ledgertopic);
				msg.setTags(onlinetags);
				msg.setKeys("fz_3_" + paramMap.get("bid"));
			}else if(paramMap.get("dealType").equals("2")){	//线下商品订单
				msg.setTopic(ledgertopic);
				msg.setTags(offlinetags);
				msg.setKeys("fz_4_" + paramMap.get("bid"));
			}
			paramMap.remove("dealType");
		}else if(revMsgTag.equals(XMN_ORDER)) {									//消费订单
			msg.setTopic(ledgertopic);	
			msg.setTags(ledgertags);
			msg.setKeys("fz_1_" + paramMap.get("bid"));
		}else{
			msg.setTopic(ledgertopic);	
			msg.setTags(revMsgTag);
			msg.setKeys(paramMap.get("bid"));
		}
		
		// 消息
		msg.setBody(body.getBytes("UTF-8"));
		// 发送消息
		SendResult sendResult = producer.send(msg);
		log.info("成功发送分账返回信息:" + body);
		log.info("成功发送分账返回信息:" + msg.getTopic()+","+msg.getTags()+","+msg.getKeys());
		log.info(sendResult);
	}

	/**
	 * 注销生产者
	 */
	public void shutdown() {
		log.info("分账消息生产者被注销...");
		producer.shutdown();
	}

	public String getLedgertopic() {
		return ledgertopic;
	}

	public void setLedgertopic(String ledgertopic) {
		this.ledgertopic = ledgertopic;
	}

	public String getLedgertags() {
		return ledgertags;
	}

	public void setLedgertags(String ledgertags) {
		this.ledgertags = ledgertags;
	}

	public String getLedgerInstance() {
		return ledgerInstance;
	}

	public void setLedgerInstance(String ledgerInstance) {
		this.ledgerInstance = ledgerInstance;
	}

	public String getSaastags() {
		return saastags;
	}

	public void setSaastags(String saastags) {
		this.saastags = saastags;
	}

	public String getOfflinetags() {
		return offlinetags;
	}

	public void setOfflinetags(String offlinetags) {
		this.offlinetags = offlinetags;
	}
	
}
