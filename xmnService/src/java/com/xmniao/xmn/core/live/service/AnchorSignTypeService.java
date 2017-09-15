package com.xmniao.xmn.core.live.service;

import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.vstar.dao.VStarPlayerInfoDao;
import org.apache.log4j.Logger;
import org.omg.IOP.ExceptionDetailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 主播身份标识
 * Created by Administrator on 2017/6/2.
 */
@Service
public class AnchorSignTypeService {

    private final Logger log = Logger.getLogger(AnchorSignTypeService.class);

    @Autowired
    private LiveGiftsInfoService liveGiftsInfoService;
    @Autowired
    private VStarPlayerInfoDao vStarPlayerInfoDao;


    /**
     * 查询主播身份标识
     * 优先显示参赛者标志，次之签约主播标志，最后美食体验官标志。
     * @param liver
     * @return
     */
    public Integer getSignType(Map<Object, Object> liver) {
        // 0 非签约主播 1 签约主播 2 内部测试账号 3 美食体验官 4 大赛主播
        if (liver == null) {
            log.warn("liver == null");
            return 0;
        }
        try {
            Integer uid = liver.get("uid") == null ? 0 : Integer.parseInt(liver.get("uid").toString());
            Object signObject = liver.get("sign_type") == null ? liver.get("signType") : liver.get("sign_type");
            Integer signType = signObject == null ? 0 : Integer.parseInt(signObject.toString());
            //
            boolean isVStar = isVStar(uid);
            if (isVStar) {  //大赛主播
                return 4;
            }
            if (signType == 1) {  // 签约主播
                return 1;
            }
            boolean isExperienceOfficer = isExperienceOfficer(uid);
            if (isExperienceOfficer) {  // 美食体验官
                return 3;
            }
            return 0;
        } catch (Exception e) {
            log.info("获取主播身份标识错误：" + liver.toString());
            return 0;
        }
    }

    /**
     * 批量查询主播身份标识
     * 优先显示参赛者标志，次之签约主播标志，最后美食体验官标志。
     * 0 非签约主播 1 签约主播 2 内部测试账号 3 美食体验官 4 大赛主播
     * @param liverList
     * @return
     */
    public Map<Integer, Integer> signTypeMap(List<Map<Object, Object>> liverList) {
        Map<Integer, Integer> signTypeMap = new HashMap<Integer, Integer>();
        if (liverList == null || liverList.size() == 0) {
            return signTypeMap;
        }
        List<Integer> uidList = new ArrayList<Integer>();

        for (Map<Object, Object> liver : liverList) {
           try {
               Integer uid = liver.get("uid") == null ? 0 : Integer.parseInt(liver.get("uid").toString());
               Object signObject = liver.get("sign_type") == null ? liver.get("signType") : liver.get("sign_type");
               Integer signType = signObject == null ? 0 : Integer.parseInt(signObject.toString());
               uidList.add(uid);
               signType = signType == 1 ? 1 : 0;
               signTypeMap.put(uid, signType);
           } catch (Exception e) {
               log.warn("获取主播身份标识错误：" + liver.toString());
           }
        }
        // 大赛主播列表
        List<Map<Object, Object>> infoList = null;
        if (uidList.size() > 0) {
           infoList = vStarPlayerInfoDao.selectVStarPlayerInfoByUidList(uidList);
        }
        if (infoList != null) {
            for (Map<Object, Object> m : infoList) {
                if (m.get("uid") == null) {
                    continue;
                }
                Integer tUid = Integer.parseInt(m.get("uid").toString());
                signTypeMap.put(tUid, 4);  //大赛主播
            }
        }
        List<Integer> eUidList = new ArrayList<Integer>();  //需要查询美食体验官的用户
        Set<Integer> idSet = signTypeMap.keySet();
        for (Integer id : idSet) {
            Integer signType = signTypeMap.get(id);
            if (signType == 4) {  //大赛主播
            } else if (signType == 1) {  //签约主播
            } else {
                eUidList.add(id);
            }
        }

        // 美食体验官
        if (eUidList != null && eUidList.size() > 0) {
            try {
                List<Map<String,String>> cardList =	liveGiftsInfoService.getExperiencecardByUids(eUidList);
                if (cardList != null) {
                    for (Map<String, String> m : cardList) {
                        boolean isE = isExperienceOfficer(m);
                        Integer uid = Integer.parseInt(m.get("uid"));
                        if (isE) {
                            signTypeMap.put(uid, 3);
                        }
                    }
                }
            } catch (Exception e) {
                log.warn("批量获取美食体验卡失败");
            }
        }
        return signTypeMap;
    }

    // 是否是美食体验官
    private boolean isExperienceOfficer(Integer uid) throws Exception {
        // 查询美食体验官
        Map<String,String> param = new HashMap<String, String>();
        param.put("uid", String.valueOf(uid));
        ResponseData responseData = liveGiftsInfoService.queryExperienceCard(param);
        if (responseData.getState() == 0) {
            return isExperienceOfficer(responseData.getResultMap());
        } else {
            log.warn(responseData.toString());
            return false;
        }
    }

    // 是否是美食体验官
    private boolean isExperienceOfficer(Map<String, String> card) {
        try {
            Integer stock = Integer.parseInt(card.get("stock"));
            Integer status = Integer.parseInt(card.get("status"));
            String dueDate = card.get("dueDate");
            Date date = DateUtil.parse(dueDate, DateUtil.defaultSimpleFormater);
            Date now = new Date();
            return date.getTime() > now.getTime() && stock > 0 && status == 0;
        } catch (Exception e) {
            log.warn("解析美食体验卡数据错误");
        }
        return false;
    }

    /**
     * 是否是大赛主播
     * @return
     */
    private boolean isVStar(Integer uid) {
        Map<Object, Object> info = vStarPlayerInfoDao.selectVStarPlayerInfoByUid(uid);
        return info != null;
    }




}
