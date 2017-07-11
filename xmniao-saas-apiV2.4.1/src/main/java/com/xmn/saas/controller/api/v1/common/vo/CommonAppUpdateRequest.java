package com.xmn.saas.controller.api.v1.common.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;

/**
 * create 2016/10/21
 *
 * @author yangQiang
 */

public class CommonAppUpdateRequest extends Request {

    @NotNull(message = "系统版本号不能为空")
    private String systemVersion;

    @NotNull(message = "App版本号不能为空")
    private String appVersion;

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
