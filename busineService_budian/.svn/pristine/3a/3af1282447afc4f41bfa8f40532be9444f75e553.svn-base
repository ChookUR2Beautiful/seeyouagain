package com.xmniao.dao.maibao;

import com.xmniao.domain.maibao.MaibaoLedger;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MaibaoLedgerMapper {
    int insert(MaibaoLedger record);

    int insertSelective(MaibaoLedger record);

    MaibaoLedger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaibaoLedger record);

    /**
     * 查询所有未通知的分账信息
     * @return
     * @param queryDate
     */
    List<MaibaoLedger> getNoAdvenceLedger(@Param("queryDate")Date queryDate);
}