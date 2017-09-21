package com.xmniao.service.manor.vo;

import java.util.Date;

/**
 * 黄金庄园激活信息Bean
 * Created by yang.qiang on 2017/6/19.
 */
public class ManorActivateInfo {
    private Integer buildStatus; // 建设状态 1:建设中  2:建设完成
    private Date buildTime;    // 建设完成时间
    private Date manorDeadline; // 庄园截至时间
    private String payType;     // 2,阳光 5.能量(对应花苗，前台转成花苗,默认就是1花苗)
    private String payNumber;   // 消费数量
    private Integer activateDays;   // 激活天数
    private Integer activateType;   // 庄园激活操作类型 1.激活 2.续租


    public ManorActivateInfo() {
        super();
    }

    public ManorActivateInfo(Integer buildStatus, Date buildTime, Date manorDeadline, String payType, String payNumber) {
        this.buildStatus = buildStatus;
        this.buildTime = buildTime;
        this.manorDeadline = manorDeadline;
        this.payType = payType;
        this.payNumber = payNumber;
    }

    public ManorActivateInfo(Integer activateType, Date manorDeadline, Integer activateDays) {
        this.manorDeadline = manorDeadline;
        this.activateDays = activateDays;
        this.activateType = activateType;
    }

    public Integer getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(Integer buildStatus) {
        this.buildStatus = buildStatus;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Date getManorDeadline() {
        return manorDeadline;
    }

    public void setManorDeadline(Date manorDeadline) {
        this.manorDeadline = manorDeadline;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public void setActivateDays(Integer activateDays) {
        this.activateDays = activateDays;
    }

    public Integer getActivateDays() {
        return activateDays;
    }

    public void setActivateType(Integer activateType) {
        this.activateType = activateType;
    }

    public Integer getActivateType() {
        return activateType;
    }
}
