package com.xmniao.xmn.core.common.request.live;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * Created by Administrator on 2017/5/17.
 */
public class LiveHomePlaybackV2Request extends BaseRequest {

    private static final long serialVersionUID = 3388118196929931712L;
    @NotNull(message="栏目类型不能为空")
    private Integer tabType = 1;  // 1 金牌推荐栏目 2 新人推荐栏目 3 缤纷娱乐栏目 4美食撩味栏目
    //页码
    private Integer page = 1;
    private Integer pageSize = 10;
    private Double longitude;  //用户经度
    private Double latitude;  //用户纬度

    public Integer getTabType() {
        return tabType;
    }

    public void setTabType(Integer tabType) {
        this.tabType = tabType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LiveHomePlaybackV2Request{" +
                "tabType=" + tabType +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
