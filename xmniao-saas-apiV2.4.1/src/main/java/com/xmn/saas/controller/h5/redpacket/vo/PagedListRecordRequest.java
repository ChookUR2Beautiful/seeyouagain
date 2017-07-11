package com.xmn.saas.controller.h5.redpacket.vo;


import com.xmn.saas.base.Request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * create 2016/11/10
 *
 * @author yangQiang
 */

public class PagedListRecordRequest extends Request {

    Integer pageNum = 0;

    @Max(20)
    Integer pageSize = 20;

    @NotNull(message = "红包Id不能为空!")
    private Long redpacketId;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getRedpacketId() {
        return redpacketId;
    }

    public void setRedpacketId(Long redpacketId) {
        this.redpacketId = redpacketId;
    }

    public Integer getPageNum() {

        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
