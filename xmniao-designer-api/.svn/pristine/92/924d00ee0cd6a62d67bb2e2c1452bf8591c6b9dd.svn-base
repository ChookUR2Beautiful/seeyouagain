package com.xmn.designer.controller.api.v1.material.vo;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.xmn.designer.base.Request;
import com.xmn.designer.entity.material.MaterialAttrGroup;
import com.xmn.designer.entity.order.Order;

public class MaterialCreateOrderRequest extends Request{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -2352590517624868361L;

    @NotNull(message = "物料主键不能为空")
    private Integer id;
    
    @NotNull(message = "物料销售属性表id组合不能为空")
    private String materialAttrIds;
    
    @NotNull(message = "收货电话不能为空")
    private String mobile;
    
    @NotNull(message = "收货人不能为空")
    private String consignee;
    
    
    @NotNull(message = "物料销售属性值组合不能为空")
    private String materialAttrVals;

    @NotNull(message = "购买份数不能为空")
    private Integer nums;
    
    @NotNull(message = "区域id不能为空")
    private Integer areaId;
    
    @NotNull(message = "邮寄地址不能为空")
    private String shippingAddress;
    
    @NotNull(message = "留言备注不能为空")
    private String remark;
    
    @NotNull(message = "订单元数据不能为空")
    private String dataList;

    
    
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

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getDataList() {
        return dataList;
    }

    public void setDataList(String dataList) {
        this.dataList = dataList;
    }
    
    

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

  

    @Override
    public String toString() {
        return "MaterialCreateOrderRequest [id=" + id + ", materialAttrIds=" + materialAttrIds
                + ", mobile=" + mobile + ", consignee=" + consignee + ", materialAttrVals="
                + materialAttrVals + ", nums=" + nums + ", areaId=" + areaId + ", shippingAddress="
                + shippingAddress + ", remark=" + remark + ", dataList=" + dataList + "]";
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

    public Order convertToOrder(Long id){
        Order order = new Order();
        order.setType("001");//平面物料
        order.setUid(id);
        order.setDeliveryAddress(this.getShippingAddress());
        order.setLeaveNote(this.getRemark());
        BeanUtils.copyProperties(this,order);
        return order;
    }
    
    public MaterialAttrGroup convertToMaterialAttrGroup(){
        MaterialAttrGroup materialAttrGroup =new MaterialAttrGroup();
        materialAttrGroup.setMaterialId(Long.valueOf(this.getId()));
        BeanUtils.copyProperties(this,materialAttrGroup);
        return materialAttrGroup;
        
    }


   
    
    
    
    
    
    


}
