package com.xmniao.dao.coupon;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.coupon.RedPacket;


public interface RedPacketDao {

//    int updateByPrimaryKeySelective(RedPacket record);

    
    /**
     * 获取红包列表
     * @Title: getRedPacketList 
     * @Description:
     */
    List<RedPacket> getRedPacketList(RedPacket redPacket);
    
    /**
     * 更新发送一个消费满赠的固定红包
     * @Title: sendRedPacket 
     * @Description:
     */
    int sendRedPacket(Long id);
    
    /**
     * 获取推荐红包
     * @Title: getRecommendRedPacketList 
     * @Description:
     */
    List<RedPacket> getRecommendRedPacketList(RedPacket redPacket);
    
    /**
     * 更新发送一个推荐人的红包
     * @Title: sendRedPacket 
     * @Description:
     */
    int sendRecommendRedPacket(Map<String,Object> uMap);
}