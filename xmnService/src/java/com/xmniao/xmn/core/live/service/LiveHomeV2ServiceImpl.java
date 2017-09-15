package com.xmniao.xmn.core.live.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.*;
import com.xmniao.xmn.core.common.request.vod.VodListRequest;
import com.xmniao.xmn.core.common.service.CommonService;
import com.xmniao.xmn.core.integral.dao.IntegralMallDao;
import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import com.xmniao.xmn.core.live.common.LiveHomeConstants;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveHomeV2Dao;
import com.xmniao.xmn.core.live.dao.LiveRoomDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveHomePlayBackEntity;
import com.xmniao.xmn.core.live.entity.LiveHomeRecordEntity;
import com.xmniao.xmn.core.util.*;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.service.SellerService;
import org.apache.commons.collections.functors.ExceptionClosure;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.transport.ObjectTable;

import java.util.*;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class LiveHomeV2ServiceImpl implements LiveHomeV2Service {

    private final Logger log = Logger.getLogger(LiveHomeV2ServiceImpl.class);

    @Autowired
    private LiveHomeV2Dao liveHomeV2Dao;
    @Autowired
    private LiveUserDao liveUserDao;
    @Autowired
    private SessionTokenService sessionTokenService;
    @Autowired
    private LiveRoomDao liveRoomDao;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private UrsService ursService;
    @Autowired
    private IntegralMallDao integralMallDao;
    @Autowired
    private LiveHomeService liveHomeService;
    @Autowired
    private String fileUrl;
    @Autowired
    private String localDomain;
    @Autowired
    private AnchorSignTypeService anchorSignTypeService;
    @Autowired
    private AnchorLiveRecordDao anchorLiveRecordDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private KSCloudService ksCloudService;

    /**
     * 金牌推荐栏目 / 新人推荐栏目 缤纷娱乐栏目 / 美食撩味栏目 直播记录
     * @param request
     * @return
     */
    @Override
    public BaseResponse getLiveRecordList(LiveHomeRecordV2Request request) {
        try {
            Map<Object, Object> liveMap = liveList(request.getSessiontoken(), request.getPage(), request.getPageSize(),
                    request.getLongitude(), request.getLatitude(), request.getTabType(), true, request.getTagId(), request.getCity_id(),
                    request);
            //直播记录
            List<LiveHomeRecordEntity> resultList = (List<LiveHomeRecordEntity>) liveMap.get("liveList");
            Integer count = 0;
            try {
                count  = (Integer) liveMap.get("count");
                count = count == null ? 0 : count;
            } catch (Exception e) {
            }
            boolean is830Version = is830Version(request);
            Map<Object, Object> map = new HashMap<Object, Object>();
            // tabtype = 1, 2 && page = 1需要特殊处理
            Integer tabType = request.getTabType();
            int splitSize = 6;
            String defaultLiveImg = "";
            String defaultLiveDesc = "";
            try{
                //从配置文件中获取当场直播区间
                splitSize = Integer.parseInt(propertiesUtil.getValue("liveTab1SplitSize", "conf_common.properties"));
                defaultLiveImg = propertiesUtil.getValue("defaultLiveImg", "conf_common.properties");
                defaultLiveDesc = propertiesUtil.getValue("defaultLiveDesc", "conf_common.properties");
            }catch(Exception e){
                e.printStackTrace();
                log.info("直播首页，解析配置失败，splitSize， defaultLiveImg， conf_common.properties");
            }
            // 网络图，需要添加默认图片
            if (tabType == LiveHomeConstants.LIVE_HOME_TAB2 || tabType == LiveHomeConstants.LIVE_HOME_TAB4) {
                if (resultList != null && resultList.size() > 0 && resultList.size() % 2 != 0) {
                    LiveHomeRecordEntity entity = new LiveHomeRecordEntity();
                    entity.setIsNormalLive(0);
                    entity.setDefaultImg(defaultLiveImg);
                    entity.setDefaultDesc(defaultLiveDesc);
                    resultList.add(entity);
                }
            }

            if (tabType == LiveHomeConstants.LIVE_HOME_TAB1 && request.getPage() == 1) {
                if (is830Version) {
                    splitSize = 20;
                }
                splitLiveList(map, resultList, splitSize);
                map.put("liveList", new ArrayList<LiveHomeRecordEntity>());
            } else if (tabType == LiveHomeConstants.LIVE_HOME_TAB2 && request.getPage() == 1) {
                splitLiveList(map, resultList, splitSize);
                map.put("liveList", new ArrayList<LiveHomeRecordEntity>());
            } else {
                map.put("preLiveList", new ArrayList<LiveHomeRecordEntity>());
                map.put("lastLiveList", new ArrayList<LiveHomeRecordEntity>());
                map.put("liveList", resultList);
            }

//            类型：0直播 1回放(关联t_live_record) 2精彩视频（关联t_live_anchor_vedio） 3预告 4节目
            Map<String, Object> tmp = subTitleMap(is830Version , 0);

            log.warn("直播记录 preLiveList：" + map.get("preLiveList").toString());
            log.warn("直播记录 lastLiveList：" + map.get("lastLiveList").toString());
            log.warn("直播记录 liveList：" + map.get("liveList").toString());
            map.put("count", count);
            if (count == 0) {
                tmp.put("showStatus", 3);
            }
            map.putAll(tmp);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播记录成功");
            response.setResponse(map);
            return response;
        } catch (Exception e) {
            log.warn("获取直播记录失败:" + request.toString(), e);
            return new BaseResponse(ResponseCode.FAILURE, "获取直播记录失败");
        }
    }

    /**
     * 金牌推荐栏目 / 新人推荐栏目 精彩预告
     * @param request
     * @return
     */
    @Override
    public BaseResponse getWonderfulAdvList(LiveHomeAdvV2Request request) {
        try {
            Integer page = request.getPage();
            int pageSize = request.getPageSize();
            if (page == 1) {
                pageSize = 4;
                try{
                    //从配置文件中获取当场直播区间
                    pageSize = Integer.parseInt(propertiesUtil.getValue("liveHomeAdvSize", "conf_common.properties"));
                }catch(Exception e){
                    e.printStackTrace();
                    log.info("获取直播首页，精彩预告大小失败");
                }
            }
            if (!(request.getTabType() == LiveHomeConstants.LIVE_HOME_TAB1 ||
                    request.getTabType() == LiveHomeConstants.LIVE_HOME_TAB2)) {
                return new BaseResponse(ResponseCode.FAILURE, "获取精彩预告失败,类型不对");
            }

            Map<Object, Object> liveMap = liveList(request.getSessiontoken(), page, pageSize,
                    request.getLongitude(), request.getLatitude(), request.getTabType(), false, null, null, request);

            boolean is830Version = is830Version(request);
            Map<String, Object> tmpMap = subTitleMap(is830Version, 3);

            //直播记录
            List<Map<Object, Object>> resultList = (List<Map<Object, Object>>) liveMap.get("liveList");
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("liveList", resultList);
            if (resultList.size() == 0) {
                tmpMap.put("showStatus", 3);
            }
            map.putAll(tmpMap);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取精彩预告成功");
            response.setResponse(map);
            return response;
        } catch (Exception e) {
            log.warn("获取精彩预告失败:" + request.toString(), e);
            return new BaseResponse(ResponseCode.FAILURE, "获取精彩预告失败");
        }

    }

    /**
     * 回放记录 (美食撩味栏目)
     * @param request
     * @return
     */
    @Override
    public BaseResponse getPlaybackList(LiveHomePlaybackV2Request request) {
        return playBackList(request);
    }

    /**
     * 添加默认标签
     * 新人推荐，默认添加 附近和全国
     * 美食撩味，默认添加，全部
     * @param rType
     * @return
     */
    private List<Map<Object, Object>> getDefaultTagList(Integer rType) {
        List<Map<Object, Object>> defaultList = new ArrayList<Map<Object, Object>>();
        if (rType == null) {
            return defaultList;
        }

        Map<Integer, String> m = new HashMap<Integer, String>();
        try{
            String liveHomeTabAddList = propertiesUtil.getValue("liveHomeTabAddList", "conf_common.properties");
            JSONObject jsonObject = JSON.parseObject(liveHomeTabAddList);
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                m.put(Integer.parseInt(entry.getKey()), entry.getValue().toString());
            }
        } catch (Exception e) {
            log.warn("解析配置失败，liveHomeTabAddList");
            return defaultList;
        }
        if (rType == 1) {  //新人推荐， 默认添加附近和全国
            String near = m.get(1);  //附近
            String location = m.get(2);  //全国
            Map<Object, Object> t1 = new HashMap<Object, Object>();
            t1.put("tagId", LiveHomeConstants.Live_Home_Tag_City);
            t1.put("tagName", near);
            Map<Object, Object> t2 = new HashMap<Object, Object>();
            t2.put("tagId", LiveHomeConstants.Live_Home_Tag_National);
            t2.put("tagName", location);
            defaultList.add(t1);
            defaultList.add(t2);
        } else if (rType == 2) {  //美味撩味
            String all = m.get(3);
            Map<Object, Object> t1 = new HashMap<Object, Object>();
            t1.put("tagId", LiveHomeConstants.Live_Home_Tag_All);
            t1.put("tagName", all);
            defaultList.add(t1);
        }
        return defaultList;
    }

    /**
     * 直播首页 标签
     * @param request
     * @return
     */
    @Override
    public Object getTagList(LiveHomeTagRequest request) {
        try {

            boolean is830Version = is830Version(request);
            if (is830Version) {
                Map<Object, Object> result = new HashMap<Object, Object>();
                result.put("tagList", new ArrayList<>());
                MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取美食撩味标签成功");
                response.setResponse(result);
                return response;
            }



            List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
            int rtype = request.getRtype();
            int count = 0;
            String liveDefaultFilterPhone = "abc";
            int minLiveToShowTag = 0;
            try{
                liveDefaultFilterPhone = propertiesUtil.getValue("liveDefaultFilterPhone", "conf_common.properties");
                minLiveToShowTag = Integer.parseInt(propertiesUtil.getValue("minLiveToShowTag", "conf_live.properties"));
            }catch(Exception e){
                log.info("解析配置失败，liveDefaultFilterPhone, minLiveToShowTag ", e);
            }
            // 用户信息
            Integer uid = getUid(request.getSessiontoken());
            Map<Object, Object> liverMap = liverInfo(uid);
            String phone = liveDefaultFilterPhone;
            if (liverMap != null) {
                phone = liverMap.get("phone") == null ? liveDefaultFilterPhone : liverMap.get("phone").toString();
            }
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("phone", phone);
            if (rtype == 1) { //新人
                count = liveHomeV2Dao.sumNoneSignLiveRecord(param);
                param.put("tabType", 2);
            } else { //美食撩味
                count = liveHomeV2Dao.sumLiveRecordByActivity(param);
                param.put("tabType", 4);
            }

            // 直播数量达到一定数量，才显示标签
            if (count > minLiveToShowTag) {
                param.put("rtype", request.getRtype());
                List<Map<Object, Object>> tagList = liveHomeV2Dao.getLiveTagList(param);
                List<Map<Object, Object>> defaultTagList = getDefaultTagList(request.getRtype());
                resultList.addAll(defaultTagList);
                if (tagList != null) {
                    for (int i = 0; i < tagList.size(); i++) {
                        Map<Object, Object> tag = tagList.get(i);
                        Map<Object, Object> tmp = new HashMap<Object, Object>();
                        tmp.put("tagId", tag.get("tag_id"));
                        tmp.put("tagName", tag.get("tag_name") == null ? "" : tag.get("tag_name").toString());
                        resultList.add(tmp);
                    }
                }
            }

            Map<Object, Object> result = new HashMap<Object, Object>();
            result.put("tagList", resultList);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取美食撩味标签成功");
            response.setResponse(result);
            return response;
        } catch (Exception e) {
            log.warn("获取美食撩味标签失败", e);
            e.printStackTrace();
            return new BaseResponse(ResponseCode.FAILURE, "获取美食撩味标签失败");
        }
    }

    /**
     * 缤纷娱乐 抢先报名
     * @param request
     * @return
     */
    @Override
    public BaseResponse getActivityList(BaseRequest request) {
        try {
            List<Map<Object, Object>> activityList =  liveHomeV2Dao.getActivityList();
            List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
            if (activityList != null) {
                for (int i = 0; i < activityList.size(); i++) {
                    Map<Object, Object> item = activityList.get(i);
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("url", item.get("url") == null ? "" : item.get("url").toString());  //连接
                    map.put("imageUrl", item.get("image_url") == null ? "" : fileUrl + item.get("image_url").toString());  //图片
                    resultList.add(map);
                }
            }
            Map<Object, Object> resultMap = new HashMap<Object, Object>();
            resultMap.put("activityList", resultList);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取缤纷娱乐-抢先报名成功");
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            log.warn("获取缤纷娱乐-抢先报名失败", e);
            return new BaseResponse(ResponseCode.FAILURE, "获取缤纷娱乐-抢先报名失败");
        }
    }

    /**
     * 缤纷娱乐 节目频道
     * @param request
     * @return
     */
    @Override
    public BaseResponse getTvShowList(BaseRequest request) {
        try {

            boolean is830Version = is830Version(request);
//            类型：0直播 1回放(关联t_live_record) 2精彩视频（关联t_live_anchor_vedio） 3预告 4节目
            Map<String, Object> tmpMap = subTitleMap(is830Version, 4);


            List<Map<Object, Object>> tvShowList =  liveHomeV2Dao.getTvShowList();
            List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
            if (tvShowList != null) {
                for (int i = 0; i < tvShowList.size(); i++) {
                    Map<Object, Object> item = tvShowList.get(i);
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("title", item.get("title") == null ? "" : item.get("title").toString()); //标题
                    map.put("subTitle", item.get("sub_title") == null ? "" : item.get("sub_title").toString());  //副标题
                    map.put("imageUrl", item.get("image_url") == null ? "" : fileUrl + item.get("image_url").toString()); //图片链接
                    map.put("url", item.get("url") == null ? "" : item.get("url").toString()); //链接
                    map.put("number", item.get("number") == null ? 0 : item.get("number").toString()); //期数
                    resultList.add(map);
                }
            }
            Map<Object, Object> resultMap = new HashMap<Object, Object>();
            resultMap.put("tvShowList", resultList);
            if (resultList.size() == 0) {
                tmpMap.put("showStatus", 3);
            }
            resultMap.putAll(tmpMap);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取缤纷娱乐-节目频道成功");
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            log.warn("获取缤纷娱乐-节目频道失败", e);
            return new BaseResponse(ResponseCode.FAILURE, "获取缤纷娱乐-节目频道失败");
        }
    }

    /**
     * 获取直播banner
     * @param request
     * @return
     */
    @Override
    public BaseResponse getBannerList(BaseRequest request) {
        try {
            Integer uid = getUid(request.getSessiontoken());
            Map<Object, Object> liverMap = liverInfo(uid);
            //结果集
            Map<Object, Object> resultMap = new HashMap<>();
            //组装参数
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("position", 4);//banner位置信息，1 附近美食，2 积分商城 3寻蜜客主页 4直播首页
            paramMap.put("status", 1);//上线状态 0.待上线，1.已上线，2.已下线
            List<BannerEntity> bannerList = integralMallDao.queryBannerList(paramMap);
            if (bannerList != null && bannerList.size() > 0) {
                //解析直播首页的banner图数据
                List<Map<Object, Object>> bannerListResult = liveHomeService.getBanner(bannerList, 1);
                commonService.checkIOSManorVersion(request, 9, bannerListResult);
                //放入结果集
                resultMap.put("bannerList", bannerListResult);
            } else {
                //如查询直播首页banner图没有或者是异常，则给定默认的图片
                resultMap.put("defultBannerImg", localDomain + "/img/liveHomeBanner.png");
            }
            resultMap.put("existCustomLiveRecord", 0);
            // 只有主播才会有可能自定义续播
            if (liverMap != null && liverMap.get("id") != null) {
                if (Integer.parseInt(liverMap.get("utype").toString()) == 1) {
                    //查询15分钟内的最新一条自定义直播记录信息
                    Map<Object, Object> customLiveRecordMap = anchorLiveRecordDao.queryCustomLiveRecordByAnchorId(Integer.parseInt(liverMap.get("id").toString()));
                    if (customLiveRecordMap != null && customLiveRecordMap.size() > 0) {
                        //是否存在自定义直播记录信息  0否 1是
                        resultMap.put("existCustomLiveRecord", 1);
                        //自定义直播记录id
                        resultMap.put("liveRecordId", customLiveRecordMap.get("id"));
                        //自定义标题
                        resultMap.put("title", customLiveRecordMap.get("zhibo_title")==null?"":customLiveRecordMap.get("zhibo_title"));
                        //自定义商家名称
                        resultMap.put("sellerName", customLiveRecordMap.get("sellername")==null?"":customLiveRecordMap.get("sellername"));
                        //自定义商家地址
                        resultMap.put("address", customLiveRecordMap.get("zhibo_address")==null?"":customLiveRecordMap.get("zhibo_address"));

                    }
                }
            }
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播首页banner成功");
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            log.warn("获取直播首页banner失败:" + request.toString(), e);
            return new BaseResponse(ResponseCode.FAILURE, "获取直播首页banner失败");
        }

    }

    /**
     * @param sessiontoken
     * @param page
     * @param pageSize
     * @param longitude
     * @param latitude
     * @param tabType
     * @param isLive true 为直播 false 为预告
     * @param tagId 标签Id
     * @param cityId
     * @return
     * @throws Exception
     */
    private Map<Object, Object> liveList(String sessiontoken, Integer page, Integer pageSize, Double longitude, Double latitude,
                                         Integer tabType, boolean isLive, Integer tagId, Integer cityId, BaseRequest request) throws Exception{
        int zhiboGiftRange = 250000;
        String liveDefaultFilterPhone = "abc";
        try{
            //从配置文件中获取当场直播区间
            zhiboGiftRange = Integer.parseInt(propertiesUtil.getValue("zhiboGiftRange", "conf_common.properties"));
            liveDefaultFilterPhone = propertiesUtil.getValue("liveDefaultFilterPhone", "conf_common.properties");
        }catch(Exception e){
            e.printStackTrace();
            log.info("解析配置失败，zhiboGiftRange， liveDefaultFilterPhone");
        }

        // 用户信息
        Integer uid = getUid(sessiontoken);
        Map<Object, Object> liverMap = liverInfo(uid);
        String phone = liveDefaultFilterPhone;
        if (liverMap != null) {
            phone = liverMap.get("phone") == null ? liveDefaultFilterPhone : liverMap.get("phone").toString();
        }


        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        List<Integer> liveRecordIds = new ArrayList<Integer>();
        List<Integer> liveAnchorUidList = new ArrayList<Integer>();

        Map<Object, Object> param = new HashMap<Object, Object>();
        param.put("page", page);
        param.put("pageSize", pageSize);
        param.put("longitude", longitude);
        param.put("latitude", latitude);
        param.put("zhiboGiftRange", zhiboGiftRange);
        param.put("phone", phone);
        // 直播（直播或预告）
        List<Map<Object, Object>> liveList = null;
        boolean is830Version = is830Version(request);
        int count = 0;
        boolean isFilterKSL = ksCloudService.isFilterKSLive(request);
        if (isLive) {  //直播
            // 830版本只有tab1有直播
            if (is830Version && tabType != LiveHomeConstants.LIVE_HOME_TAB1) {
                liveList = null;
            } else {
                liveList = liveRecordListByTabType(param, tabType, tagId, cityId, is830Version, isFilterKSL);
                if (page == 1) {  //统计直播数量
                    count = getLiveCountByTab(tabType, phone, isFilterKSL);
                }
            }
        } else {  // 预告
            // 830版本只有tab1有直播
            if (is830Version && tabType != LiveHomeConstants.LIVE_HOME_TAB1) {
                liveList = null;
            } else {
                liveList = wonderfulAdvListByTabType(param, tabType);
            }
        }

        if (liveList == null || liveList.size() == 0) {
            resultMap.put("liveList", new ArrayList<>());
            return resultMap;
        }
        for (Map<Object, Object> live : liveList) {
            Integer id = Integer.parseInt(live.get("id").toString()); // 直播记录Id
            liveRecordIds.add(id);
            String anchorId = live.get("anchor_id").toString();  //主播Id
            liveAnchorUidList.add(Integer.parseInt(anchorId));
        }

        List<Map<Object, Object>> tagList = null;
        if (liveRecordIds != null && liveRecordIds.size() > 0) {
            tagList = liveRoomDao.queryLiveRecordTagConf(liveRecordIds);
        }
        // 直播标签
        Map<String, List<Map<Object, Object>>> tagMap = new HashMap<String, List<Map<Object, Object>>>();
        if (tagList != null) {
            for (Map<Object, Object> map : tagList) {
                String liveRecordId = map.get("liveRecordId").toString();
                List<Map<Object, Object>> tags = tagMap.get(liveRecordId);
                if (tags == null) {
                    tags = new ArrayList<Map<Object, Object>>();
                    tagMap.put(liveRecordId, tags);
                }
                tags.add(map);
            }
        }

        //主播列表
        List<Map<Object, Object>> anchorList = null;
        if (liveAnchorUidList != null && liveAnchorUidList.size() > 0) {
            anchorList = liveUserDao.queryLiverInfoByIdList(liveAnchorUidList);
        }
        Map<String, Map<Object, Object>> anchorMap = new HashMap<String, Map<Object, Object>>();
        if (anchorList != null) {
            for (Map<Object, Object> anchor : anchorList) {
                String anchorId = anchor.get("id").toString();
                anchorMap.put(anchorId, anchor);
            }
        }
        // 批量查询签约标识
        Map<Integer, Integer> signTypeMap = anchorSignTypeService.signTypeMap(anchorList);

        // 返回数据
        List<LiveHomeRecordEntity> resultList = new ArrayList<LiveHomeRecordEntity>();
        for (int i = 0; i < liveList.size(); i++) {
            Map<Object, Object> liveMap = liveList.get(i);  //直播记录
            String liveId = liveMap.get("id").toString();
            String anchorId = liveMap.get("anchor_id").toString();
            List<Map<Object, Object>> liveTagList = tagMap.get(liveId);
            Map<Object, Object> liver = anchorMap.get(anchorId);
            List<String> liveTagNameList = new ArrayList<String>();
            if (liveTagList != null) {
                for (Map<Object, Object> tag : liveTagList) {
                    if (tag.get("tagName") != null) {
                        liveTagNameList.add(tag.get("tagName").toString());
                    }
                }
            }
            //  新人直播，标签最多返回1
            if (liveTagNameList != null && liveTagNameList.size() > 1 &&
                    (tabType == LiveHomeConstants.LIVE_HOME_TAB2 || tabType ==  LiveHomeConstants.LIVE_HOME_TAB4)) {
                List<String> tmp = new ArrayList<String>();
                tmp.add(liveTagNameList.get(0));
                liveTagNameList = tmp;
            }
            if(liver == null) {
                log.warn("查找不到用户信息，liver == null, anchorId= " + String.valueOf(anchorId));
                continue;
            }
            LiveHomeRecordEntity entity = toLiveRecordResponseModel(liveMap, liver, signTypeMap, liveTagNameList, isLive, request);
            resultList.add(entity);
        }
        resultMap.put("liveList", resultList);
        resultMap.put("count", count);  //直播总数
        return resultMap;
    }

    // 回放列表
    private BaseResponse playBackList(LiveHomePlaybackV2Request request) {
        try {
            int tabType = request.getTabType();
//            3 缤纷娱乐栏目 4美食撩味栏目
            if (!(tabType == LiveHomeConstants.LIVE_HOME_TAB3 ||
                    tabType == LiveHomeConstants.LIVE_HOME_TAB4)) {
                return new BaseResponse(ResponseCode.FAILURE, "获取回放列表失败,类型不对");
            }
            // 用户信息
            Integer uid = getUid(request.getSessiontoken());
            Map<Object, Object> liverMap = liverInfo(uid);
            String liveDefaultFilterPhone = "abc";
            try {
                liveDefaultFilterPhone = propertiesUtil.getValue("liveDefaultFilterPhone", "conf_common.properties");
            } catch (Exception e) {
                log.warn("解析配置失败：liveDefaultFilterPhone");
            }

            String phone = liveDefaultFilterPhone;
            if (liverMap != null) {
                phone = liverMap.get("phone") == null ? liveDefaultFilterPhone : liverMap.get("phone").toString();
            }

            List<Integer> anchorIdList = new ArrayList<Integer>();
            List<Map<Object, Object>> playBackList = null;

            //回放记录
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("page", request.getPage());
            param.put("pageSize", request.getPageSize());
            param.put("longitude", request.getLongitude());
            param.put("latitude", request.getLatitude());
            param.put("phone", phone);


            // 8月30号，直播首页改版
            boolean is830Version = is830Version(request);
//            类型：0直播 1回放(关联t_live_record) 2精彩视频（关联t_live_anchor_vedio） 3预告 4节目
            // 副标题
            Map<String, Object> subTitleMap = subTitleMap(is830Version, 1);

            if (is830Version) {
                if (request.getTabType() == LiveHomeConstants.LIVE_HOME_TAB4) {
                    playBackList = liveHomeV2Dao.getStarsVideo(param);
                } else {
                    playBackList = null;
                }

            } else {
                playBackList = playBackListByTabType(param, tabType);
            }
            if (playBackList != null) {
                for (Map<Object, Object> playBack : playBackList) {
                    String anchorId = playBack.get("anchor_id").toString(); //主播Id
                    anchorIdList.add(Integer.parseInt(anchorId));
                }
            }

            // 主播列表
            List<Map<Object, Object>> anchorList = null;
            Map<String, Map<Object, Object>> anchorMap = new HashMap<String, Map<Object, Object>>();
            if (anchorIdList.size() > 0) {
                anchorList = liveUserDao.queryLiverInfoByIdList(anchorIdList);
                if (anchorList != null) {
                    for (Map<Object, Object> anchor : anchorList) {
                        String anchorUid = anchor.get("id").toString();
                        anchorMap.put(anchorUid, anchor);
                    }
                }
            }

            // 返回数据
            List<LiveHomePlayBackEntity> resultList = new ArrayList<LiveHomePlayBackEntity>();
            if (playBackList != null) {
                for (int i = 0; i < playBackList.size(); i++) {
                    Map<Object, Object> playBack = playBackList.get(i);
                    String anchorId = playBack.get("anchor_id").toString();
                    Map<Object, Object> anchor = anchorMap.get(anchorId);
                    if (anchor == null) {
                        log.warn("查找不到用户信息： id=" + String.valueOf(anchorId));
                        continue;
                    }
                    LiveHomePlayBackEntity model = toPlayBackResponseModel(playBack, anchor, uid);
                    resultList.add(model);
                }
            }

            int appVersion = VersionUtil.getVersionCode(request);
            // 361之后，栏目3回放也是网格
            boolean tabTypeCondition = tabType == LiveHomeConstants.LIVE_HOME_TAB4 || (appVersion > 361 && tabType == LiveHomeConstants.LIVE_HOME_TAB3);
            // 添加默认图片
            if ( tabTypeCondition && resultList.size() > 0 && resultList.size() % 2 != 0) {
                String defaultLiveImg = "";
                String defaultLiveDesc = "";
                try{
                    //从配置文件中获取当场直播区间
                    defaultLiveImg = propertiesUtil.getValue("defaultLiveImg", "conf_common.properties");
                    defaultLiveDesc = propertiesUtil.getValue("defaultLiveDesc", "conf_common.properties");
                }catch(Exception e){
                    e.printStackTrace();
                    log.info("直播回放，解析配置失败， defaultLiveImg， conf_common.properties");
                }
                LiveHomePlayBackEntity entity = new LiveHomePlayBackEntity();
                entity.setIsNormalLive(0);
                entity.setDefaultDesc(defaultLiveDesc);
                entity.setDefaultImg(defaultLiveImg);
                resultList.add(entity);
            }

            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取回放列表成功");
            Map<Object, Object> resultMap = new HashMap<Object, Object>();
//            log.warn("热门回放：" + resultList.toString());

            if ((resultList.size() == 0)) {
                subTitleMap.put("showStatus", 3);
            }
            resultMap.putAll(subTitleMap);  //副标题
            resultMap.put("liveList", resultList);
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            log.warn("获取回放列表失败:" + request.toString(), e);
            return new BaseResponse(ResponseCode.FAILURE, "获取回放列表失败");
        }
    }

    public boolean is830Version(BaseRequest request) {
        boolean isIOS = request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains("ios");
        boolean isAndroid = request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains("android");
        int appLiveHomeIOS830Version = 0;
        int appLiveHomeAndroid830Version = 0;
        try {
            appLiveHomeIOS830Version = Integer.parseInt(propertiesUtil.getValue("appLiveHomeIOS830Version", "conf_common.properties"));
            appLiveHomeAndroid830Version = Integer.parseInt(propertiesUtil.getValue("appLiveHomeAndroid830Version", "conf_common.properties"));
        } catch (Exception e) {
            log.warn("获取配置失败，appLiveHomeIOS830Version appLiveHomeAndroid830Version");
        }
        boolean is830Version = false;
        if (isIOS) {
            is830Version = VersionUtil.getVersionCode(request) >= appLiveHomeIOS830Version && isIOS;
        } else if (isAndroid) {
            is830Version = VersionUtil.getVersionCode(request) >= appLiveHomeAndroid830Version && isAndroid;
        }
        return is830Version;
    }

    // 副标题
    public Map<String, Object> subTitleMap(boolean isNeedSubTitle, int mtype) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("subTitle", "");
        map.put("subDesc", "");
        map.put("showStatus", 3);  //0 显示描述和标题 1 只显示标题 2 只显示描述 3 都不显示

        if (!isNeedSubTitle) {
            return map;
        }

        try {
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("mtype", mtype);
            Map<Object, Object> tab = liveHomeV2Dao.getLiveTab(param);
            // 是否显示 0：隐藏 1：显示
            int is_show = tab == null ? 0 : Integer.parseInt(tab.get("is_show").toString());
            String subTitle = tab == null ? "" : tab.get("sub_title").toString();
            String subDesc = tab == null ? "" : tab.get("sub_desc").toString();
            int showStatus = is_show != 0 ? 0 : 3;
            map.put("subTitle", subTitle);
            map.put("subDesc", subDesc);
            map.put("showStatus", showStatus);
        } catch (Exception e) {
            log.warn("获取标题栏目失败", e);
        }
        return map;
    }

    // 分割直播list
    private void splitLiveList(Map<Object, Object> result, List<LiveHomeRecordEntity> liveList, int sqlit) {
        List<LiveHomeRecordEntity> pList = new ArrayList<LiveHomeRecordEntity>();
        List<LiveHomeRecordEntity> lList = new ArrayList<LiveHomeRecordEntity>();

        for (int i = 0; i < liveList.size(); i++) {
            if (i < sqlit) {
                pList.add(liveList.get(i));
            } else {
                lList.add(liveList.get(i));
            }
        }
        result.put("preLiveList", pList);
        result.put("lastLiveList", lList);
    }


    private List<Map<Object, Object>> playBackListByTabType(Map<Object, Object> param, int tabType) {
        if (tabType == LiveHomeConstants.LIVE_HOME_TAB3) {  //缤纷娱乐栏目
//            模块类型 1-新人推荐, 2-缤纷娱乐, 3-美味撩味
            param.put("moduleType", 2);
            return liveHomeV2Dao.getPlaybackList(param);
        } else if (tabType == LiveHomeConstants.LIVE_HOME_TAB4) {  //美食撩味栏目
            param.put("moduleType", 3);
            return liveHomeV2Dao.getPlaybackList(param);
        } else {
            throw new IllegalStateException("直播首页查询，类型不对");
        }
    }

    // 统计直播数量
    private Integer getLiveCountByTab(int tabType, String phone, boolean isFilterKSL) {
        Map<Object, Object> param = new HashMap<Object, Object>();
        param.put("phone", phone);
        if (isFilterKSL) {
            param.put("isFilterKSL", isFilterKSL);
        }

        switch (tabType) {
            case LiveHomeConstants.LIVE_HOME_TAB1:
                param.put("signType", 1);
//                return liveHomeV2Dao.sumSignLiveRecord(param);
                return liveHomeV2Dao.sumLiveRecord(param);
            case LiveHomeConstants.LIVE_HOME_TAB2:
                param.put("signType", 0);
//                return liveHomeV2Dao.sumNoneSignLiveRecord(param);
                return 0;
            case LiveHomeConstants.LIVE_HOME_TAB3:
                param.put("isActivity", 0); //isActivity不为null 为活动，其他为非活动
//                return liveHomeV2Dao.sumLiveRecordByActivity(param);
                return 0;
            case LiveHomeConstants.LIVE_HOME_TAB4:
//                return liveHomeV2Dao.sumLiveRecordByActivity(param);
                return 0;
            default:
                log.warn("根据类型获取直播数量，类型不对：" + tabType);
                break;
        }
        return 0;
    }

    private Integer getDefaultCityId() {
        Integer defaultCityId = 1964;  //默认城市Id为广州
        try {
            String tmpCityId = propertiesUtil.getValue("defaultCityId", "conf_common.properties");
            defaultCityId = Integer.parseInt(tmpCityId);
        } catch (Exception e) {
            log.warn("解析配置失败，defaultCityId");
        }
        return defaultCityId;
    }

    private List<Map<Object, Object>> liveRecordListByTabType(Map<Object, Object> param, int tabType, Integer tagId, Integer cityId,
                                                              boolean is830Version, boolean isFilterKSL) {

        // 附近和全国tagId，需要特殊处理
        if (tagId != null) {
            boolean isAll = tagId == LiveHomeConstants.Live_Home_Tag_All;
            boolean isCity = tagId == LiveHomeConstants.Live_Home_Tag_City;
            boolean isNation = tagId == LiveHomeConstants.Live_Home_Tag_National;

            if (isAll || isNation) {  //查询全部
                tagId = null;
                log.info("直播列表：isAll=" + String.valueOf(isAll) + " isNation=" + String.valueOf(isNation));
            }
            if (isCity) {  //查询城市
                tagId = null;
                cityId = cityId == null ? getDefaultCityId() : cityId;
                List<Integer> anchorList = new ArrayList<Integer>();
                List<Map<Object, Object>> anchorInfoList = liveUserDao.selectAnchorByCityId(cityId);
                for (Map<Object, Object> map : anchorInfoList) {
                    Integer id  = Integer.parseInt(map.get("id").toString());
                    anchorList.add(id);
                }
                log.info("直播列表：cityId = " + cityId + " 过滤后，主播Id为：" + anchorList.toString());
                if (anchorList.size() > 0) {
                    param.put("anchorList", anchorList);
                }
            }
        }
        if (isFilterKSL) {
            param.put("isFilterKSL", isFilterKSL);
        }

        if (tabType == LiveHomeConstants.LIVE_HOME_TAB1) {  //金牌推荐栏目
            param.put("signType", 1);  //1 为签约，0为非签约
            if (is830Version) {
                param.put("pageSize", 30);
                return liveHomeV2Dao.getLiveRecordList(param);  //所有直播
            } else {
                return liveHomeV2Dao.getLiveRecordListBySign(param);
            }
        } else if (tabType == LiveHomeConstants.LIVE_HOME_TAB2) {  //新人推荐栏目
            param.put("signType", 0);
            param.put("tagId", tagId);
            return liveHomeV2Dao.getLiveRecordListByNotSign(param);
        } else if (tabType == LiveHomeConstants.LIVE_HOME_TAB3) {  //缤纷娱乐栏目
            param.put("isActivity", 0); //isActivity 非null 为活动，其他为非活动
            param.put("pageSize", 999);  //客户端没做分页，所有返回所有
            return liveHomeV2Dao.getLiveRecordListByActivity(param);
        } else if (tabType == LiveHomeConstants.LIVE_HOME_TAB4) {  //美食撩味栏目
            param.put("tagId", tagId);
            param.put("pageSize", 999);  //客户端没做分页，所有返回所有
            return liveHomeV2Dao.getLiveRecordListByActivity(param);
        } else {
            throw new IllegalStateException("直播首页查询，类型不对");
        }
    }

    private List<Map<Object, Object>> wonderfulAdvListByTabType(Map<Object, Object> param, int tabType) {
        if (tabType == LiveHomeConstants.LIVE_HOME_TAB1) {  //金牌推荐栏目
            param.put("signType", 1);  //1 为签约，0为非签约
//            return liveHomeV2Dao.getPreListBySign(param);
            param.put("page", 1);
            param.put("pageSize", 4);
            return liveHomeV2Dao.getPreList(param);
        } else if (tabType == LiveHomeConstants.LIVE_HOME_TAB2) {  //新人推荐栏目
            param.put("signType", 0);
            return liveHomeV2Dao.getPreListByNoneSign(param);
        }  else {
            throw new IllegalStateException("直播首页查询，类型不对");
        }
    }

    @Override
    public Integer getUid(String sessionToken) {
        String uid = null;
        //有传token则默认为登录状态
        if (sessionToken != null && StringUtils.isNotEmpty(sessionToken)) {
            //验证token
            uid = sessionTokenService.getStringForValue(sessionToken) + "";
            if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
                uid = null;
            }
        }
        if (uid != null) {
            return Integer.parseInt(uid);
        }
        return null;
    }

    /**
     * 获取直播首页tab栏目名称
     * @param request
     * @return
     */
    @Override
    public BaseResponse getTabNameList(BaseRequest request) {
        try {
            Map<Integer, String> map = new HashMap<Integer, String>();
            String liveHomeTabName = propertiesUtil.getValue("liveHomeTabName", "conf_common.properties");
            JSONArray jsonArray = JSON.parseArray(liveHomeTabName);
            Object[] tabNameArray = jsonArray.toArray();
            List<Object> tabNameList = Arrays.asList(tabNameArray);
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播首页tab栏目名称成功");
            Map<Object, Object> resultMap = new HashMap<Object, Object>();
            resultMap.put("tabNameList", tabNameList);
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            log.warn("获取直播首页tab栏目名称失败");
            e.printStackTrace();
            return new BaseResponse(ResponseCode.FAILURE, "获取直播首页tab栏目名称失败");
        }
    }

    /**
     * 获取直播首页tab栏目名称
     * @param request
     * @return
     */
    @Override
    public BaseResponse getTabNameListV2(BaseRequest request) {
        try {
            boolean is830Version = is830Version(request);
            List<Object> tabNameList = new ArrayList<Object>();
            if (is830Version) {
                Map<Object, Object> param = new HashMap<Object, Object>();
                List<Map<Object, Object>> tabList =  liveHomeV2Dao.getLiveTabList(param);
                if (tabList != null && tabList.size() == 4) {
                    for (Map<Object, Object> tab : tabList) {
                        String name = tab.get("title").toString();
                        Integer id = Integer.parseInt(tab.get("id").toString());
                        Integer position = Integer.parseInt(tab.get("position").toString());
                        Integer rtype = Integer.parseInt(tab.get("rtype").toString());
                        tab.clear();
                        tab.put("name", name);
                        tab.put("id", rtype);
                        tab.put("position", position);
                    }
                    Collections.sort(tabList, new Comparator<Map<Object, Object>>() {
                        @Override
                        public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
                            Integer po1 = Integer.parseInt(o1.get("position").toString());
                            Integer po2 = Integer.parseInt(o2.get("position").toString());
                            return po1 - po2;
                        }
                    });
                    tabNameList.addAll(tabList);
                }
            } else {
                Map<Integer, String> map = new HashMap<Integer, String>();
                String liveHomeTabName = propertiesUtil.getValue("liveHomeTabNameV2", "conf_common.properties");
                JSONArray jsonArray = JSON.parseArray(liveHomeTabName);
                Object[] tabNameArray = jsonArray.toArray();
                tabNameList = Arrays.asList(tabNameArray);
            }
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播首页tab栏目v2名称成功");
            Map<Object, Object> resultMap = new HashMap<Object, Object>();
            resultMap.put("tabNameList", tabNameList);
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            log.warn("获取直播首页tab栏目v2名称失败");
            e.printStackTrace();
            return new BaseResponse(ResponseCode.FAILURE, "获取直播首页tab栏目v2名称失败");
        }
    }

    private LiveHomeRecordEntity toLiveRecordResponseModel(Map<Object, Object> liveInfo, Map<Object, Object> liver,
              Map<Integer, Integer> signTypeMap, List<String> liveTagNameList, boolean isLive, BaseRequest request) {
        Integer uid = liver.get("uid") == null ? 0 : Integer.parseInt(liver.get("uid").toString());
        Integer signType = signTypeMap.get(uid);
        // 版本控制，前端写错了将类型4的改为4了
        if (VersionUtil.getVersionCode(request) <= 361) {
            if (signType == 4) {
                signType = 3;
            }
        }

        String nname = StrUtils.standardName(liver.get("nname"), liver.get("phone"));
        String cover = liveInfo.get("zhibo_cover") == null ? "" : liveInfo.get("zhibo_cover").toString();
        cover = safeToUrl(cover);
        String liveTime = liveTime(liveInfo);
        String title = zhiboTitle(liveInfo);
        Integer roomLock = roomLock(liveInfo);
        String sellername = sellerName(liveInfo);
        String vedio_url = liveInfo.get("vedio_url") == null ? "" : liveInfo.get("vedio_url").toString();
        String start_date = "";
        if (isLive) {  //直播
            try {
                start_date = liveInfo.get("start_date") == null ? "" : liveInfo.get("start_date").toString();
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("直播记录，解析时间失败");
            }
        } else {
            start_date = liveInfo.get("plan_start_date") == null ? "" : liveInfo.get("plan_start_date").toString();
        }
        int index = start_date.indexOf(".");
        if (index > 0) {
            start_date = start_date.substring(0, index);
        }

        String distanceStr = "";
        try {
            //距离
            if (liveInfo.get("distance") != null &&StringUtils.isNotEmpty(liveInfo.get("distance").toString()) && Double.parseDouble(liveInfo.get("distance").toString()) > 0) {
                if (Double.parseDouble(liveInfo.get("distance").toString()) < 1000) {
                    distanceStr = liveInfo.get("distance").toString() + "m";
                }else {
                    distanceStr = ArithUtil.div(Double.parseDouble(liveInfo.get("distance").toString()), 1000, 2) + "km";
                }
            }
        } catch (Exception e) {
            log.info("获取距离失败", e);
        }
        String tsl_push_check_key = "myqcloud";
        try {
            tsl_push_check_key = propertiesUtil.getValue("tsl_push_check_key", "conf_common.properties");
        } catch (Exception e) {
            log.warn("解析错误配置 tsl_push_check_key");
        }
        int livePlatform = 1;  // 1 为腾讯 2 为金山
        String liveUrl = "";
        // 直播平台
        if (isLive) {
            try {
                boolean isTLSLive = vedio_url == null || vedio_url.toLowerCase().contains(tsl_push_check_key);
                KSLiveEntity entity = ksCloudService.getAndCreateLiveInfo(liver, 1);  //拉流
                livePlatform = entity == null || isTLSLive ? 1 : entity.getPlatform();
                liveUrl = entity == null ? "" : entity.getUrl();
            } catch (Exception e) {
                log.warn("获取直播平台失败，使用默认腾讯直播", e);
            }
        }

        LiveHomeRecordEntity entity = new LiveHomeRecordEntity();
        entity.setUid(uid);
        entity.setSex(liver.get("sex") == null ? 0 : Integer.parseInt(liver.get("sex").toString()));
        entity.setSignType(signType);
        entity.setAvatar(liver.get("avatar") == null ? "" : fileUrl + liver.get("avatar").toString());
        entity.setGroupId(liver.get("group_id") == null ? "" : liver.get("group_id").toString());
        entity.setNname(nname);
        entity.setAnchorId(liver.get("id") == null ? 0 : Integer.parseInt(liver.get("id").toString()));
        entity.setZbPhone(liver.get("phone") == null ? "" : liver.get("phone").toString());
        entity.setLiveRecordId(liveInfo.get("id") == null ? 0 : Integer.parseInt(liveInfo.get("id").toString()));
        entity.setVedioUrl(vedio_url);
        entity.setLiveTime(liveTime);
        entity.setTitle(title);
        entity.setCover(cover);
        entity.setZhiboType(liveInfo.get("zhibo_type") == null ? -1 : Integer.parseInt(liveInfo.get("zhibo_type").toString()));
        entity.setSellerId(liveInfo.get("sellerid") == null ? 0 : Integer.parseInt(liveInfo.get("sellerid").toString()));
        entity.setExistRoomLock(roomLock);
        entity.setSellername(sellername);
        entity.setAnchorRoomNo(liveInfo.get("anchor_room_no") == null ? "" : liveInfo.get("anchor_room_no").toString());
        entity.setViewCount(liveInfo.get("view_count") == null ? 0 : Integer.parseInt(liveInfo.get("view_count").toString()));
        entity.setLiveStartType(Integer.parseInt(liveInfo.get("live_start_type").toString()));
        entity.setLiveTagNameList(liveTagNameList == null ? new ArrayList<String>() : liveTagNameList);
        entity.setTag("");
        entity.setLable(0);
        entity.setIsSell(0);
        entity.setStart_date(start_date);
        entity.setIsNormalLive(1);  //默认正常直播数据
        entity.setDistance(distanceStr == null ? "" : distanceStr);  //距离
        entity.setZhiboAddress(liveInfo.get("zhibo_address") == null ? "" : liveInfo.get("zhibo_address").toString());
        entity.setLivePlatform(livePlatform);
        entity.setLiveRtmpUrl(liveUrl);
        return entity;
    }

    private String safeToUrl(String picUrl) {
        if (picUrl == null || picUrl.trim().equals("")) {
            return "";
        }
        if (!picUrl.contains(fileUrl)) {
            picUrl = fileUrl + picUrl;
        }
        return picUrl;
    }

    private LiveHomePlayBackEntity toPlayBackResponseModel(Map<Object, Object> playBack, Map<Object, Object> liver, Integer uid) {
        Integer sellerid = playBack.get("sellerid") == null ? 0 : Integer.parseInt(playBack.get("sellerid").toString());
        String distance = playBack.get("distance") == null ? null : playBack.get("distance") .toString();
        int label = lable(sellerid, uid, distance);
        Integer signType = anchorSignTypeService.getSignType(liver);
        String cover = playBack.get("zhibo_cover")==null ? "" : playBack.get("zhibo_cover").toString();
        cover = safeToUrl(cover);
        String nname = StrUtils.standardName(liver.get("nname"), liver.get("phone"));
        String start_date = "";
        String end_date = "";
        if (playBack.get("start_date") != null) {
            try {
                Date date = DateUtil.parse(playBack.get("start_date").toString());
                start_date = DateUtil.format(date, DateUtil.defaultSimpleFormater);  //开始时间
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("回放列表，时间转换出错, start_date");
            }
        }
        if (playBack.get("end_date") != null) {
            try {
                Date date = DateUtil.parse(playBack.get("end_date").toString());
                end_date =  DateUtil.format(date, DateUtil.defaultSimpleFormater);  //结束时间
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("回放列表，时间转换出错, end_date");
            }
        }
        LiveHomePlayBackEntity entity = new LiveHomePlayBackEntity();
        entity.setAvatar(liver.get("avatar") == null ? "" : fileUrl + liver.get("avatar").toString());
        entity.setNname(nname);
        entity.setSignType(signType);
        entity.setSex(liver.get("sex") == null ? 0 : Integer.parseInt(liver.get("sex").toString()));
        entity.setFansCoun(0);
        entity.setRoomNo(playBack.get("anchor_room_no") == null ? 0 : Integer.parseInt(playBack.get("anchor_room_no").toString()));
        entity.setZhibo_cover(cover);
        entity.setAnchorId(playBack.get("anchor_id") == null ? 0 : Integer.parseInt(playBack.get("anchor_id").toString()));
        entity.setId(playBack.get("id") == null ? 0 : Integer.parseInt(playBack.get("id").toString()));
        entity.setZhibo_title(playBack.get("zhibo_title") == null ? "" : playBack.get("zhibo_title").toString());
        entity.setView_count(playBack.get("view_count") == null ? 0 : Integer.parseInt(playBack.get("view_count").toString()));
        entity.setVedio_url(playBack.get("vedio_url") == null ? "" : playBack.get("vedio_url").toString());
        entity.setSellerid(playBack.get("sellerid") == null ? 0 : Integer.parseInt(playBack.get("sellerid").toString()));
        entity.setSellername(playBack.get("sellername") == null ? "" : playBack.get("sellername").toString());
        entity.setStart_date("");
        entity.setEnd_date("");
        entity.setLable(label);
        entity.setIsNormalLive(1);
        entity.setStart_date(start_date);
        entity.setEnd_date(end_date);
        return entity;
    }

    // 查询商家标签
    private int lable(Integer sellerid, Integer uid, String distance){
        int lable = 0;
        try {
            if (uid != null) {
                //是否消费过
                if( sellerService.billCountBySelleridAndUid(sellerid, uid)>0) {
                    lable=1;
                }else {	 //是否收藏
                    if( ursService.isCollectSeller(uid, sellerid) > 0 ) {
                        lable=2;
                    }else {	//是否浏览
                        if( ursService.queryBrowsedCountByUidAndSellerid(uid, sellerid) > 0 )  {
                            lable=3;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lable == 0) {
            //距离近
            if (distance != null && distance.trim().equals("")) {
                distance = "0";
            }
            if(distance != null && Double.parseDouble(distance) <= 500) {
                lable = 4;
            }
        }
        return lable;
    }

    private Map<Object, Object> liverInfo(Integer uid) {
        if (uid != null) {
            try {
                Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(uid);
                return liverMap;
            } catch (Exception e) {
                log.warn("直播记录 获取用户信息失败", e);
            }
        }
        return null;
    }

    private String liveTime(Map<Object, Object> liveMap) {

        //计算观看时长
        Long duration = 0L;
        //计算本次观看时长
        if (liveMap.get("start_date")!=null) {
            duration = new Date().getTime() - DateUtil.parse(liveMap.get("start_date").toString()).getTime();
        }else {
            duration = 0L;
        }
        int liveTime = (int) Math.floor(ArithUtil.div(Double.parseDouble(duration + ""), 60000));
        return String.valueOf(liveTime);
    }

    private Integer roomLock(Map<Object, Object> liveMap) {
        if (liveMap.get("anchor_room_password") != null && StringUtils.isNotEmpty(liveMap.get("anchor_room_password").toString())) {
            return 1;
        }else {
            return 0;
        }
    }

    private String sellerName(Map<Object, Object> liveMap) {
        String sellername = liveMap.get("sellername") == null ? "" : liveMap.get("sellername").toString(); //店铺名称
        //如果有商家别名,使用商家别名
        if (liveMap.get("seller_alias") != null && StringUtils.isNotEmpty(liveMap.get("seller_alias").toString())) {
            sellername = liveMap.get("seller_alias").toString();
        }
        return sellername;
    }

    private String zhiboTitle(Map<Object, Object> liveMap) {
        if (liveMap.get("zhibo_title") != null && StringUtils.isNotEmpty(liveMap.get("zhibo_title").toString())) {
            //预告标题/直播标题
            return liveMap.get("zhibo_title").toString();
        }else {
            //如果没有预告标题/直播标题，则取店铺别名，或者是店铺
            if (liveMap.get("seller_alias") != null && StringUtils.isNotEmpty(liveMap.get("seller_alias").toString())) {
                return liveMap.get("seller_alias").toString();
            }else {
                return liveMap.get("sellername") == null ? "" : liveMap.get("sellername").toString();
            }
        }
    }

}
