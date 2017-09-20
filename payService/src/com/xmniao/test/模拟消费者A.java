package com.xmniao.test;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

public class 模拟消费者A {
    public static void main(String[] args){  
        DefaultMQPushConsumer consumer =   
                new DefaultMQPushConsumer("Consumer_APK");  
        consumer.setNamesrvAddr("192.168.50.123:9876");   
        try {  
            //订阅PushTopic下Tag为push的消息  
//            consumer.subscribe("PushTopic", "push1 || push2");  
            consumer.subscribe("live_ledger_", "live_gift_ledger"); 
            //程序第一次启动从消息队列头取数据  
            consumer.setConsumeFromWhere(  
                    ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);  
            consumer.registerMessageListener(  
                new MessageListenerConcurrently() {  
                    public ConsumeConcurrentlyStatus consumeMessage(  
                            List<MessageExt> list,  
                            ConsumeConcurrentlyContext Context) {  
                        Message msg = list.get(0);  
                        System.out.println(msg.toString());
                        System.out.println(new String(msg.getBody()));  
                        System.out.println("topic:"+msg.getTopic()+" - tags:"+msg.getTags());
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;  
                    }  
                }  
            );  
            consumer.start();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
