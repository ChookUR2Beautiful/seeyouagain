package com.xmniao.service.manor;

import com.xmniao.dao.manor.ManorFlowerRelationRecordMapper;
import com.xmniao.domain.manor.ManorFlowerRelationRecord;
import com.xmniao.domain.manor.ManorInfoRecord;
import com.xmniao.urs.dao.manor.ManorInfoRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 黄金庄园记录 Service
 * Created by yang.qiang on 2017/6/20.
 */
@Service
public class ManorLogService {

    @Autowired
    private ManorInfoRecordMapper manorInfoRecordMapper;
    @Autowired
    private ManorFlowerRelationRecordMapper manorFlowerRelationRecordMapper;

    /**
     * 记录花朵操作
     * @param manorFlowerRelationRecord
     */
    public void logFlowerRelationRecord(ManorFlowerRelationRecord manorFlowerRelationRecord) {
        manorFlowerRelationRecord.setCreateTime(new Date());
        manorFlowerRelationRecordMapper.insertSelective(manorFlowerRelationRecord);
    }


    /**
     * 记录庄园操作
     */
    public void logManorInfoRecord(ManorInfoRecord manorInfoRecord) {
        manorInfoRecord.setCreateTime(new Date());
        manorInfoRecordMapper.insertSelective(manorInfoRecord);
    }

}
