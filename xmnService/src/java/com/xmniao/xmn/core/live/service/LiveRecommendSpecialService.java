package com.xmniao.xmn.core.live.service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.catehome.dao.SpecilTopicPicDao;
import com.xmniao.xmn.core.catehome.entity.SpecilTopicPic;
import com.xmniao.xmn.core.catehome.response.HomeImageResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveRecommendSpecialRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 直播的好看专题推荐
 * Created by Administrator on 2017/3/29.
 */
@Service
public class LiveRecommendSpecialService {

    private final Logger log = Logger.getLogger(LiveRecommendSpecialService.class);

    //专题图片dao
    @Autowired
    private SpecilTopicPicDao specilTopicPicDao;

    //注入配置service
    @Autowired
    private PropertiesUtil propertiesUtil;

    //服务器地址
    @Autowired
    private String fileUrl;

    /**
     * 好看专题推荐
     * @param liveRecommendSpecialRequest
     * @return
     */
    public Object findRecommendActivityPic(LiveRecommendSpecialRequest liveRecommendSpecialRequest) {
        try {
            String url = "";
            try{
                //从配置文件中获取跳转地址
                url = propertiesUtil.getValue("specialUrl", "conf_common.properties");
            }catch(Exception e){
                e.printStackTrace();
                log.info("获取配置文件专题Url地址异常");
            }

            Integer cityId = liveRecommendSpecialRequest.getCityId();
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("cityId", null); //产品说，2017.4.20版本都是全国的
            param.put("page", 1);
            param.put("pageSize", 9);
            List<Map<Object, Object>> specilTopicPics = specilTopicPicDao.findRecommendActivityPicByCondition(param);
            List<HomeImageResponse> resultList = new ArrayList<>();
            if(specilTopicPics != null && specilTopicPics.size() > 0){
                for(Map<Object, Object> map : specilTopicPics){
                    HomeImageResponse image = new HomeImageResponse();
                    image.setId(Integer.parseInt(map.get("fid").toString()));//专题活动
                    image.setPicUrl(map.get("pic_url") == null ? "" : fileUrl + map.get("pic_url"));//图片地址
                    image.setSort(map.get("home_sort") == null ? 0 : Integer.parseInt(map.get("home_sort").toString()));//图片排序
                    image.setContent(url);
                    resultList.add(image);
                }
            }


            Map<Object, Object> resultMap = new HashedMap();
            resultMap.put("specials", resultList);
            //响应
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取好看推荐成功");
            response.setResponse(resultMap);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取好看推荐失败,错误信息如下:" + e.getMessage());
            return new BaseResponse(ResponseCode.FAILURE, "获取好看推荐失败");
        }
    }
}
