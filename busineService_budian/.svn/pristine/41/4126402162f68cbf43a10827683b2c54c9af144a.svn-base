package com.xmniao.service.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.dao.order.ActivityServiceDao;
import com.xmniao.domain.activity.RewardActivityBean;

/**
 * 打赏活动服务接口
 * @author  LiBingBing
 * @version  [版本号, 2015年3月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RewardActivityService
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(RewardActivityService.class);
    
    /**
     * 注入活动业务DAO层
     */
    @Autowired
    private ActivityServiceDao activityDao;
    
    /**
     * 注入JMS生产者发送消息
     */
    @Autowired
    private DefaultMQProducer producer;
    
    /**
     * 注入JMS发送消息主题Topic
     */
    private String msgtopic;
    
    /**
     * 注入打赏活动发送消息的标签Tags
     */
    private String tiptags;
    
    /**
     * 打赏活动记录业务处理
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void rewardActivityProcess()
    {
        log.info("开始执行打赏活动业务处理......");
        try
        {
            long startTime = System.currentTimeMillis();
            List<RewardActivityBean> resList = activityDao.queryRewardActtyInfos();
            log.info("获取到未打赏记录总数:"+resList.size()+"条");
            //判断获取未打赏记录LIST集合是否为空
            if (resList.size() != 0)
            {
                for(RewardActivityBean resBean : resList)
                {
                    //组装发送消息MAP
                    Map<String,String> sendMsgMap = new HashMap<String,String>();
                    sendMsgMap.put("order_id", resBean.getOrderCode());
                    sendMsgMap.put("seller_id", resBean.getSellerId());
                    sendMsgMap.put("seller_name",resBean.getSellerName());
                    if(StringUtils.isNotEmpty(resBean.getJointId()))
                    {
                        sendMsgMap.put("jointid", resBean.getJointId());
                    }else
                    {
                        sendMsgMap.put("jointid", "");
                    }
                    if(StringUtils.isNotEmpty(resBean.getCorporate()))
                    {
                        sendMsgMap.put("corporate",resBean.getCorporate());
                    }else
                    {
                        sendMsgMap.put("corporate","");
                    }
                    sendMsgMap.put("area_id", resBean.getAreaId());
                    sendMsgMap.put("uid", String.valueOf(resBean.getUid()));
                    sendMsgMap.put("u_phone", resBean.getuPhone());
                    if(StringUtils.isNotEmpty(resBean.getuName()))
                    {
                        sendMsgMap.put("u_nname", resBean.getuName());
                    }else
                    {
                        sendMsgMap.put("u_nname", "");
                    }
                    sendMsgMap.put("waiter_id", resBean.getWaiterId());
                    sendMsgMap.put("s_phone", resBean.getsPhone());
                    if(StringUtils.isNotEmpty(resBean.getsName()))
                    {
                        sendMsgMap.put("s_nname", resBean.getsName());
                    }else
                    {
                        sendMsgMap.put("s_nname", "");
                    }
                    sendMsgMap.put("tip", String.valueOf(resBean.getTip()));
                    sendMsgMap.put("tip_status", String.valueOf(resBean.getTipStatus()));
                    long tipDate = System.currentTimeMillis();
                    sendMsgMap.put("tip_date", String.valueOf(tipDate));
                    
                    //发送消息
                    Message msg = new Message();
                    //活动发送消息主题
                    msg.setTopic(msgtopic);
                    //打赏活动业务发送消息标签Tags
                    msg.setTags(tiptags);
                    //打赏活动业务发送消息Key
                    msg.setKeys(resBean.getOrderCode());
                    log.info("rewardActivityProcess sendMessage info:Topic:"+ msg.getTopic() + " Tags:" + msg.getTags() + " Keys:" + msg.getKeys());
                    //打赏活动业务发送消息内容
                    String msgContent = JSONObject.toJSONString(sendMsgMap);
                    log.info("rewardActivityProcess sendMessage JSON:" + msgContent);
                    msg.setBody(msgContent.getBytes());
                    
                    //开始发送消息
                    SendResult sendResult = producer.send(msg);
                    log.info("rewardActivityProcess sendMessage sendResult:" +sendResult);
                    //获取发送消息状态
                    String resStatus = sendResult.getSendStatus().name();
                    //判断是否发送成功，若没法送成功，则重新发送消息
                    if (resStatus.equals("SEND_OK"))
                    {
                        SimpleDateFormat sdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        resBean.setSdate(sdateFormat.format(new Date()));
                        //若发送消息成功，则批量更新t_tip表中的打赏状态status为2处理中
                        activityDao.modifyRewardActtyInfos(resBean);
                    }
                    else
                    {
                        //重新发送消息
                        sendResult = producer.send(msg);
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            log.info("定时任务执行总耗时："+(endTime-startTime)+"ms");
        }
        catch (MQClientException e)
        {
            e.printStackTrace();
        }
        catch (RemotingException e)
        {
            e.printStackTrace();
        }
        catch (MQBrokerException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        log.info("打赏活动业务处理结束......");
    }
    
    public DefaultMQProducer getProducer()
    {
        return producer;
    }
    
    public void setProducer(DefaultMQProducer producer)
    {
        this.producer = producer;
    }
    
    public String getMsgtopic()
    {
        return msgtopic;
    }
    
    public void setMsgtopic(String msgtopic)
    {
        this.msgtopic = msgtopic;
    }
    
    public String getTiptags()
    {
        return tiptags;
    }
    
    public void setTiptags(String tiptags)
    {
        this.tiptags = tiptags;
    }
}
