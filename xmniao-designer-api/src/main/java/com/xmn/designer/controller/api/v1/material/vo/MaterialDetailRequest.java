package com.xmn.designer.controller.api.v1.material.vo;

import javax.validation.constraints.NotNull;

import com.xmn.designer.base.Request;

public class MaterialDetailRequest extends Request {
    private static final long serialVersionUID = 7849661756421644739L;
    
    @NotNull( message = "平面物料主键不能为空" )
    private Long id;
    
    @NotNull( message = "配送地编号不能为空" )
    private String deliveryCityNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryCityNo() {
        return deliveryCityNo;
    }

    public void setDeliveryCityNo(String deliveryCityNo) {
        this.deliveryCityNo = deliveryCityNo;
    }

    @Override
    public String toString() {
        return "MaterialDetailRequest [id=" + id + ", deliveryCityNo=" + deliveryCityNo + "]";
    }

    

}
