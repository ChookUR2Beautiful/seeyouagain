package com.xmn.saas.base;

/**
 * create 2016/11/23
 * 分页实体
 * @author yangQiang
 */

public class Page {

    private Integer pageSize = 5;
    private Integer pageNum = 0;
    private Integer start;


    public Integer getStart() {
        return pageNum * pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
