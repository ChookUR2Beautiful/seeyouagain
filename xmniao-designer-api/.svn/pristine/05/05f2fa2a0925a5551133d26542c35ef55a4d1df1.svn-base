package com.xmn.designer.controller.api.v1.material.vo;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.xmn.designer.base.Request;
import com.xmn.designer.entity.material.MaterialAttrGroup;
import com.xmn.designer.entity.order.Order;

public class MaterialOrderDetailRequest extends Request{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -2352590517624868361L;

    @NotNull(message = "物料主键不能为空")
    private Integer id;
    
    @NotNull(message = "物料销售属性表id组合不能为空")
    private String materialAttrIds;
    
    
    @NotNull(message = "物料销售属性值组合不能为空")
    private String materialAttrVals;

    @NotNull(message = "购买份数不能为空")
    private Integer nums;
    
    @NotNull(message = "区域id不能为空")
    private Integer areaId;
    

    
    
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }


    public String getMaterialAttrIds() {
        return materialAttrIds;
    }

    public void setMaterialAttrIds(String materialAttrIds) {
        this.materialAttrIds = materialAttrIds;
    }

    public String getMaterialAttrVals() {
        return materialAttrVals;
    }

    public void setMaterialAttrVals(String materialAttrVals) {
        this.materialAttrVals = materialAttrVals;
    }

    
    public MaterialAttrGroup convertToMaterialAttrGroup(){
        MaterialAttrGroup materialAttrGroup =new MaterialAttrGroup();
        materialAttrGroup.setMaterialId(Long.valueOf(this.getId()));
        BeanUtils.copyProperties(this,materialAttrGroup);
        return materialAttrGroup;
        
    }

    @Override
    public String toString() {
        return "MaterialOrderDetailRequest [id=" + id + ", materialAttrIds=" + materialAttrIds
                + ", materialAttrVals=" + materialAttrVals + ", nums=" + nums + ", areaId="
                + areaId + "]";
    }

    

   
    
    
    
    
    
    


}
