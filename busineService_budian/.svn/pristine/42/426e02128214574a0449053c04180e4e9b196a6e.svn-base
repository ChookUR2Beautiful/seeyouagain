package com.xmniao.service.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.xmniao.dao.order.MallOrderDao;
import com.xmniao.domain.order.MallOrderBean;

/**
 * 积分商城订单定时器
 * @author chenJie
 *
 */
public class MallOrderQuertzService {
	
	/**
	 * 初始化日志类
	 */
	private Logger log = Logger.getLogger(MallOrderQuertzService.class);
	
	@Autowired
	private MallOrderDao mOrderDao;
	
	@Autowired
	private DefaultMQProducer producerConnection;
	
	@Autowired
	private String freshOrderTopic;
	
	@Autowired
	private String freshOrderTags;
	
	private Date startDate = new Date();//服务器启动时间
	
	public void updateFreshBillOrder() throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
		log.info("开始扫描积分商城未支付 订单...");
		
		Map<String,Object> parMap = new HashMap<>();
		Integer page = 0;
		parMap.put("date",startDate);
		parMap.put("page",page);
		List<MallOrderBean> noPaymentOrders = mOrderDao.scanNoPaymentOrder(parMap);
		
		int flag = noPaymentOrders.size();
		
		if(noPaymentOrders !=null && noPaymentOrders.size()>0){
			
			while(flag>0){
				String jsonMsg = JSON.toJSONString(noPaymentOrders);
				
				Message msg = new Message();
				msg.setTopic(freshOrderTopic);
				msg.setTags(freshOrderTags);
				msg.setBody(jsonMsg.getBytes());
				msg.setKeys(System.currentTimeMillis()+"");
				log.info("发送MQ:\r\nTopic:" + msg.getTopic() + ",Tags:" + msg.getTags()
						+ ",keys:" + msg.getKeys() + ",body:" + jsonMsg);
				SendResult send = producerConnection.send(msg);
				
				page++;
				parMap.put("page",page);
				noPaymentOrders = mOrderDao.scanNoPaymentOrder(parMap);
				flag = noPaymentOrders.size();
			}
			
		}else {
			log.info("未扫描到超时未支付订单...");
		}
		log.info("扫描积分商城未支付订单结束...");
	}
}
