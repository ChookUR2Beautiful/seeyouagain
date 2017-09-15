package com.xmniao.xmn.core.market.controller.activity.vo;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * Created by yang.qiang on 2017/2/23.
 */
public class IndianaPayRequest extends BaseRequest{
    // 夺宝期号
    @NotNull
    private Integer boutId;
    // 夺宝份数
    @Min(value = 1)
    private Integer point;

    public Integer getBoutId() {
        return boutId;
    }

    public void setBoutId(Integer boutId) {
        this.boutId = boutId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "IndianaPayRequest{" +
            "boutId=" + boutId +
            ", point=" + point +
            '}';
    }
}
