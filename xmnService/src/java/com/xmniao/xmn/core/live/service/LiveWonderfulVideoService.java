package com.xmniao.xmn.core.live.service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveWonderfulUpdateRequest;
import com.xmniao.xmn.core.common.request.live.LiveWonderfulVideoRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveAnchorVideoDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.WonderfulVideoEntity;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.RedisGlobalLockUtils;
import com.xmniao.xmn.core.util.VersionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 精彩时刻视频
 * Created by Administrator on 2017/4/12.
 */
@Service
public class LiveWonderfulVideoService {

    private final Logger log = Logger.getLogger(LiveWonderfulVideoService.class);
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private AnchorLiveRecordDao anchorLiveRecordDao;
    @Autowired
    private LiveAnchorVideoDao liveAnchorVideoDao;
    @Autowired
    private String fileUrl;
    @Autowired
    private LiveUserDao liveUserDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisGlobalLockUtils redisGlobalLockUtils;
    public static String updateViewCountRedisKey = "add_share_video_view_count";
    @Autowired
    private LiveHomeV2Service liveHomeV2Service;


    /**
     * 精彩时刻视频
     * @param liveWonderfulVideoRequest
     * @return
     */
    public Object queryWonderfulVideoV1(LiveWonderfulVideoRequest liveWonderfulVideoRequest) {
        try {
            int wonderfulLiveRecordNums = Integer.parseInt(propertiesUtil.getValue("wonderfulLiveRecordNums",
                    "conf_common.properties"));
            int appVersion = VersionUtil.getVersionCode(liveWonderfulVideoRequest);
            return queryWonderfulVideo(1, wonderfulLiveRecordNums, false, 1, appVersion, liveWonderfulVideoRequest);
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("获取精彩时刻视频失败");
            return new BaseResponse(ResponseCode.FAILURE, "获取精彩时刻视频失败");
        }
    }

    /**
     * 精彩时刻视频
     * @param request
     * @return
     */
    public Object queryWonderfulVideoV2(LiveWonderfulVideoRequest request) {
        int appVersion = VersionUtil.getVersionCode(request);
        return queryWonderfulVideo(request.getPage(), request.getPageSize(), true, request.getmType(), appVersion, request);
    }

    /**
     * 根据类型刷选精彩时刻
     * @param mType
     * @param page
     * @param pageSize
     * @return
     */
    private  List<Map<Object, Object>> wonderfulVideoByType(Integer mType, int page, int pageSize) {
        List<Map<Object, Object>> wonderfulVideo = null;
        if (mType != null && mType == 2) {  //新人推荐
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("page", page);
            param.put("pageSize", pageSize);
            wonderfulVideo = liveAnchorVideoDao.getRecommendVideoByNoneSign(param);
        } else {  //精选推荐
            wonderfulVideo = getWonderfulVideoByRecommend(page, pageSize);
        }
        return wonderfulVideo;
    }

