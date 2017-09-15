package com.xmniao.xmn.core.kscloud.service;

import com.sun.jndi.toolkit.url.Uri;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.coupon.service.UserCouponService;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.request.KSCloudConfRequest;
import com.xmniao.xmn.core.kscloud.request.KSCloudHBRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.recruit.dao.UserDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.VersionUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.collections.RedisZSet;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/1.
 */
@Service
public class KSCloudService {

    private final Logger log = Logger.getLogger(KSCloudService.class);

//    private static String ak = "AKLTJzOEvP0QRNKwYKDPPdpfGQ";
//    private static String pak = "ONQMPWR35vZ8mSwnaJrbsaxW1RKMB7SeVcv+NdHCuCB4d8/ZNtUpnCj5yrprYeUshw==";
//    public static String ksc_push_header_url = "rtmp://niaoren888.uplive.ks-cdn.com/live/";  //拉流头部
//    public static String ksc_pull_header_url = "rtmp://niaoren888.rtmplive.ks-cdn.com/live/";  //推流头部
    public static String redis_ksc_live_header = "live_kscloud_anchor_";
    public static int timeoutHeartbeat = 30;

    @Autowired
    private LiveUserDao liveUserDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private AnchorLiveRecordDao anchorLiveRecordDao;
    @Autowired
    private SessionTokenService sessionTokenService;


    // 是否过滤金山云直播(旧版本过滤金山云直播)
    public boolean isFilterKSLive(BaseRequest request) {
        int is_open_ksc_filter = 1;
        int ksc_filter_android_version=369;
        int ksc_filter_ios_version=369;
        try {
            is_open_ksc_filter = Integer.parseInt(propertiesUtil.getValue("is_open_ksc_filter", "conf_common.properties"));
            ksc_filter_android_version = Integer.parseInt(propertiesUtil.getValue("ksc_filter_android_version", "conf_common.properties"));
            ksc_filter_ios_version = Integer.parseInt(propertiesUtil.getValue("ksc_filter_ios_version", "conf_common.properties"));
        } catch (Exception e) {
            log.warn("解析金山云配置失败，is_open_ksc_filter, ksc_filter_android_version, ksc_filter_ios_version", e);
        }
        int code = VersionUtil.getVersionCode(request);
        boolean isAndroid = request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains("android");
        boolean isIOS = request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains("ios");
        if (is_open_ksc_filter == 1 && isAndroid && code < ksc_filter_android_version) {
            return true;
        }
        if (is_open_ksc_filter == 1 && isIOS && code < ksc_filter_ios_version) {
            return true;
        }
        return false;
    }

    public boolean isKSL(Map<Object, Object> liveRecordInfo) {
        String vedio_url = liveRecordInfo.get("vedio_url") == null ? "" : liveRecordInfo.get("vedio_url").toString();
        return isKSL(vedio_url);
    }

    public boolean isKSL(String vedio_url) {
        String ksc_push_check_key = "";
        try {
            ksc_push_check_key = propertiesUtil.getValue("ksc_push_check_key", "conf_common.properties");
        } catch (Exception e) {
            log.warn("解析金山云配置失败，ksc_push_check_key", e);
        }
        if (vedio_url != null && vedio_url.contains(ksc_push_check_key)) {
            return true;
        }
        return false;
    }

    public boolean isKSL(LiveRecordInfo live) {
        String vedio_url = live.getVedio_url() == null ? "" : live.getVedio_url().toString(); // 非金山云直播
        return isKSL(vedio_url);
    }

    public Integer useWhichLivePlatForm(LiverInfo liverInfo) {
        if (liverInfo == null) {
            return 1;
        }
        int sign_type = liverInfo.getSign_type() == null ? 1 : liverInfo.getSign_type();
        String phone = liverInfo.getPhone() == null ? "" : liverInfo.getPhone().trim();
        return useWhichLivePlatFormKernel(sign_type, phone);
    }

    public Integer useWhichLivePlatForm(Map<Object, Object> userInfo) {
        if (userInfo == null) {
            return 1;
        }
        int sign_type = userInfo.get("sign_type") == null ? 1 : Integer.parseInt(userInfo.get("sign_type").toString());
        String phone = userInfo.get("phone") == null ? "" : userInfo.get("phone").toString().trim();
        return useWhichLivePlatFormKernel(sign_type, phone);
    }

