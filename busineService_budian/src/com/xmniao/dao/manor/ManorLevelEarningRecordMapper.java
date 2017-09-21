package com.xmniao.dao.manor;

import com.xmniao.domain.manor.ManorLevelEarningRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface ManorLevelEarningRecordMapper {

    int insert(ManorLevelEarningRecord record);

    ManorLevelEarningRecord get(Long id);
    
    ManorLevelEarningRecord getByTransNo(String transNo);

    int updateStatus(ManorLevelEarningRecord record);

    List<ManorLevelEarningRecord> selectTodayByStatus(@Param("status") int status);
    
    /**
     * 
     * 方法描述：统计当天奖励条数 <br/>
     * 创建人： Administrator <br/>
     * 创建时间：2017年7月25日下午8:12:00 <br/>
     * @param date
     * @return
     */
    long countEarningToday(Date date);
}