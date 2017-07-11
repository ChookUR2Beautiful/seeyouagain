package com.xmn.saas.dao.shop;

import com.xmn.saas.entity.shop.LiveClassifyTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiveClassifyTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LiveClassifyTag record);

    int insertSelective(LiveClassifyTag record);

    LiveClassifyTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiveClassifyTag record);

    int updateByPrimaryKey(LiveClassifyTag record);

    List<LiveClassifyTag> selectByClassId(@Param("classifyId") Integer classifyId);

    List<LiveClassifyTag> selectByIds(@Param("tags") String[] tags);
}