package com.xmniao.urs.dao.manor;

import com.xmniao.domain.manor.ManorInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ManorInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManorInfo record);

    int insertSelective(ManorInfo record);

    ManorInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManorInfo record);

    int updateByPrimaryKey(ManorInfo record);

    /**
     * 根据uid 查询该用户的庄园信息
     * @param uid 用户uid
     * @return  庄园信息
     */
    ManorInfo selectByUid(@Param("uid") Integer uid);


    /**
     * 查询激活状态中的庄园
     * @return
     */
    List<ManorInfo> selectActivatedManors();
    
    /**
     * 按页查询激活状态中的庄园
     * @return
     */
    List<ManorInfo> selectActivatedManorsByPage(Map<String,Object> page);

    /**
     * 更新下级被摆放位置
     * @param location
     * @param uid
     */
    int updateBranchLocationByUid(@Param("location") Integer location, @Param("uid") Integer uid);
}