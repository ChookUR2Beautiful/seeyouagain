package com.xmniao.xmn.core.live.service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.request.live.*;
import com.xmniao.xmn.core.common.request.vod.VodListRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.util.dataSource.DataSource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */
public interface LiveHomeV2Service {
    // 金牌推荐栏目 / 新人推荐栏目 缤纷娱乐栏目 / 美食撩味栏目 直播记录
    BaseResponse getLiveRecordList(LiveHomeRecordV2Request request);

    // 金牌推荐栏目 / 新人推荐栏目 精彩预告
    BaseResponse getWonderfulAdvList(LiveHomeAdvV2Request request);

    // 回放记录 (美食撩味栏目)
    BaseResponse getPlaybackList(LiveHomePlaybackV2Request request);

    // 直播首页 标签
    Object getTagList(LiveHomeTagRequest request);

    // 缤纷娱乐 抢先报名
    BaseResponse getActivityList(BaseRequest request);

    // 缤纷娱乐 节目频道
    BaseResponse getTvShowList(BaseRequest request);

    // 直播首页 banner
    BaseResponse getBannerList(BaseRequest request);


    Integer getUid(String sessionToken);

    BaseResponse getTabNameList(BaseRequest request);

    BaseResponse getTabNameListV2(BaseRequest request);

    boolean is830Version(BaseRequest request);

    Map<String, Object> subTitleMap(boolean isNeedSubTitle, int mtype);

}
