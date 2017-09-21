package com.xmniao.service.manor;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.domain.manor.ManorFlower;
import com.xmniao.domain.manor.ManorFlowerMqBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang.qiang on 2017/7/6.
 */
@Service
public class ManorFlowerProducer {
    private final Logger logger = LoggerFactory.getLogger(ManorFlowerProducer.class);

    @Autowired
    private DefaultMQProducer mqProducer;
    @Autowired
    private String manorBusineTopic;
    @Autowired
    private String manorInsertInitNodeTag;
    @Autowired
    private String manorPlantTag;
    @Autowired
    private String manorMigrateChainTag;
    @Autowired
    private String manorUpdateFlowerTypeTag;
    @Autowired
    private String manorUpdateLocationForManorInfoTag;


    /** 向MQ推送花朵操作消息*/
    private void pushFlowerMessage(String mqTag,ManorFlowerMqBean flowerInfo) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(manorBusineTopic, mqTag, "", JSON.toJSONString(flowerInfo).getBytes());

        // 顺序发送消息队列
        SendResult sendResult = mqProducer.send(message, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                Integer id = (Integer) arg;
                return mqs.get(id);
            }
        }, 0);
        logger.info("发送消息队列成功! sendResult:" + JSON.toJSONString(sendResult));
    }

    /**
     * 推送 插入用户初始节点消息队列
     * @param transNo
     * @param uid 用户uid
     */
    public void pushInitNodeMessage(String transNo, Integer uid) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        ManorFlowerMqBean manorFlowerMqBean = new ManorFlowerMqBean();
        manorFlowerMqBean.setTransNo(transNo);
        manorFlowerMqBean.setUid(uid);
        pushFlowerMessage(manorInsertInitNodeTag,manorFlowerMqBean);

    }

    /**
     * 推送 种植花朵消息
     */
    public void pushPlantFlowerMessage(String transNo, Integer uid, Integer location, List<ManorFlower> flowers) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        int limitSize = 1000;

        ManorFlowerMqBean plantInfo = new ManorFlowerMqBean();
        plantInfo.setUid(uid);
        plantInfo.setTransNo(transNo);
        plantInfo.setLocation(location);


        // 分批推送MQ消息
        int slices = flowers.size() / limitSize + 1;
        for (int i = 0; i < slices; i++) {
            int toIndex = limitSize * (i + 1);
            toIndex = flowers.size() < toIndex ? flowers.size() : toIndex;
            plantInfo.setFlowers(flowers.subList(limitSize * i,  toIndex));

            if (plantInfo.getFlowers().size() > 0) {
                pushFlowerMessage(manorPlantTag,plantInfo);
            }

        }



    }

    /**
     * 推送迁移花朵关系链 消息队列
     * @param transNo
     * @param subUid       被迁移的用户
     * @param parentUid   迁移到该用户
     * @param location  迁移位置
     */
    public void pushMigrateFlowerChainMessage(String transNo, Integer subUid, Integer parentUid, Integer location) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        ManorFlowerMqBean migrateInfo = new ManorFlowerMqBean();
        migrateInfo.setSubUid(subUid);
        migrateInfo.setParentUid(parentUid);
        migrateInfo.setLocation(location);
        migrateInfo.setTransNo(transNo);

        pushFlowerMessage(manorMigrateChainTag,migrateInfo);

    }

    /**
     * 推送 更新初始节点花类型消息队列
     * @param transNo
     * @param uid
     * @param flowerType
     */
    public void pushUpdateInitNodeTypeMessage(String transNo, Integer uid, Integer flowerType) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        ManorFlowerMqBean flowerInfo = new ManorFlowerMqBean();
        flowerInfo.setUid(uid);
        flowerInfo.setType(flowerType);
        pushFlowerMessage(manorUpdateFlowerTypeTag,flowerInfo);
    }

    public void pushUpdateLocationForBursRelation(Integer uid, Integer location) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        ManorFlowerMqBean mqInfo = new ManorFlowerMqBean();
        mqInfo.setSubUid(uid);
        mqInfo.setLocation(location);
        pushFlowerMessage(manorUpdateLocationForManorInfoTag,mqInfo);
    }

}
