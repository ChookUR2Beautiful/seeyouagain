package com.xmniao.xmn.core.timer;

import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import sun.util.resources.CalendarData;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/2.
 */
@Service
public class KSCloudQuertzService {

    private final Logger log = Logger.getLogger(KSCloudQuertzService.class);
    @Autowired
    private AnchorLiveRecordDao anchorLiveRecordDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private DescribeLVBChannelQuertz describeLVBChannelQuertz;
    @Autowired
    private KSCloudService ksCloudService;

    /**
     * 检测心跳
     */
    public void checkHeartbeat() {
        log.info("执行定时任务，金山云直播心跳检测，开始------------------------");
        try {
            // 获取所有直播信息
            List<LiveRecordInfo> liveReordInfo = anchorLiveRecordDao.queryCurrentLiveRecord();
            if (liveReordInfo == null || liveReordInfo.size() == 0) {
                return;
            }
            for (LiveRecordInfo live : liveReordInfo) {
                try {
                    if (!isTimeOut(live)) {
                        continue;
                    }
                    log.info("开始 --------- 关闭超时金山直播：" + live.toString());
                    describeLVBChannelQuertz.clearLiveChannelByKSL(live);
                    log.info("结束 ---------- 关闭超时金山直播：" + live.toString());
                } catch (Exception e) {
                    log.warn("执行检测金山云心跳异常: ", e);
                }
            }
        } catch (Exception e) {
            log.warn("金山云心跳处理异常: ", e);
        } finally {
            log.info("执行定时任务，金山云直播心跳检测，结束------------------------");
        }
    }


    private boolean isTimeOut(LiveRecordInfo live ) {
        Long id = live.getId();
        if (id == null) {
            return false;
        }
        String vedio_url = live.getVedio_url();
        // 非金山云直播
        if (vedio_url != null && !ksCloudService.isKSL(vedio_url)) {  //金山云
            return false;
        }
        log.info("金山云直播：id=" + id + " vedio_url=" + vedio_url);
        String key = KSCloudService.redis_ksc_live_header + id;
        String lastUpdateStr = stringRedisTemplate.opsForValue().get(key);
        long lastUpdateTime = 0;
        if (lastUpdateStr != null && !lastUpdateStr.trim().equals("")) {
            try {
                lastUpdateTime = Long.parseLong(lastUpdateStr);
            } catch (Exception e) {
                log.warn("金山云key不存在");
            }
        }
        int timeout = KSCloudService.timeoutHeartbeat;
        if (System.currentTimeMillis() - lastUpdateTime >= TimeUnit.SECONDS.toMillis(timeout)) {
            // 正常
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(live.getCreate_time());
        long createTime = calendar.get(Calendar.SECOND);
        calendar.setTime(new Date());
        long now = calendar.get(Calendar.SECOND);

        if ( (now - createTime ) <= timeout) {
            // 正常
            return false;
        }

        log.info("直播心跳超时:" + live.toString());
        return true;
    }



}
