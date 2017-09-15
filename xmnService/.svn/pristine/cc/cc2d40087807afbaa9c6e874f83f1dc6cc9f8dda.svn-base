package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.search.FreshWord;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreshWordDao {
    @DataSource("joint")
    int deleteByPrimaryKey(Long id);

    @DataSource("joint")
    int insert(FreshWord record);

    @DataSource("joint")
    int insertSelective(FreshWord record);

    @DataSource("joint")
    FreshWord selectByPrimaryKey(Long id);

    @DataSource("joint")
    int updateByPrimaryKeySelective(FreshWord record);

    @DataSource("joint")
    int updateByPrimaryKey(FreshWord record);

    @DataSource("joint")
    List<String> selectHotWord(Integer records);

    @DataSource("joint")
    List<String> selectByUid(@Param("uid") Integer uid);

    @DataSource("joint")
    void deleteByUid(@Param("uid") Integer uid);

    @DataSource("joint")
    List<FreshWord> selectByWordAndUid(@Param("userid") Integer userid, @Param("word") String word);

    @DataSource("joint")
    void deleteHistory(@Param("historys") int historys, @Param("userid") Integer userid);

    @DataSource("joint")
    List<Integer> selectHistoryIdByUid(@Param("userid") Integer userid, @Param("historys") int historys);

    @DataSource("joint")
    void deleteByIds(@Param("ids") List<Integer> ids);

    @DataSource("joint")
    String selectDefaultWord(@Param("word") String word);

    @DataSource("joint")
    void updateIncrById(@Param("freshWord") FreshWord freshWord);

    @DataSource("joint")
    FreshWord selectByRecordWord(String word);

    @DataSource("joint")
    FreshWord selectByWordAndType(@Param("word") String word, @Param("type") int type);

    @DataSource("joint")
    List<String> selectHotRecord(Integer records);

    @DataSource("joint")
    String selectDefaultWordByType();
}