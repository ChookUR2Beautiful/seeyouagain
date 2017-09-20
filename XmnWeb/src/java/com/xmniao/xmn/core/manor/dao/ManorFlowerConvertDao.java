package com.xmniao.xmn.core.manor.dao;

import java.util.List;

import com.xmniao.xmn.core.manor.entity.TManorFlowerConvert;


public interface ManorFlowerConvertDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TManorFlowerConvert record);

    int insertSelective(TManorFlowerConvert record);

    TManorFlowerConvert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TManorFlowerConvert record);

    int updateByPrimaryKey(TManorFlowerConvert record);
    
    int insertManorFlowerConvertBatch(List<TManorFlowerConvert> manorSunshineCouponList);
    
    int deleteManorFlowerConvert(TManorFlowerConvert record);
    
    List<TManorFlowerConvert> getManorFlowerConvertList(TManorFlowerConvert record); 
}