package com.xmn.saas.dao.celebrity;

import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.Celebrity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CelebrityDao {
    int deleteByPrimaryKey(Long id);

    int insert(Celebrity record);

    int insertSelective(Celebrity record);

    Celebrity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Celebrity record);

    int updateByPrimaryKey(Celebrity record);

    /** 查询 名嘴 并根据条件排序,分页 */
    List<Celebrity> selectByReviewer(@Param("page") Page page, @Param("order") Integer order);

    Long countReviewer();
}