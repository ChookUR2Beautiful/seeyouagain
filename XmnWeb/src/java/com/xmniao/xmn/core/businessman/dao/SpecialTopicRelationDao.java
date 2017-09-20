package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.businessman.entity.TSpecialTopicRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface SpecialTopicRelationDao {
    int deleteByPrimaryKey(Integer id);

    @DataSource("master")
    int insert(TSpecialTopicRelation record);
    
    @DataSource("master")
    int insertSelective(TSpecialTopicRelation record);

    TSpecialTopicRelation selectByPrimaryKey(Integer id);

    @DataSource("master")
    int updateByPrimaryKeySelective(TSpecialTopicRelation record);

    @DataSource("master")
    int updateByPrimaryKeyWithBLOBs(TSpecialTopicRelation record);

    @DataSource("master")
    int updateByPrimaryKey(TSpecialTopicRelation record);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getSpecialTopicRelationDataList(Integer recordid);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getSellerRelationDataList(Integer recordid);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getMultipRelationDataList(Integer recordid);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getAllianceRelationDataList(Integer recordid);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getBeforliveRelationDataList(Integer recordid);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getFansCouponRelationDataList(Integer recordid);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getSellerPackageRelationDataList(Integer recordid);
    
    @DataSource("master")
    List<TSpecialTopicRelation> getProductInfoRelationDataList(Integer recordid);
    
    
    @DataSource("master")
    int deleteSpecialTopicRelationByFid(Integer fid);
}