package com.xmniao.dao.coupon;

import java.util.List;

import com.xmniao.domain.coupon.RedPacketRecord;

public interface RedPacketRecordDao {

    int insertSelective(RedPacketRecord record);

    int updateByPrimaryKeySelective(RedPacketRecord record);

    RedPacketRecord getRedPacketRecord(RedPacketRecord record);
    
    List<RedPacketRecord> getRedPacketRecordList(RedPacketRecord record);
}