    private Integer useWhichLivePlatFormKernel(int signType, String phone) {
        int isOpenKSCloudPlatform = 0;  //是否启用金山云
        int ksLiveStrategy = 0;  // 使用金山的策略： 1 非签约 2 签约 3 手机 4 非签约 + 手机 5 签约 + 手机 6 所有主播
        String ksLivePhoneList = "";
        try {
            isOpenKSCloudPlatform = Integer.parseInt(propertiesUtil.getValue("isOpenKSCloudPlatform", "conf_common.properties"));
            ksLiveStrategy = Integer.parseInt(propertiesUtil.getValue("ksLiveStrategy", "conf_common.properties"));
            ksLivePhoneList = propertiesUtil.getValue("ksLivePhoneList", "conf_common.properties");
        } catch (Exception e) {
            log.info("解析配置失败，isOpenKSCloudPlatform，ksLiveStrategy, ksLivePhoneList conf_common.properties");
        }
        if (isOpenKSCloudPlatform == 0) {  //不启用金山云直播
            return 1;
        }
//        # 使用金山的策略： 1 非签约 2 签约 3 手机 4 非签约/手机 5 签约/手机 6 所有主播
        int platform = 1;  // 1 腾讯云 2 金山云

        boolean isContainPhone = ksLivePhoneList != null && phone != null && !phone.equals("") && ksLivePhoneList.contains(phone);
        boolean isSign = signType == 1;
        switch (ksLiveStrategy) {
            case 1:    // 1 非签约直播使用金山云
                platform = !isSign ? 2 : 1;
                break;
            case 2:  // 2 签约直播使用金山云
                platform = isSign ? 2 : 1;
                break;
            case 3:   // 3 指定手机使用金山云
                platform = isContainPhone ? 2 : 1;
                break;
            case 4:    // 4 非签约/手机
                platform = !isSign || isContainPhone ? 2 : 1;
                break;
            case 5:  // 5 签约/手机
                platform = isSign || isContainPhone ? 2 : 1;
                break;
            case 6:  //所有主播
                platform = 2;
                break;
            default:
                platform = 1;  //
                break;
        }
        return platform;
    }

    // 拉流
    public KSLiveEntity getAndCreatePullLiveInfo(Map<Object, Object> userInfo) {
        return getAndCreateLiveInfo(userInfo, 1);
    }

    public KSLiveEntity getAndCreateLiveInfo(Map<Object, Object> userInfo, int type) {
        try {
            if (userInfo == null) {
                return null;
            }
            if (userInfo == null) {
                return null;
            }
            if (userInfo.get("uid") == null) {
                return null;
            }
            int livePlatForm = useWhichLivePlatForm(userInfo);
            if (livePlatForm == 1) {  // 1 腾讯直播， 2 金山云
                return null;
            }
            String vdoid = userInfo.get("uid").toString();
            return createKSLUrl(vdoid, type, livePlatForm);
        } catch (Exception e) {
            log.warn("生成金山推流url失败", e);
        }
        return null;
    }

    public KSLiveEntity createKSLPullUrl(String vdoid, LiverInfo liverInfo) throws UnsupportedEncodingException {
        int platform = useWhichLivePlatForm(liverInfo);
        return createKSLUrl(vdoid, 1, platform);
    }

    public KSLiveEntity createKSLPullUrl(String vdoid, Map<Object, Object> userInfo) throws UnsupportedEncodingException {
        int platform = useWhichLivePlatForm(userInfo);
        return createKSLUrl(vdoid, 1, platform);
    }

    private KSLiveEntity createKSLUrl(String vdoid, int type, int platform) throws UnsupportedEncodingException {
        String ksc_push_header_url = "";
        String ksc_pull_header_url = "";
        try {
            ksc_push_header_url = propertiesUtil.getValue("ksc_push_header_url", "conf_common.properties");
            ksc_pull_header_url = propertiesUtil.getValue("ksc_pull_header_url", "conf_common.properties");
        } catch (Exception e) {
            log.warn("解析金山云配置失败，ksc_push_header_url  ksc_pull_header_url", e);
        }
        // type = 1 拉流，否则推流
        String url = type == 1 ? ksc_pull_header_url + vdoid : ksc_push_header_url + vdoid + "?" + getStreamUrlNormal(vdoid);
        KSLiveEntity ksLiveEntity = new KSLiveEntity();
        ksLiveEntity.setPlatform(platform);  // 1 腾讯 2 金山
        ksLiveEntity.setUrl(url);
        return ksLiveEntity;
    }


