package com.xmniao.urs.dao.manor;

import java.util.List;

import com.xmniao.domain.manor.ManorLevel;

import org.apache.ibatis.annotations.Param;

public interface ManorLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManorLevel record);

    int insertSelective(ManorLevel record);

    ManorLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManorLevel record);

    int updateByPrimaryKey(ManorLevel record);

    /**
     * 查询用户等级
     * @param roses
     * @param orchids
     * @param sunflowers
     * @return
     */
    ManorLevel selectManorNo(@Param("roses") int roses, @Param("orchids") int orchids, @Param("sunflowers") int sunflowers);

    List<ManorLevel> getList(ManorLevel t);
}