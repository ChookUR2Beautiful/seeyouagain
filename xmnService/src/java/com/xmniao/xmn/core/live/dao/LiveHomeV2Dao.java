package com.xmniao.xmn.core.live.dao;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 直播首页，3.6版本dao
 * Created by Administrator on 2017/5/15.
 */
@Repository
public interface LiveHomeV2Dao {

    // 所有直播列表
    @DataSource("joint")
    List<Map<Object, Object>> getLiveRecordList(Map<Object, Object> param);
    // 所有预告
    @DataSource("joint")
    List<Map<Object, Object>> getPreList(Map<Object, Object> param);
    // 统计直播数量
    @DataSource("joint")
    Integer sumLiveRecord(Map<Object, Object> param);
    // 回放视频 明星活动
    @DataSource("joint")
    List<Map<Object, Object>> getStarsVideo(Map<Object, Object> param);
    // 获取直播栏目
    @DataSource("joint")
    Map<Object, Object> getLiveTab(Map<Object, Object> param);
    // 获取直播栏目列表
    @DataSource("joint")
    List<Map<Object, Object>> getLiveTabList(Map<Object, Object> param);


    // 签约/非签约-直播记录
    @DataSource("joint")
    List<Map<Object, Object>> getLiveRecordListBySign(Map<Object, Object> param);
    @DataSource("joint")
    List<Map<Object, Object>> getLiveRecordListByNotSign(Map<Object, Object> param);
    @DataSource("joint")
    List<Map<Object, Object>> getLiveRecordListByActivity(Map<Object, Object> param);
    @DataSource("joint")
    List<Map<Object, Object>> getPreListBySign(Map<Object, Object> param);
    @DataSource("joint")
    List<Map<Object, Object>> getPreListByNoneSign(Map<Object, Object> param);
    /**
     * 回放记录
     * @param param
     * @return
     */
    @DataSource("joint")
    List<Map<Object, Object>> getPlaybackList(Map<Object, Object> param);

    /**
     * 直播标签
     * @return
     */
    @DataSource("joint")
    List<Map<Object, Object>> getLiveTagList(Map<Object, Object> param);

    /**
     * 缤纷娱乐 抢先报名
     * @return
     */
    @DataSource("joint")
    List<Map<Object, Object>> getActivityList();

    /**
     * 缤纷娱乐 节目频道
     * @return
     */
    @DataSource("joint")
    List<Map<Object, Object>> getTvShowList();

//    统计签约或者非签约直播记录
    @Deprecated
    @DataSource("joint")
    Integer sumLiveRecordBySigntype(Map<Object, Object> param);

//    统计活跃或不活跃直播记录
    @DataSource("joint")
    Integer sumLiveRecordByActivity(Map<Object, Object> param);

    // 统计签约主播直播记录
    @DataSource("joint")
    Integer sumSignLiveRecord(Map<Object, Object> param);
    // 统计非签约主播记录
    @DataSource("joint")
    Integer sumNoneSignLiveRecord(Map<Object, Object> param);





}
