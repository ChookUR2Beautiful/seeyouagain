package com.xmniao.xmn.core.kscloud.request;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * Created by Administrator on 2017/8/2.
 */
public class KSCloudHBRequest extends BaseRequest {

    @NotNull
    private Integer recordId;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    @Override
    public String toString() {
        return "KSCloudHBRequest{" +
                "recordId=" + recordId +
                '}';
    }
}
