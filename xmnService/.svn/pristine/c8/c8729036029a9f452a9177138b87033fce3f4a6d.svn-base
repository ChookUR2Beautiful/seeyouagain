package com.xmniao.xmn.core.market.controller.product.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
public class ProductCollectRequest extends BaseRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2550479187751113517L;


    
    @NotNull(message="商品编号不能为空！")
    private Integer codeId ;
    
    @NotNull(message="收藏类型不能为空！")
    private Integer collect;
    
    //活动id
    private Integer activityId;
    
    

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

 

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    @Override
    public String toString() {
        return "ProductCollectRequest [codeId=" + codeId + ", collect=" + collect + ", activityId="
                + activityId + "]";
    }

    
    
    
    
    
    
    
    

}
