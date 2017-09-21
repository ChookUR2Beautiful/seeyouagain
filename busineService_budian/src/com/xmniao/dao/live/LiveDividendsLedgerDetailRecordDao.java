package com.xmniao.dao.live;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.live.LiveDividendsLedgerDetailRecord;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiveDividendsLedgerDetailRecordDao
 * 
 * 类描述： 直播分红详情记录DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月2日 下午2:25:54 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface LiveDividendsLedgerDetailRecordDao {

    int insertList(@Param("list") List<LiveDividendsLedgerDetailRecord> list,@Param("pid") Integer pid,@Param("createDate") Date createDate);

    List<LiveDividendsLedgerDetailRecord> getDiviDendsLedgerDetailRecordList(@Param("pid")Integer pid);
    
    int updateDividendsLedgerDetailRecord(LiveDividendsLedgerDetailRecord uRecord);

    LiveDividendsLedgerDetailRecord getDiviDendsLedgerDetailRecordSum(@Param("pid")Integer pid);

}