package com.xmn.saas.dao.redpacket;

import com.xmn.saas.entity.redpacket.RedpacketRecord;
import com.xmn.saas.service.redpacket.impl.RedpacketRecordServiceImpl;

import java.util.List;
import java.util.Map;

public interface RedpacketRecordDao {
    int deleteByPrimaryKey(Long redpacketRecordId);

    int insert(RedpacketRecord record);

    int insertSelective(RedpacketRecord record);

    RedpacketRecord selectByPrimaryKey(Long redpacketRecordId);

    int updateByPrimaryKeySelective(RedpacketRecord record);

    int updateByPrimaryKey(RedpacketRecord record);
    
    List<RedpacketRecord> findRedpacketRecordByParams(Map<String,Object> paramMap);


    List<RedpacketRecord> selectByRedpacketIdAndLimit(Long redpacketId, Integer pageNum, Integer pageSize);

    Object countByRedpacketId(Long redpacketId);
}