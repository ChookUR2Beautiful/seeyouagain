package com.xmniao.xmn.core.live.service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveTopicRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 直播精选专题
 * Created by Administrator on 2017/4/5.
 */
@Service
public class LiveTopicService {

    /**
     * 日志
     */
    private final Logger log = Logger.getLogger(LiveTopicService.class);

    @Autowired
    private LiveUserDao liveUserDao;

    @Autowired
    private AnchorLiveRecordDao anchorLiveRecordDao;

    @Autowired
    private LiveHomeService liveHomeService;

    @Autowired
    private SessionTokenService sessionTokenService;

    /**
     * 获取直播专题推荐
     * @param liveTopicRequest
     * @return
     */
    public Object getLiveTopicRecommend(LiveTopicRequest liveTopicRequest) {
        try {

            // 内部测试账号  sign_type = 2   isInside = 1   测试账号 内部使用账号， 则显示
            Map<Object, Object> param = new HashMap<>();
            param.put("page", liveTopicRequest.getPage());
            param.put("pageSize", liveTopicRequest.getPageSize());
            List<Map<Object, Object>> insideTestAnchors = liveUserDao.findInsideTestAnchors(param);

            List<Map<Object, Object>> topicLiveRecords = null;
            if (insideTestAnchors != null && insideTestAnchors.size() > 0) {
                List<Integer> insideIds = new ArrayList<>();
                for (Map<Object, Object> anchor : insideTestAnchors) {
                    insideIds.add((Integer) anchor.get("id"));  //直播用户ID
                }
                // 查询精选专题直播（官方直播）
                topicLiveRecords = queryLiveTopicRecord(liveTopicRequest, insideIds);
            }

            if(topicLiveRecords == null) {
                topicLiveRecords = new ArrayList<>();
            }

            Map<Object, Object> resultMap = new HashMap<>();
            resultMap.put("topicLiveRecords", topicLiveRecords);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播精选专题成功");
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取直播精选专题失败,错误信息如下:" + e.getMessage());
            return new BaseResponse(ResponseCode.FAILURE, "获取直播精选专题失败");
        }
    }

    /**
     * 直播精选专题
     * @param liveTopicRequest
     * @param insideIds
     * @return
     */
    List<Map<Object, Object>> queryLiveTopicRecord(LiveTopicRequest liveTopicRequest, List<Integer> insideIds) throws IOException {
        //判断用户是否登录过,登录过则获取uid
        String uid = "";
        String phone = null;
        Map<Object, Object> liverMap = null;
        if (liveTopicRequest != null && StringUtils.isNotEmpty(liveTopicRequest.getSessiontoken())) {
            uid = sessionTokenService.getStringForValue(liveTopicRequest.getSessiontoken()) + "";
            //如果token过期,则当没有登录处理
            if (StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
                liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
                phone = liverMap.get("phone") == null ? null : String.valueOf(liverMap.get("phone"));
            }
        }


        //纬度
        Double latitude = liveTopicRequest.getLatitude();
        //经度
        Double longitude = liveTopicRequest.getLongitude();

        //组装参数
        Map<Object,Object> paramMap = new HashMap<Object,Object>();
        paramMap.put("recommended", 1);
        paramMap.put("zhiboType", 1);
        paramMap.put("page", liveTopicRequest.getPage());
        paramMap.put("limit", liveTopicRequest.getPageSize());
        paramMap.put("insideIds", insideIds);  //直播精选专题，刷选主播id列表
        if (latitude != null && longitude != null && latitude != 0 && longitude != 0) {
            paramMap.put("longitude", longitude);
            paramMap.put("latitude", latitude);
        }
        paramMap.put("phone", phone);  //过滤手机号码
        List<Map<Object, Object>> currentLiveRecordList = anchorLiveRecordDao.queryLiveRecordList(paramMap);
        if (currentLiveRecordList != null && currentLiveRecordList.size() > 0) {
            //调整直播列表返回字段
            return liveHomeService.updateLiveRecordList(currentLiveRecordList, liverMap);
        }
        return null;
    }


}