    private Object queryWonderfulVideo(Integer page, Integer pageSize, boolean isV2, Integer mType, int appVersion, BaseRequest request) {
        try {

            // api 2
            boolean is830Version = liveHomeV2Service.is830Version(request);
//            类型：0直播 1回放(关联t_live_record) 2精彩视频（关联t_live_anchor_vedio） 3预告 4节目
            Map<String, Object> map = liveHomeV2Service.subTitleMap(false, 2);


            // 类型1 为精选视频 2 为 新人推荐
            if (mType == 1 && is830Version && isV2) {
                MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取精彩时刻视频成功");
                Map<Object, Object> resultMap = new HashMap<Object, Object>();
                resultMap.put("wonderfulVideo", new ArrayList<>());
                map.put("showStatus", 3);
                resultMap.putAll(map);
                response.setResponse(resultMap);
                return response;
            }


            List<WonderfulVideoEntity> resultVideo = new ArrayList<WonderfulVideoEntity>();
            // 优先从视频推荐表中获取，不足则从记录记录表中获取
            List<Map<Object, Object>> wonderfulVideo = wonderfulVideoByType(mType, page, pageSize);
            updateRecommendVideoResult(wonderfulVideo, resultVideo);

            if (!isV2) {
                if (resultVideo == null || resultVideo.size() < pageSize) {
                    int size = pageSize - (resultVideo == null ? 0 : resultVideo.size());
                    // 从直播记录表中获取推荐视频
                    wonderfulVideo = getWonderfulVideoByLiveRecord(size);
                    updateLiveRecordVideoResult(wonderfulVideo, resultVideo);
                }
            }

            // signType和uid都需要跨库查询 。。。
            HashSet<Integer> idSet = new HashSet<Integer>();
            for (WonderfulVideoEntity result : resultVideo) {
                idSet.add(result.getAnchorId());
            }
            Map<Object, Map<Object, Object>> userMap = getUserInfoByIds(Arrays.asList(idSet.toArray()));
            if (userMap != null) {
                for (WonderfulVideoEntity result : resultVideo) {
                    Integer anchorId = result.getAnchorId();
                    Map<Object, Object> userInfo = userMap.get(anchorId);
                    if (userInfo != null) {
                        Integer signType = userInfo.get("signType") == null ? 0 : Integer.parseInt(userInfo.get("signType").toString());
                        result.setUid(userInfo.get("uid") == null ? 0 : Integer.parseInt(userInfo.get("uid").toString()));
                        result.setSignType(signType);
                        //v2添加
                        result.setSex(userInfo.get("sex") == null ? 0 : Integer.parseInt(userInfo.get("sex").toString()));
                    }
                }
            }
            // 361之后，精选推荐，也是网格
            boolean tabTypeCondition = mType != null && (mType == 2 || (mType == 1 && appVersion > 361));
            // 新人推荐，需要加网格
            if (isV2 && tabTypeCondition && resultVideo.size() % 2 != 0) {
                String defaultLiveImg = "";
                String defaultLiveDesc = "";
                try{
                    //从配置文件中获取当场直播区间
                    defaultLiveImg = propertiesUtil.getValue("defaultLiveImg", "conf_common.properties");
                    defaultLiveDesc = propertiesUtil.getValue("defaultLiveDesc", "conf_common.properties");
                }catch(Exception e){
                    e.printStackTrace();
                    log.info("精彩时刻，解析配置失败，splitSize， defaultLiveImg， conf_common.properties");
                }

                WonderfulVideoEntity entity = new WonderfulVideoEntity();
                entity.setDefaultDesc(defaultLiveDesc);
                entity.setDefaultImg(defaultLiveImg);
                entity.setIsNormalLive(0);
                resultVideo.add(entity);
            }

            //响应
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取精彩时刻视频成功");
            Map<Object, Object> resultMap = new HashMap<Object, Object>();
            resultMap.put("wonderfulVideo", resultVideo);
            if (resultVideo.size() == 0) {
                map.put("showStatus", 3);
            }
            resultMap.putAll(map);
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("获取精彩时刻视频失败");
            return new BaseResponse(ResponseCode.FAILURE, "获取精彩时刻视频失败");
        }
    }

    /**
     * 更新精彩时刻视频观看次数
     * @param liveWonderfulUpdateRequest
     * @return
     */
    public Object updateVideoViewCount(LiveWonderfulUpdateRequest liveWonderfulUpdateRequest) {
        try {
            boolean update = updateViewCount(liveWonderfulUpdateRequest.getVideoToken(), liveWonderfulUpdateRequest.getVideoId(), true);
            if (!update) {
                return new BaseResponse(ResponseCode.FAILURE, "更新精彩视频观看次数失败,视频token无效");
            }
            return new BaseResponse(ResponseCode.SUCCESS, "更新精彩视频观看次数成功");
        } catch (Exception e) {
            log.warn("更新精彩视频观看次数失败，id=" + String.valueOf(liveWonderfulUpdateRequest.getVideoId()));
            return new BaseResponse(ResponseCode.FAILURE, "更新精彩视频观看次数失败");
        }
    }

