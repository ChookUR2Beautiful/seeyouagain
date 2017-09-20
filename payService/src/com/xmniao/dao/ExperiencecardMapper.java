package com.xmniao.dao;

import com.xmniao.common.Page;
import com.xmniao.entity.Experiencecard;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExperiencecardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Experiencecard record);

    int insertSelective(Experiencecard record);

    Experiencecard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Experiencecard record);

    int updateByPrimaryKey(Experiencecard record);

    /**
     * 根据用户 uid 查询
     * @param uid
     * @return
     */
    Experiencecard selectByUid(Integer uid);

    /**
     * 扣除1张美食体验卡
     * @param experiencecard
     * @return
     */
    int deductExperiencecard(Experiencecard experiencecard);

    /**
     * 增加一张美食体验卡
     * @param excard
     * @return
     */
    int increaseExperiencecard(Experiencecard excard);

    /**
     * 统计美食体验卡数量
     * @param paramMap
     * @return
     */
    int countExperiencecard(@Param("paramMap") Map<String, String> paramMap);

    /**
     * 根据条件分页查询美食体验卡
     * @param paramMap
     * @param page
     * @return
     */
    List<Experiencecard> queryExperiencecardList(@Param("paramMap") Map<String, String> paramMap, @Param("page") Page page);

    /**
     * 根据uid批量查询美食体验卡
     * @param uids
     * @return
     */
    List<Experiencecard> selectByUids(@Param("uids") List<Integer> uids);
}