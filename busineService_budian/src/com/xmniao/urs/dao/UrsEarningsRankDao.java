package com.xmniao.urs.dao;

import com.xmniao.domain.live.UrsEarningsRank;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UrsEarningsRankDao {

    int insertSelective(UrsEarningsRank record);

    UrsEarningsRank selectByUid(@Param("uid") Integer uid, @Param("rankSource") Integer rankSource);

    int updateSelective(UrsEarningsRank record);
    
    /**
     * 
     * 方法描述：根据V客UID获取直播分账比例 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-5-23上午11:29:26 <br/>
     * @param uid
     * @return
     */
    Map<String,Object> getVkeRatioByUid(String uid);

    /**
     * 查询用户等级id
     * @param uid
     * @param rankSource
     * @return
     */
    Integer selectRankidByUid(@Param("uid") Integer uid, @Param("rankSource") Integer rankSource);
}