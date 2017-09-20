package com.xmn.designer.controller.api.v1.material.vo;

import javax.validation.constraints.NotNull;

import com.xmn.designer.base.Request;

public class MaterialSearchRequest extends Request{
    
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -6865882282159308146L;



    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    @NotNull(message = "搜索关键字不能为空")
    private String keys;
    
    @NotNull(message = "页数不能为空")
    private Integer page;
    
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;



    @Override
    public String toString() {
        return "MaterialSearchRequest [keys=" + keys + ", page=" + page + ", pageSize=" + pageSize
                + "]";
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

    
    


}