    private Map<Object, Map<Object, Object>> getUserInfoByIds(List<Object> ids) {
        try {
            if (ids == null || ids.size() == 0) {
                return null;
            }
            Map<Object, Map<Object, Object>> map = new HashMap<Object, Map<Object, Object>>();
            List<Map<Object,Object>> userInfo = liveUserDao.findAllByIdList(ids);
            for (Map<Object, Object> user : userInfo) {
                Object id = user.get("id");  //主播id
                map.put(id, user);
            }
            return map;
        } catch (Exception e) {
            log.warn("获取用户信息失败：ids=" + String.valueOf(ids.toString()));
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过t_live_anchor_video表查询
     * @return
     */
    private List<Map<Object, Object>> getWonderfulVideoByRecommend(int page, int pageSize) {
        try {
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("page", page);
            param.put("pageSize", pageSize);
            List<Map<Object, Object>> wonderfulLiveRecordList = liveAnchorVideoDao.getLiveHomeRecommendVideoV2(param);
            return wonderfulLiveRecordList;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("获取推荐精彩时刻视频失败");
            throw new IllegalStateException("获取推荐精彩时刻视频失败");
        }
    }

    /**
     * 通过直播记录获取精彩时刻视频
     * @return
     */
    private List<Map<Object, Object>> getWonderfulVideoByLiveRecord(Integer pageSize) {
        try {
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("page", 1);
            paramMap.put("type", 2);  //type = 1 热门回放   type = 2 最新精彩时刻回放
            paramMap.put("limit", pageSize);
            List<Map<Object, Object>> wonderfulLiveRecordList = anchorLiveRecordDao.queryPlayBackRecord(paramMap);
            return wonderfulLiveRecordList;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("获取直播记录精彩视频失败");
        }
        return null;
    }

    void updateRecommendVideoResult(List<Map<Object, Object>> wonderfulVideo, List<WonderfulVideoEntity> resultVideo) {
        if (wonderfulVideo == null || wonderfulVideo.size() == 0) {
            return;
        }

        for (int i = 0; i < wonderfulVideo.size(); i++) {
            Map<Object, Object> videoInfo = wonderfulVideo.get(i);
            if (videoInfo == null) {
                continue;
            }
            WonderfulVideoEntity entity = new WonderfulVideoEntity();
            entity.setAnchorId(videoInfo.get("anchor_id") == null ? 0 : Integer.parseInt(videoInfo.get("anchor_id").toString()));
            entity.setCover(videoInfo.get("cover_url") == null ? "" : fileUrl + videoInfo.get("cover_url"));
            entity.setTitle(videoInfo.get("title") == null ? "" : videoInfo.get("title").toString());
            entity.setNname(videoInfo.get("anchor_name") == null ? "" : videoInfo.get("anchor_name").toString());
            entity.setView_count(videoInfo.get("view_count") == null ? 0 : Integer.parseInt(videoInfo.get("view_count").toString()));
            entity.setVideoId(videoInfo.get("id") == null ? 0 : Integer.parseInt(videoInfo.get("id").toString()));
            entity.setVedioUrl(videoInfo.get("video_url") == null ? "" : videoInfo.get("video_url").toString());
            entity.setType(1);
            entity.setLiveRecordId(0);
            entity.setAnchorRoomNo("");
            entity.setZhiboType(3);
            entity.setSignType(0);
            entity.setUid(0);
            entity.setIsNormalLive(1);
            resultVideo.add(entity);
        }
    }

    void updateLiveRecordVideoResult(List<Map<Object, Object>> wonderfulVideo, List<WonderfulVideoEntity> resultVideo) {
        if (wonderfulVideo == null || wonderfulVideo.size() == 0) {
            return;
        }
        for (int i = 0; i < wonderfulVideo.size(); i++) {
            Map<Object, Object> recordInfo = wonderfulVideo.get(i);
            if (recordInfo == null) {
                continue;
            }
            Map<Object, Object> videoInfo = wonderfulVideo.get(i);
            WonderfulVideoEntity entity = new WonderfulVideoEntity();
            entity.setAnchorId(recordInfo.get("anchor_id") == null ? 0 : Integer.parseInt(recordInfo.get("anchor_id").toString()));
            entity.setCover(recordInfo.get("zhibo_cover") == null ? "" : fileUrl + recordInfo.get("zhibo_cover").toString());
            entity.setTitle(recordInfo.get("zhibo_title") == null ? "" : recordInfo.get("zhibo_title").toString());
            entity.setNname(recordInfo.get("nname") == null ? "" : recordInfo.get("nname").toString());
            entity.setView_count(recordInfo.get("view_count") == null ? 0 : Integer.parseInt(recordInfo.get("view_count").toString()));
            entity.setVideoId(videoInfo.get("id") == null ? 0 : Integer.parseInt(videoInfo.get("id").toString()));
            entity.setVedioUrl(recordInfo.get("zhibo_playback_url")==null?"":recordInfo.get("zhibo_playback_url").toString());
            entity.setType(2);
            entity.setLiveRecordId(recordInfo.get("id") == null ? 0 : Integer.parseInt(recordInfo.get("id").toString()));
            entity.setAnchorRoomNo(recordInfo.get("anchor_room_no") == null ? "" : recordInfo.get("anchor_room_no").toString());
            entity.setZhiboType(3);
            entity.setSignType(0);
            entity.setUid(0);
            entity.setIsNormalLive(1);
            resultVideo.add(entity);
        }
    }


    public String makeRandomVideoToken(int id) {
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(token, id+"");
        stringRedisTemplate.expire(token, 30 * 60, TimeUnit.SECONDS);
        return token;
    }

    public boolean updateViewCount(int id) {
        return updateViewCount(null, id, false);
    }

    //
    public boolean updateViewCount(String videoToken, int id, boolean isNeedToken) {
//        if (isNeedToken) {
//            String value = stringRedisTemplate.opsForValue().get(videoToken);
//            if (Integer.parseInt(value) != id) {
//                return false;
//            }
//        }
        //		更新观看次数
        try {
            Map<Object, Object> paramMap =  new HashMap<Object, Object>();
            paramMap.put("id", id);
            redisGlobalLockUtils.lock(updateViewCountRedisKey, updateViewCountRedisKey, 60, TimeUnit.SECONDS.toMillis(3));
            // 更新观看次数
            liveAnchorVideoDao.updateVnchorVideoViewCount(paramMap);
        } finally {
            redisGlobalLockUtils.unlock(updateViewCountRedisKey);
        }
        return true;
    }

}
