package com.xmniao.xmn.core.live.service;

import com.xmniao.xmn.core.api.controller.live.room.LiveRoomAdApi;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.catehome.dao.AdvertisingDao;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveRoomAdRequest;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22.
 */
@Service
public class LiveRoomAdService {

    private final Logger log = Logger.getLogger(LiveRoomAdService.class);

    @Autowired
    private AdvertisingDao advertisingDao;
    @Autowired
    private String fileUrl;


    public BaseResponse getLiveRoomAdTask(LiveRoomAdRequest request) {
        try {
            List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();

            List<Map<Object, Object>>  advList = advertisingDao.selectLiveHomeAdList(request.getCityId());
            if (advList != null) {
                for (Map<Object, Object> adv : advList) {
                    Integer live_begin = adv.get("live_begin") == null ? null : Integer.parseInt(adv.get("live_begin").toString());
                    Integer live_end = adv.get("live_end") == null ? null : Integer.parseInt(adv.get("live_end").toString());
                    Integer live_in = adv.get("live_in") == null ? null : Integer.parseInt(adv.get("live_in").toString());
                    Map<Object, Object> map = null;
                    if (live_begin != null && live_begin == 1) {  //进入直播间
                        map = toAdObject(adv, 1);
                        resultList.add(map);
                    }
                    if (live_in != null && live_in == 1) {  //直播中
                        map = toAdObject(adv, 2);
                        resultList.add(map);
                    }
                    if (live_end != null && live_end == 1) {  //直播结束
                        map = toAdObject(adv, 3);
                        resultList.add(map);
                    }
                }
            }
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("adList", resultList);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播间广告成功");
            response.setResponse(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("获取直播间广告失败");
            return new BaseResponse(ResponseCode.FAILURE,"获取直播间广告失败");
        }
    }

    private Map<Object, Object> toAdObject(Map<Object, Object> adv, int adType) {
//        类型: 1-图片, 2-视频
        Integer show_type = adv.get("show_type") == null ? 0 : Integer.parseInt(adv.get("show_type").toString());
        String start_url = adv.get("start_url") == null ? "" : adv.get("start_url").toString();
        String video_url = adv.get("video_url") == null ? "" : adv.get("video_url").toString();
        String pic = adv.get("pic") == null ? "" : adv.get("pic").toString();
        String adUrl = "";
        String mediaUrl = "";
        if (show_type == 1) {  //图片
            mediaUrl = pic.equals("") ? "" : fileUrl + pic;
            adUrl = start_url;
        } else if (show_type == 2) {  //视频
            mediaUrl = video_url;
            adUrl = start_url;
        }
        Integer duration = adv.get("count_down") == null ? 0 : Integer.parseInt(adv.get("count_down").toString());
        Integer interval = adv.get("intervals") == null ? 0 : Integer.parseInt(adv.get("intervals").toString()) * 60;
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("duration", duration);  //单个广告播放时间
        map.put("interval", interval);  //间隔时间
//        map.put("loopTimes", -1);  //循环播放次数 -1代表无限循环
        map.put("mediaUrl", mediaUrl);  //图片或视频url
        map.put("mediaType", show_type);  //广告类型 0 未知 1 图片 2 视频
        map.put("adUrl", adUrl);   //跳转url
//        map.put("adTitle", "");   //广告标题
        map.put("adType", adType);   //直播广告类型   1 直播开始 2 直播中间 3 直播结束
        return map;
    }
}
