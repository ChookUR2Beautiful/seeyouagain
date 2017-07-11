package com.xmn.saas.controller.h5.opinion.vo;

import com.xmn.saas.base.Page;
import com.xmn.saas.base.Request;
import org.springframework.beans.BeanUtils;

/**
 * create 2016/11/29
 *
 * @author yangQiang
 */

public class CelebrityListRequest extends Request {

    private Integer pageSize = 5;
    private Integer pageNum = 0;


    public Page convetToPage(){
        Page page = new Page();
        BeanUtils.copyProperties(this,page);
        return page;
    }
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
