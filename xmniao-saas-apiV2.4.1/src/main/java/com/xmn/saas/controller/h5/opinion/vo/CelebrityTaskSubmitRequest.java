package com.xmn.saas.controller.h5.opinion.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * create 2016/11/28
 *
 * @author yangQiang
 */

public class CelebrityTaskSubmitRequest extends Request {

    // 主播id
    @NotNull(message = "主播id不能为空")
    private Long celebrityId;

    // 服务类型     1:晒照    2:晒照+分享
    @NotNull(message = "服务类型不能为空")
    private Integer serviceType;

    // 简单描述
    @NotNull(message = "简单描述信息不能为空")
    private String simpleDescribe;

    // 具体描述
    @NotNull(message = "具体描述信息不能为空")
    private String detailDescribe;

    // 预计到店时间
    @NotNull(message = "到店时间不能为空")
    private Date arrivalTime;

    public CelebrityOrder convertToCelebrityTask(){
        CelebrityOrder celebrityTask = new CelebrityOrder();
        BeanUtils.copyProperties(this,celebrityTask);
        return celebrityTask;
    }


    @Override
    public String toString() {
        return "CelebrityTaskSubmitRequest{" +
                "celebrityId=" + celebrityId +
                ", serviceType=" + serviceType +
                ", simpleDescribe='" + simpleDescribe + '\'' +
                ", detailDescribe='" + detailDescribe + '\'' +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    public Long getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(Long celebrityId) {
        this.celebrityId = celebrityId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getSimpleDescribe() {
        return simpleDescribe;
    }

    public void setSimpleDescribe(String simpleDescribe) {
        this.simpleDescribe = simpleDescribe;
    }

    public String getDetailDescribe() {
        return detailDescribe;
    }

    public void setDetailDescribe(String detailDescribe) {
        this.detailDescribe = detailDescribe;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
