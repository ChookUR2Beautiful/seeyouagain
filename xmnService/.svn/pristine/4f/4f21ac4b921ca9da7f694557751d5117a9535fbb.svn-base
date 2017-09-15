package com.xmniao.xmn.core.live.dao;

import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 */
@Repository
public interface LiveAnchorVideoDao {

    /**
     * 获取精彩时刻视频
     * @param id
     * @return
     */
    @DataSource("joint")
    Map<Object, Object> getAnchorVideoById(@Param("id") Integer id);

    /**
     * 获取直播首页精彩时刻视频
     * @param map
     * @return
     */
    @DataSource("joint")
    List<Map<Object, Object>> getLiveHomeRecommendVideo(Map<Object, Object> map);


    /**
     * 更新观看次数
     * @param map
     */
    @DataSource("joint")
    void updateVnchorVideoViewCount(Map<Object, Object> map);

    /**
     * 精选直播，精彩时刻
     * @param map
     */
    @DataSource("joint")
    List<Map<Object, Object>> getLiveHomeRecommendVideoV2(Map<Object, Object> map);


    /**
     * 非签约主播精彩视频推荐
     * @param map
     */
    @DataSource("joint")
    List<Map<Object, Object>> getRecommendVideoByNoneSign(Map<Object, Object> map);
}
