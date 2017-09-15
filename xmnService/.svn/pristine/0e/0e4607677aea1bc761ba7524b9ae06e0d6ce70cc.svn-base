package com.xmniao.xmn.core.kscloud.service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.live.LVBChannelInfoApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveShareRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.vod.Module.Live;
import com.xmniao.xmn.core.vod.QcloudApiModuleCenter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/8/3.
 */
@Service
public class LVBChannelInfoService {

    private final Logger log = Logger.getLogger(LVBChannelInfoService.class);
    @Autowired
    private AnchorLiveRecordDao anchorLiveRecordDao;
    @Autowired
    private KSCloudService ksCloudService;


    /**
     * 获取直播信息
     * @param liveShareRequest
     * @return
     */
    public Object getLiveChannelInfo(LiveShareRequest liveShareRequest) {
        //响应
        MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播频道详情信息成功");

        //结果集,默认为推流结果为直播中
        Map<Object, Object> resultMap = new HashMap<>();

        int channelStatus = 1;
        try {
            //直播记录id
            Integer liveRecordId = liveShareRequest.getLiveRecordId();
            Map<Object, Object> paramMap = new HashMap<>();
            //查询直播记录信息
            paramMap.put("id", liveRecordId);
            LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
            if (liveRecordInfo == null) {
                return new BaseResponse(ResponseCode.FAILURE, "查无直播记录信息");
            }
            String video_url = liveRecordInfo.getVedio_url();
            if (video_url != null && ksCloudService.isKSL(video_url)) {  //金山云
                channelStatus = kscLiveStatus(liveRecordInfo);
            } else {
                channelStatus = tslLiveStatus(liveRecordInfo);
            }
            //获取直播频道状态0 无输入流 ，1   直播中， 2  异常，3  关闭',
            resultMap.put("channelStatus", channelStatus);
            //响应
            response.setResponse(resultMap);
            return response;

        } catch (Exception e) {
            log.info("查询频道详情信息失败,直播记录id:" + liveShareRequest.getLiveRecordId(), e);
            e.printStackTrace();
            return new MapResponse(ResponseCode.FAILURE, "查询频道详情信息失败");
        }
    }


    /**
     * 金山直播
     * @param liveRecordInfo
     */
    private int kscLiveStatus(LiveRecordInfo liveRecordInfo) {
        Integer zhiboType = liveRecordInfo.getZhibo_type();
        if (zhiboType == null || zhiboType != 1) {
            return 3;
        } else {
            return 1;
        }
    }

    /**
     * 腾讯直播
     * @param liveRecordInfo
     */
    private int tslLiveStatus(LiveRecordInfo liveRecordInfo) throws Exception {
        String channel_id = liveRecordInfo.getChannel_id();
        //查询腾讯云直播频道状态
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Live(),"GET");
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        if (StringUtils.isNotEmpty(channel_id)) {
            //请求参数,频道号
            params.put("channelId",channel_id);
            /* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
            String channelResult = module.call("DescribeLVBChannel", params);
            //解析数据
            JSONObject jsonObj = JSONObject.parseObject(channelResult);
            //获取直播频道列表信息
            String channelInfoList = jsonObj.getString("channelInfo");
            //获取直播频道第一个信息
            JSONObject channelInfo = JSONObject.parseObject(JSONObject.parseArray(channelInfoList).getString(0));
            //获取直播频道状态0 无输入流 ，1   直播中， 2  异常，3  关闭',
            String channelStatus = channelInfo.getString("channel_status");
            //返回结果
            return Integer.parseInt(channelStatus);
        }
        return 1;
    }
}