    public BaseResponse getKSLInfoByUid(BaseRequest request) {
        try {
            //获取uid
            String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
            if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
                return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
            }
            Map<Object, Object> userInfo = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
            KSLiveEntity entity = createKSLPullUrl(uid, userInfo);

            Map<Object, Object> resultMap = new HashMap<Object, Object>();
            if (entity != null) {
                resultMap.put("livePlatform", entity.getPlatform());
                resultMap.put("liveRtmpUrl", entity.getUrl());
            }
            MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "获取直播平台信息成功");
            mapResponse.setResponse(resultMap);
            return mapResponse;
        } catch (Exception e) {
            log.warn("获取直播平台信息失败", e);
            return new BaseResponse(ResponseCode.FAILURE, "获取直播平台信息失败");
        }
    }


    /**
     * 心跳检测
     * @param request
     * @return
     */
    public MapResponse checkAnchorLiveHeartBeat(KSCloudHBRequest request) {
        try {
            long now = System.currentTimeMillis();
            String key = redis_ksc_live_header + request.getRecordId();
            stringRedisTemplate.opsForValue().set(key, String.valueOf(now), timeoutHeartbeat, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.warn("金山云心跳异常", e);
        }
        return new MapResponse(ResponseCode.SUCCESS, "心跳保存成功");
    }

    // 直播配置
    public BaseResponse liveConf(KSCloudConfRequest request) {
        MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "获取成功");
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
        map.put("liveRtmpUrl", "");  //拉流地址
        try {
            Map<Object, Object> tmp = null;
            if (request.getmType() == 1) {  //推流
                map = liveConfPush(request);
            } else {
                map = liveConfPull(request);
                if (map == null) {
                    return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
                }
            }
            map.putAll(tmp);
        } catch (Exception e) {
            log.warn("获取直播配置失败");
        }
        mapResponse.setResponse(map);
        return mapResponse;
    }

    // 拉流
    private Map<Object, Object> liveConfPull(KSCloudConfRequest request) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            map.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
            map.put("liveRtmpUrl", "");  //拉流地址
            LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryLiveRecordById(request.getLiveRecordId());
            long anchorId = 0;
            Map<Object, Object> userInfo = null;
            String uid = null;
            if (liveRecordInfo != null) {
                anchorId = liveRecordInfo.getAnchor_id() == null ? 0 : liveRecordInfo.getAnchor_id();
            }
            if (anchorId != 0) {
                userInfo = liveUserDao.queryLiverInfoById((int)anchorId);
                uid = userInfo == null ? null : userInfo.get("uid").toString();
            }
            if (uid != null && userInfo != null) {
                //  生成拉流地址
                KSLiveEntity entity = createKSLPullUrl(uid, userInfo);
                if (entity != null) {
                    map.put("livePlatform", entity.getPlatform());
                    map.put("liveRtmpUrl", entity.getUrl());
                }
            }
        } catch (Exception e) {
            log.warn("添加拉流失败", e);
        }
        return map;
    }

    // 推流
    private Map<Object, Object> liveConfPush(KSCloudConfRequest request) throws UnsupportedEncodingException {
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            map.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
            map.put("liveRtmpUrl", "");  //流地址
            //获取uid
            String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
            if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
                return null;
            }
            Map<Object, Object> userInfo = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
            int platform = useWhichLivePlatForm(userInfo);
            // type = 1 拉流，否则推流
            KSLiveEntity entity = createKSLUrl(uid, 2, platform);

            Map<Object, Object> resultMap = new HashMap<Object, Object>();
            if (entity != null) {
                resultMap.put("livePlatform", entity.getPlatform());
                resultMap.put("liveRtmpUrl", entity.getUrl());
            }
            return resultMap;
        } catch (Exception e) {
            log.warn("获取直播平台信息失败", e);
            throw e;
        }
    }


    /**
     * 常规鉴权
     */
    private String getStreamUrlNormal(String vdoid) throws UnsupportedEncodingException {

        // 点播需要
        String sTime = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        vdoid = URLEncoder.encode(vdoid+"-"+sTime, "utf-8");

        StringBuilder sb = new StringBuilder();
//        sb.append("rtmp://niaoren888.uplive.ks-cdn.com/live/");
//        sb.append(vdoid);
//        sb.append("?");

        String ks_live_ak = "";
        String ks_live_pak = "";
        // 12个月 单位秒
        long ksc_expire_time = 3600 * 24 * 30 * 12;
        try {
            ks_live_ak = propertiesUtil.getValue("ks_live_ak", "conf_common.properties");
            ks_live_pak = propertiesUtil.getValue("ks_live_pak", "conf_common.properties");
            ksc_expire_time = Long.parseLong(propertiesUtil.getValue("ksc_expire_time", "conf_common.properties"));
        } catch (Exception e) {
            log.warn("解析金山云配置失败，ks_live_ak  ks_live_pak ksc_expire_time", e);
        }

        String nonce = getRandomString(32);
        String resource = "nonce=" + nonce + "&vdoid=" + vdoid;
//        String resource = "nonce=" + Uri.encode(nonce) + "&vdoid=" + Uri.encode(vdoid);

//        long expire = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + (ksc_expire_time * 1000);
        Calendar calendar = Calendar.getInstance();
        int offset = calendar.get(Calendar.ZONE_OFFSET);
        calendar.add(Calendar.MILLISECOND, -offset);
        Date date = calendar.getTime();
        long millisecond = date.getTime();

        long expire = millisecond  + ksc_expire_time * 1000;

//        expire = 158763240L;
        String stringToSign = "GET\n" + expire + "\n" + resource;

        byte[] sha1 = new byte[0];
        try {
            sha1 = HmacSHA1Encrypt(stringToSign, ks_live_pak);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String signature = Base64.encodeBase64String(sha1);
        signature = signature.substring(0,28);
        sb.append("signature=").append(URLEncoder.encode(signature, "utf-8")).append("&accesskey=").append(ks_live_ak).append("&expire=").append(expire).append("&nonce=")
                .append(nonce).append("&vdoid=").append(vdoid);
        return sb.toString();
    }


    private byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes("UTF-8");
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance("HmacSHA1");
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("UTF-8");
        //完成 Mac 操作
        return mac.doFinal(text);
    }

    private String byte2hex(byte[] b)
    {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    private String hamcsha1(byte[] data, byte[] key)
    {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}
