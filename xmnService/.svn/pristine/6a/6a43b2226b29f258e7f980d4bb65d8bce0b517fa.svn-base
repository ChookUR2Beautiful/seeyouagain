package com.xmniao.xmn.core.kscloud.request;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * Created by Administrator on 2017/8/2.
 */
public class KSCloudConfRequest extends BaseRequest {

    private Integer liveRecordId;  // 拉流必须传
    @NotNull
    private Integer mType = 2;   //1 推流 2 拉流

    public Integer getLiveRecordId() {
        return liveRecordId;
    }

    public void setLiveRecordId(Integer liveRecordId) {
        this.liveRecordId = liveRecordId;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    @Override
    public String toString() {
        return "KSCloudConfRequest{" +
                "liveRecordId=" + liveRecordId +
                ", mType=" + mType +
                '}';
    }
}
