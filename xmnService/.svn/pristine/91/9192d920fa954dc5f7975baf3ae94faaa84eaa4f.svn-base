package com.xmniao.xmn.core.market.controller.product.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
public class ProductAttrRequest extends BaseRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2550479187751113517L;


    
    @NotNull(message="商品编号不能为空！")
    private Integer codeId ;
    
    //活动id
    private Integer activityId;

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "ProductAttrRequest [codeId=" + codeId + ", activityId=" + activityId + "]";
    }
    
    
    
    
    
    
    
    

}
