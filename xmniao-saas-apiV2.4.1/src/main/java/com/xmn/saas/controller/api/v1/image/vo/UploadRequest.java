package com.xmn.saas.controller.api.v1.image.vo;

import com.xmn.saas.base.Request;

/**
 * create 2016/09/29
 *
 * @author yangQiang
 */

public class UploadRequest extends Request {

    private String sessionToken;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "UploadRequest{" +
                "sessionToken='" + sessionToken + '\'' +
                '}';
    }
}
