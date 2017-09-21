package com.xmniao.dao.live;

import java.util.List;

import com.xmniao.domain.live.LiveLedgerRecord;

public interface LiveLedgerRecordDao {

    int insertLiveLedgerRecord(LiveLedgerRecord record);

    LiveLedgerRecord getLiveLedgerRecord(LiveLedgerRecord record);
    
    List<LiveLedgerRecord> getLiveLedgerRecordList(LiveLedgerRecord record);

    int updateLiveLedgerRecord(LiveLedgerRecord record);

    int updateByPrimaryKey(LiveLedgerRecord record);
    
    /**
     * 
     * 方法描述：查询订单是否已进行分账
     * 创建人： ChenBo
     * 创建时间：2016年12月26日
     * @param record
     * @return long
     */
    long countLedgerRecord(LiveLedgerRecord record);
        
}