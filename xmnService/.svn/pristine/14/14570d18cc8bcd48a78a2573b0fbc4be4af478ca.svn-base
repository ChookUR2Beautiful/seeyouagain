package com.xmniao.xmn.core.util;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * Created by Administrator on 2017/5/16.
 */
public class VersionUtil {

    public static int getVersionCode(BaseRequest request) {
        String appversion = request.getAppversion();
        appversion = appversion.replace(".", "");
        int appv = Integer.parseInt(appversion);
        return appv;
    }

    public static int getVersionCode(String appversion) {
        appversion = appversion.replace(".", "");
        int appv = Integer.parseInt(appversion);
        return appv;
    }

    public static boolean isIOS(BaseRequest request) {
        return request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains("ios");
    }

    public static boolean isAndroid(BaseRequest request) {
        return request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains("android");
    }
}
