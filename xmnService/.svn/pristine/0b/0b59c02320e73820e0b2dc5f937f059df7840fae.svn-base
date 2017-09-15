package com.xmniao.xmn.core.xmer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.ZoneListRequest;
import com.xmniao.xmn.core.common.request.xmer.SaasTypeRequest;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.UrsEarningsRelationInfo;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.xmer.dao.AreaDao;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import com.xmniao.xmn.core.xmer.entity.Area;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * Created by Administrator on 2017/5/3.
 */
@Service
public class XmerService {

    private Logger log = Logger.getLogger(XmerService.class);

    @Autowired
    private LiveUserDao liveUserDao;

    @Autowired
    private SessionTokenService sessionTokenService;

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private SaasOrderDao saasOrderDao;

    /**
     * 根据UID获取用户身份列表
     * @param uid
     * @return
     */
    public List<Integer> identityList(Integer uid) {

        List<Integer> identity = new ArrayList<Integer>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("uid", uid);
        List<UrsEarningsRelationInfo> infos = liveUserDao.queryBursEarningsRelationList(map);
        HashSet<Integer> uids = new HashSet<Integer>();
        //     1 寻觅客 2中脉 3 V客 4 主播v客
        for (UrsEarningsRelationInfo info : infos) {
            // 5.常规寻蜜客 6.主播寻蜜客(V客赠送) 7.V客寻密客 8.脉客寻密客
            int objectOriented = info.getObjectOriented();
            if (objectOriented == 5 ) {
                identity.add(1);
            } else if (objectOriented == 6) {
                identity.add(4);
            } else if (objectOriented == 7 ) {
                identity.add(3);
            } else if (objectOriented == 8) {
                identity.add(2);
            }
        }
        return identity;
    }

    /**
     * 获取用户身份Map
     * 1 寻觅客 2中脉 3 V客
     * @param uid
     * @return
     */
    public Map<Integer, Boolean> identityMap(Integer uid) {
        List<Integer> identityList = identityList(uid);
        Map<Integer, Boolean> identityMap = new HashMap<Integer, Boolean>();
        identityMap.put(1, false);
        identityMap.put(2, false);
        identityMap.put(3, false);
        identityMap.put(4, false);
//        1 寻觅客 2中脉 3 V客
        for (Integer identity : identityList) {
            identityMap.put(identity, true);
        }
        return identityMap;
    }

    /**
     * 查询身份
     * @param baseRequest
     * @return
     */
    public Object queryIdentity(BaseRequest baseRequest) {
        try {
            String sessionToken = baseRequest.getSessiontoken();
            String uid = sessionTokenService.getStringForValue(sessionToken) + "";
            if(uid.equals("")||uid.equals("null")){
                return new BaseResponse(ResponseCode.FAILURE, "查询身份失败,sessiontoken失效");
            }
            Map<Object, Object> map = new HashMap<Object, Object>();
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "成功");
            List<Integer> identity = identityList(Integer.parseInt(uid));
            map.put("identity", identity);
            response.setResponse(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("查询身份", e);
            return new BaseResponse(ResponseCode.FAILURE, "查询身份");
        }
    }

    // h5
    public String getUidBySUid(String sessionToken, String sUid) {
        String uid = null;
        if (sessionToken == null || sessionToken.equals("")) {
            if (sUid != null) {
                sUid = Base64.getFromBase64(sUid);
                if (sUid != null && sUid.length() > 4) {
                    uid = sUid.substring(2, sUid.length() - 2);
                }
            }
        } else {
            uid = sessionTokenService.getStringForValue(sessionToken) + "";
        }
        return uid;
    }

    /**
     * 查询saas类型
     * @param baseRequest
     * @return
     */
    public Object querySaasType(SaasTypeRequest baseRequest) {
        try {
            String sUid = getUidBySUid(baseRequest.getSessiontoken(), baseRequest.getsUid());
            if(sUid == null || sUid.equals("")||sUid.equals("null")){
                return new BaseResponse(ResponseCode.FAILURE, "查询saas类型失败,sessiontoken失效");
            }
            Integer uid = Integer.parseInt(sUid);
            Map<Object, Object> map = new HashMap<Object, Object>();
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "成功");

            // 查询身份
            List<Integer> identityList = identityList(uid);

            List<Integer> sortIdentityList = new ArrayList<Integer>();
            // 查询套餐
            List<Map<Object,Object>> saasInfoList = saasOrderDao.queryOrderNumsV2(uid);
            if (saasInfoList != null && saasInfoList.size() >= 0) {
                Map<Integer, Integer> saasTypeMap = new HashMap<Integer, Integer>();
                for (int i = 0; i < saasInfoList.size(); i++) {
                    Map<Object, Object> saasInfo = saasInfoList.get(i);
                    Integer saas_channel = Integer.parseInt(saasInfo.get("saas_channel").toString());
//                saas购买渠道 1常规购买 2 脉客购买 3 V客兑换  4 主播接受V客赠送
//                saasType    1 寻觅客 2中脉 3 V客 4 v客
                    Integer saasType = saas_channel;
                    saasTypeMap.put(saasType, saas_channel);
                }
                if (identityList != null && identityList.size() > 0) {
                    for (Integer identity : identityList) {
                        if (saasTypeMap.containsKey(identity)) {
                            sortIdentityList.add(identity);
                        }
                    }
                }
            }

            List<Map<Object, Object>> mapList = new ArrayList<Map<Object, Object>>();

            //  添加文本描述
            if (sortIdentityList.size() > 0) {
                Map<Integer, String> m1 = getSaasInfoDescMap();
                for (Integer number : sortIdentityList) {
                    Map<Object, Object> m = new HashMap<Object, Object>();
                    m.put("id", number);
                    String text = m1.get(number) == null ? "" : m1.get(number).toString();
                    m.put("text",  text);
                    mapList.add(m);
                }
            }
            map.put("identity", mapList);
            response.setResponse(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("查询saas类型失败", e);
            return new BaseResponse(ResponseCode.FAILURE, "查询saas类型失败");
        }

    }

    // saas类型描述map
    public Map<Integer, String> getSaasInfoDescMap() {
        Map<Integer, String> m1 = new HashMap<Integer, String>();
        try {
            String sassType = propertiesUtil.getValue("sassType", "conf_xmer.properties");
            JSONObject json = JSON.parseObject(sassType);
            for (Map.Entry<String, Object> entry : json.entrySet()) {
                m1.put(Integer.parseInt(entry.getKey()), entry.getValue().toString());
            }
        } catch (Exception e) {
            log.warn("解析配置失败，sassType  conf_xmer.properties");
        }
        return m1;
    }


    /**
     * 通过区域查询商圈失败
     * @return
     */
    public Object queryZonesByArea(ZoneListRequest zoneListRequest) {
        try {
            Map<Object, Object> param = new HashMap<Object, Object>();
            param.put("id", zoneListRequest.getpId());
            List<Map<Object,Object>> areas = businessDao.findAllByAreaId(param);
            List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
            if (areas != null) {
                for (Map<Object,Object> area : areas) {
                    String zoneId = area.get("zoneId").toString();
                    String zoneName = area.get("zoneName").toString();
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("zoneId", zoneId);
                    map.put("zoneName", zoneName);
                    resultList.add(map);
                }
            }
            MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("zoneList", resultList);
            response.setResponse(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("通过区域查询商圈失败");
            return new BaseResponse(ResponseCode.FAILURE,"查询异常");
        }
    }


}
