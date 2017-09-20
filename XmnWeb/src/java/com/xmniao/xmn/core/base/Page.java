package com.xmniao.xmn.core.base;

/**
 * create 2016/11/23
 * 分页实体
 * @author yangQiang
 */

public class Page {

    private Integer limit = 15;
    private Integer page = 1;
    private Integer start;


    public Integer getStart() {
        return (page -1) * limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
