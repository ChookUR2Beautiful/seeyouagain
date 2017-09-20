package com.xmniao.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.QueryResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

public class QueryRocketMQ {
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		
		
		/* FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[] {"WebRoot/WEB-INF/pay-context.xml","WebRoot/WEB-INF/pay-service.xml","WebRoot/WEB-INF/pay-rocketmq.xml"}, true);
	     context.start();
	     
	    
	      DefaultMQProducer  producer = (DefaultMQProducer) context.getBean("producer");*/
		  DefaultMQProducer  producer =  new DefaultMQProducer();
	      producer.setNamesrvAddr("192.168.50.123:9876");
	      producer.setProducerGroup("consumerWithdrawPay_yulu110");
	      producer.start();
		  QueryResult queryMessage =
                  producer.queryMessage("topic_withdraw_yulu110","80001170", 10, 0, System.currentTimeMillis());
         /* for (MessageExt m : queryMessage.getMessageList()) {
              System.out.println(m);
          }*/
		  
		  
/*		  MessageExt [queueId=2, storeSize=216, queueOffset=0, sysFlag=0, bornTimestamp=1430295245899,  bornHost=/192.168.50.117:64295, storeTimestamp=1430295246437, storeHost=/192.168.50.123:10911, msgId=C0A8327B00002A9F00000000001BDD6A, commitLogOffset=1826154, bodyCRC=2015574743,reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message [topic=topic_withdraw_yulu, flag=0, properties={TAGS=withdraw_yulu, KEYS=80001076}, body=73]]*/
		  
		  
		  
		  
		 // MessageExt [queueId=1, storeSize=216, queueOffset=0, sysFlag=0, bornTimestamp=1430295184657, bornHost=/192.168.50.117:64295, storeTimestamp=1430295185195, storeHost=/192.168.50.123:10911, msgId=C0A8327B00002A9F00000000001BDBA1, commitLogOffset=1825697, bodyCRC=3883349, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message [topic=topic_withdraw_yulu, flag=0, properties={TAGS=withdraw_yulu, KEYS=80001072}, body=73]]
		  MessageExt m = queryMessage.getMessageList().get(0);
		  
		  System.out.println(m);
		  System.out.println("内容："+new String(m.getBody()));
		  
		  System.out.println("产生时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(m.getBornTimestamp())));
		  
		/*  System.out.println(m.get);
		  
		  System.out.println();*/
		
		
		
		
		
		//{"Message":"代发交易成功","status":"1","orderNumber":"80001072","platform_code":"0001569194"}
		
		
	/*	
		Map<String,String> paramMap = new HashMap<String,String>();
		
		paramMap.put("Message", "代发交易成功");
		paramMap.put("status", "1");
		paramMap.put("orderNumber", "80000733");
		paramMap.put("platform_code", "0001569194");
		
		   DefaultMQProducer  producer =  new DefaultMQProducer();
		      producer.setNamesrvAddr("192.168.50.123:9876");
		      producer.setProducerGroup("ProducerWithdraw_pay_kaifa1");
		      producer.start();
		      
		  	Message sendMsg = new Message();
			Date time = new Date();
			String body = JSON.toJSONString(paramMap); // 消息主体
			// 消息
			sendMsg.setTopic("topic_callback_kf");
			sendMsg.setTags("withdraw_return_kf");
			sendMsg.setKeys(time.getTime() + "");
			sendMsg.setBody(body.getBytes("UTF-8"));

			SendResult sendResult = producer.send(sendMsg);
			System.out.println("成功发送提现返回信息:" + body);
			System.out.println(sendResult);
		     */
		
	}
	

}
