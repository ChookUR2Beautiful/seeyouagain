package com.xmniao.xmn.core.market.base;

/**
 * create 2016/11/23
 * 分页实体
 * @author yangQiang
 */

public class Page {

    private Integer pageSize = 10;
    private Integer page = 1;
    private Integer start;
    // 查询最大值
    private Integer maxSize;

    public Page() {
        /**  Created by yang qiang on 2016/12/27. */
        super();
    }

    public Page(int pageNum, Integer pageSize) {
        /**  Created by yang qiang on ${DATE}. */
        this.page = pageNum;
        this.pageSize = pageSize;
    }


    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;

    }

    public Integer getStart() {
        return (page -1) * pageSize;
    }

    public Integer getPageSize() {
        // 判断是否设置了最大查询数
        if (this.maxSize != null) {
            int queryNum = this.getStart() + this.pageSize;
            if((queryNum) > this.maxSize){
                int size = maxSize - this.getStart();
                return size > 0 ? size : 0;
            }
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Page{" +
            "pageSize=" + pageSize +
            ", page=" + page +
            ", start=" + start +
            '}';
    }
}
